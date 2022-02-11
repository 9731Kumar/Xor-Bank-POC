package com.xoriant.bank.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xoriant.bank.controller.AccountController;
import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.AccountType;
import com.xoriant.bank.entity.CurrentAccount;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Manager;
import com.xoriant.bank.entity.SavingAccount;
import com.xoriant.bank.exception.ResourceNotFoundException;
import com.xoriant.bank.repository.AccountRepo;
import com.xoriant.bank.repository.CurrentAccountRepo;
import com.xoriant.bank.repository.ManagerRepo;
import com.xoriant.bank.repository.SavingAccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private SavingAccountRepo savingAccRepo;

	@Autowired
	private CurrentAccountRepo currentAccRepo;
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	private SavingAccount savingAccount = new SavingAccount();
	private CurrentAccount currentAcc = null;

	@Override
	public Double getBalance(long accountNumber) {

		log.info("Fetching the balace for the acc no.:"+accountNumber);
		Account account = accountRepo.findById(accountNumber).orElse(null);

		double balance = account.getBalance();
		// System.out.println(balance);
		return balance;

	}

	@Override
	public Double getBalanceByManager(String managerEmail, long accountNumber) {
		
		log.info("Fetching the balace for the acc no.:"+accountNumber);
		Manager manager = managerRepo.findByEmailId(managerEmail);
		Set<Customer> customers = manager.getCustomers();
		
		Account account = accountRepo.findById(accountNumber).orElseThrow(()-> new ResourceNotFoundException("Account no not found"));
		Customer cus = account.getCustomer();
		if (!customers.contains(cus)) {
			throw new ResourceNotFoundException("customer not found");
		}
		return account.getBalance();

	}

	@Override
	public Account saveNewAccount(AccountDTO accountDto) {
		log.info("saving the new account for the customer");

		AccountType type = accountDto.getAccountType();
		if (type.toString().equals("SAVING")) {
			savingAccount.setAccountNumber(accountDto.getAccountNumber());
			savingAccount.setAccountHolderName(accountDto.getAccountHolderName());
			savingAccount.setAccountType(accountDto.getAccountType());
			savingAccount.setOpenDate(accountDto.getOpenDate());
			savingAccount.setAccountStatus(accountDto.getAccountStatus());
			savingAccount.setBalance(accountDto.getBalance());
			savingAccount.setCustomer(accountDto.getCustomer());
			savingAccount.setDailyLimitAmount(accountDto.getDailyLimitAmount());
			savingAccount.setDailyLimitNoOfTransaction(accountDto.getDailyLimitNoOfTransaction());
			// System.out.println(savingAccount);
			return savingAccRepo.save(savingAccount);

		} else {
			CurrentAccount currentAcc = new CurrentAccount(accountDto.getAccountNumber(),
					accountDto.getAccountHolderName(), accountDto.getAccountType(), accountDto.getOpenDate(),
					accountDto.getAccountStatus(), accountDto.getBalance(), null, accountDto.getCustomer(),
					accountDto.getMinimumBalance(), accountDto.getCompanyName());

			return currentAccRepo.save(currentAcc);

		}
	}

	@Override
	public Account updateAccount(AccountDTO accountDto) {
		
		AccountType type = accountDto.getAccountType();
		if (type.toString().equals("SAVING")) {
			log.info("updating the saving account of the customer");
			SavingAccount savingAcc = new SavingAccount(accountDto.getAccountNumber(),
					accountDto.getAccountHolderName(), accountDto.getAccountType(), accountDto.getOpenDate(),
					accountDto.getAccountStatus(), accountDto.getBalance(), null, accountDto.getCustomer(),
					accountDto.getDailyLimitAmount(), accountDto.getDailyLimitNoOfTransaction());

			return savingAccRepo.save(savingAcc);

		} else {
			log.info("updating the current account of the customer");
			CurrentAccount currentAcc = new CurrentAccount(accountDto.getAccountNumber(),
					accountDto.getAccountHolderName(), accountDto.getAccountType(), accountDto.getOpenDate(),
					accountDto.getAccountStatus(), accountDto.getBalance(), null, accountDto.getCustomer(),
					accountDto.getMinimumBalance(), accountDto.getCompanyName());

			return currentAccRepo.save(currentAcc);

		}
	}

	@Override
	@Transactional
	public void deleteTheAccountB(long accountNumber) {
		String msg = null;

		log.info("deleting the account of the customer");
		SavingAccount account = savingAccRepo.findByAccountNumber(accountNumber);
		if (account != null) {
			savingAccRepo.deleteByAccountNumber(accountNumber);

		}

	}

}
