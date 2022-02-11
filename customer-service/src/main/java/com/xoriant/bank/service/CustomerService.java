package com.xoriant.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.xoriant.bank.dto.DateDto;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;

public interface CustomerService {


	List<Transaction> getMiniStatement(long accountNumber);

	User changeCustomerPassword(User user);

	Customer saveTheCustomer(Customer customer,String email);

	Customer updateTheCustomer(Customer customer, String email);

	void deleteCustomer(String customerEmail);

	List<Transaction> getCustomizedStatement(long accountNumber, String from, String to);

}
