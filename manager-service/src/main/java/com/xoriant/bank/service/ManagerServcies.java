package com.xoriant.bank.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;

public interface ManagerServcies {

	

	Customer updateCustomerDetails(Customer customer, String managerEmail);

	Account saveNewAccount(AccountDTO accountDto, String managerEmail);

	Account updateAccount(AccountDTO accountDto, String managerEmail);

	String depositAmount(Transaction transaction, String managerEmail);

	Transaction withdrawAmount(Transaction transaction, String managerEmail);

	String changePassword(User user, String managerEmail);

	

	List<Transaction> getMiniStatement(String managerEmail, long accountNumber);

	List<Transaction> getCustomizedStatement( String managerEmail, long accountNumber, String from, String to);

	Transaction fundTransfer(Transaction transaction, String managerEmail);

	void deleteAccount(String managerEmail, long accountNumber);

	

}
