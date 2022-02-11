package com.xoriant.bank.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.xoriant.bank.controller.ReportResource;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.repository.AccountRepo;
import com.xoriant.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private AccountRepo accountRepo;
	
	private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public List<Transaction> getMiniStatement(long accountNumber) {

		try {
			log.info("Fetching the ministatement for the account number: "+accountNumber+"in Service");
			Account account = accountRepo.findById(accountNumber).get();
//			if (account!=null) {
			List<Transaction> list = transactionRepo.findByAccountNumbers(accountNumber);
			List<Transaction> collect = list.stream()
					.sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).limit(5)
					.collect(Collectors.toList());
			return collect;
//			}
//			return null;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}
	}

	@Override
	public List<Transaction> getCustomizedStatement(long accountNumber, String from, String to) {
		try {
			log.info("Fetching the CustomizedStatement for the account number: "+accountNumber+"in Service");
			Account account = accountRepo.findById(accountNumber).orElseThrow();
			LocalDate fromDate = LocalDate.parse(from);
			LocalDate toDate = LocalDate.parse(to);

			List<Transaction> list = transactionRepo.findByDates(accountNumber, fromDate, toDate);
			return list;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}
	}

}
