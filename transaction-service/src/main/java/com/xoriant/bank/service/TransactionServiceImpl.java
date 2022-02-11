package com.xoriant.bank.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.xoriant.bank.controller.RabbitSender;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.TransactionType;
import com.xoriant.bank.model.ExceptionModel;
import com.xoriant.bank.repository.AccountRepository;
import com.xoriant.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private RabbitSender sender;

	Random random = new Random();
	String msg = null;

	@Override
	public Transaction doTransaction(Transaction transaction, long fromAccountNumber) {

		Account account = accountRepo.findById(fromAccountNumber).get();
		double balance1 = account.getBalance();

		Account account2 = accountRepo.findById(transaction.getToAccount().getAccountNumber()).get();
		double balance2 = account2.getBalance();

		transaction.setFromAccount(account);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionId(random.nextLong());

		if (account.getBalance() > transaction.getTransactionAmount()
				&& fromAccountNumber != transaction.getToAccount().getAccountNumber()) {
			Transaction save = transactionRepo.save(transaction);
			if (save != null) {



				// long transactionId = save.getTransactionId();
				Account fromAccount = save.getFromAccount();
				Account toAccount = save.getToAccount();
				double amount = save.getTransactionAmount();

				fromAccount.setBalance(balance1 - amount);
				accountRepo.save(fromAccount);

				toAccount.setBalance(balance2 + amount);
				accountRepo.save(toAccount);

			}
			return save;
		}
		return transaction;

	}

	@Override
	@Transactional(rollbackOn = SQLException.class)
	public String depositAmount(Transaction transaction) throws Exception {

		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionId(random.nextLong());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		try {
			System.out.println("Transcation initiated");
			Transaction save = transactionRepo.save(transaction);
			System.out.println("Transcation initation completed");

			if (save != null) {

				Map<String, Object> depositInfo = new HashMap<String, Object>();
				depositInfo.put("Transaction_Id", save.getTransactionId());
				depositInfo.put("Transaction_Date", save.getTransactionDate());
				depositInfo.put("To_Account", save.getToAccount().getAccountNumber());
				depositInfo.put("Transaction_Amount", save.getTransactionAmount());
				// depositInfo.put("Transaction_Type", save.getTransactionType());

				 sender.sendDepositEmail(depositInfo);

				Set<Transaction> set = new HashSet<Transaction>();
				set.add(save);

				Account account = transaction.getToAccount();
				Account depositAccount = accountRepo.getById(account.getAccountNumber());
				double balance = depositAccount.getBalance();

				// updating the balance and setting transaction
				// depositAccount.setTransactions(set);
				depositAccount.setBalance(balance + transaction.getTransactionAmount());

				accountRepo.save(depositAccount);

				msg = "Transafering the " + transaction.getTransactionAmount() + " is Successfull..";
				return msg;
			} else {
				System.out.println("error in transaction");
				msg = "Transafering the " + transaction.getTransactionAmount() + " was not Successfull..";
				return msg;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Error("Transaction error");

		}
		// return msg;
	}

	@Override
	public Transaction withdrawAmount(Transaction transaction) {
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionId(random.nextLong());
		transaction.setTransactionType(TransactionType.WITHDRAWAL);
		try {
			Account check = accountRepo.findById(transaction.getFromAccount().getAccountNumber()).get();
			if (check.getBalance() > transaction.getTransactionAmount()) {
				Transaction save = transactionRepo.save(transaction);
				if (save != null) {

					Map<String, Object> withdrawInfo = new HashMap<String, Object>();
					withdrawInfo.put("Transaction_Id", save.getTransactionId());
					withdrawInfo.put("Transaction_Date", save.getTransactionDate());
					withdrawInfo.put("From_Account", save.getFromAccount().getAccountNumber());
					withdrawInfo.put("Transaction_Amount", save.getTransactionAmount());
					// depositInfo.put("Transaction_Type", save.getTransactionType());

					sender.sendWithDrawEmail(withdrawInfo);

					Set<Transaction> set = new HashSet<Transaction>();
					set.add(save);

					Account account = transaction.getFromAccount();
					Account withdrawAccount = accountRepo.getById(account.getAccountNumber());
					double balance = withdrawAccount.getBalance();

					// updating the balance and setting transaction
					// depositAccount.setTransactions(set);
					withdrawAccount.setBalance(balance - transaction.getTransactionAmount());

					accountRepo.save(withdrawAccount);

					return save;
				}
				return transaction;
			}
		} catch (Exception e) {
			throw new Error("Transaction error");
		}
		return null;

	}

	@Override
	public Transaction fundTransfer(Transaction transaction) {

		Account check = accountRepo.findById(transaction.getFromAccount().getAccountNumber()).orElseThrow();
		double balance = check.getBalance();
		// checking the balance is greater than amount for transfer
		if (balance > transaction.getTransactionAmount()) {
			transaction.setTransactionId(random.nextLong());
			transaction.setTransactionDate(LocalDate.now());
			transaction.setTransactionType(TransactionType.TRANSFER);

			Transaction save = transactionRepo.save(transaction);

//			Set<Transaction> set = new HashSet<Transaction>();
//			set.add(save);

			Account fromAccount = save.getFromAccount();
			Account toAccount = save.getToAccount();
			double amount = save.getTransactionAmount();

			fromAccount.setBalance(balance - amount);
			// fromAccount.setTransactions(set);
			accountRepo.save(fromAccount);

			toAccount.setBalance(balance + amount);
			// toAccount.setTransactions(set);
			accountRepo.save(toAccount);

			Map<String, Object> transferInfo = new HashMap<String, Object>();
			transferInfo.put("Transaction_Id", save.getTransactionId());
			transferInfo.put("Transaction_Date", save.getTransactionDate());
			transferInfo.put("From_Account", save.getFromAccount().getAccountNumber());
			transferInfo.put("To_Account", save.getToAccount().getAccountNumber());
			transferInfo.put("Transaction_Amount", save.getTransactionAmount());

			sender.sendTransferEmail(transferInfo);

			return save;
		}

		return null;
	}

}
