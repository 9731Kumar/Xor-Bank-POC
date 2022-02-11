package com.xoriant.bank.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.bank.controller.ManagerServiceResource;
import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Manager;
import com.xoriant.bank.entity.PersonalInfo;
import com.xoriant.bank.entity.Role;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;
import com.xoriant.bank.exception.ResourceNotFoundException;
import com.xoriant.bank.feignclients.AccountFeign;
import com.xoriant.bank.feignclients.CustomerFeign;
import com.xoriant.bank.feignclients.ReportFeign;
import com.xoriant.bank.feignclients.TransactionFeign;
import com.xoriant.bank.repository.AccountRepo;
import com.xoriant.bank.repository.CustomerRepo;
import com.xoriant.bank.repository.ManagerRepo;
import com.xoriant.bank.repository.PersonalInfoRepo;
import com.xoriant.bank.repository.UserRepo;

@Service
public class ManagerServiceImpl implements ManagerServcies {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private CustomerFeign customerFeign;
	
	@Autowired
	private AccountFeign accountFeign;
	
	@Autowired
	private TransactionFeign transactionFeign;
	
	@Autowired
	private ReportFeign reportFeign;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private PersonalInfoRepo personRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ManagerRepo managerRepo;
	
	

	

	@Override
	public Customer updateCustomerDetails(Customer customer, String managerEmail) {
		logger.info("Checking the customer for updating the Customer Details");
		Customer customer2 = customerRepo.findById(customer.getPersonId()).get();
		if (!customer2.getManager().getEmailId().equals(managerEmail)) {
			throw new ResourceNotFoundException("Invalid Customer");
		}
		return customerFeign.updateCustomer(customer, managerEmail);

	}

	@Override
	public Account saveNewAccount(AccountDTO accountDto, String managerEmail) {
		logger.info("Checking the customer for creating the new account");
		Customer customer = customerRepo.findById(accountDto.getCustomer().getPersonId()).get();
		String emailId = customer.getManager().getEmailId();
		if (managerEmail.equals(emailId)) {
			return accountFeign.addAccount(accountDto);
		} else {
			return null;
		}
	}

	@Override
	public Account updateAccount(AccountDTO accountDto, String managerEmail) {
		logger.info("Checking the customer for updating the account");
		Customer customer = customerRepo.findById(accountDto.getCustomer().getPersonId()).get();
		String emailId = customer.getManager().getEmailId();
		if (managerEmail.equals(emailId)) {
			return accountFeign.updateAccount(accountDto);
		} else {
			return null;
		}
	}

	@Override
	public String depositAmount(Transaction transaction, String managerEmail) {
		logger.info("Checking the customer for depositing the amount");
		Account account = accountRepo.findById(transaction.getToAccount().getAccountNumber()).get();
		Customer customer = customerRepo.findById(account.getCustomer().getPersonId()).get();
		String emailId = customer.getManager().getEmailId();
		
		if (managerEmail.equals(emailId)) {
//			System.out.println("Transaction block");
//			System.out.println("transaction input" + transaction.toString());
			return transactionFeign.depositTheAmount(transaction);
		} else {
			return "Account Number deos not belong to this branch";
		}
		
	}
	

	@Override
	public Transaction withdrawAmount(Transaction transaction, String managerEmail) {
		logger.info("Checking the customer for withdraw the amount");
		Account account = accountRepo.findById(transaction.getFromAccount().getAccountNumber()).get();
		Customer customer = customerRepo.findById(account.getCustomer().getPersonId()).get();
		String emailId = customer.getManager().getEmailId();
		if (managerEmail.equals(emailId)) {
			return transactionFeign.withdrawTheAmount(transaction);
		} else {
			return null;
		}
	}

	@Override
	public String changePassword(User user, String managerEmail) {
		logger.info("changing the password");
		PersonalInfo info = personRepo.getByEmailId(managerEmail);
		Role role = info.getUser().getRole();
		String userName = info.getUser().getUserName();
		if (role.toString().endsWith("MANAGER") && userName.equals(user.getUserName())) {
			User user2 = userRepo.findByUserName(user.getUserName());
			user2.setPassword(user.getPassword());
			user2.setRole(role);
			
			userRepo.save(user2);
			return "Password Changed Successfull";
		} 
		return "Try Again";
		
	}



	@Override
	public List<Transaction> getMiniStatement(String managerEmail, long accountNumber) {
		logger.info("Checking the customer for fetching the mini statement");
		Manager manager = managerRepo.findByEmailId(managerEmail);
		Set<Customer> customers = manager.getCustomers();
		
		Account account = accountRepo.findById(accountNumber).get();
		Customer cus = account.getCustomer();
		if (!customers.contains(cus)) {
			 throw new ResourceNotFoundException("Invalid Account Number does not belong to this Branch");
		}
		return reportFeign.getMiniStatementForAccount(accountNumber);
	}

	@Override
	public List<Transaction> getCustomizedStatement(String managerEmail,long accountNumber,
			String from, String to) {
//		try {
		logger.info("Checking the customer for fetching the customized statement");
		Manager manager = managerRepo.findByEmailId(managerEmail);
		Set<Customer> customers = manager.getCustomers();
		
		Account account = accountRepo.findById(accountNumber).get();
		Customer cus = account.getCustomer();
		if (!customers.contains(cus)) {
			 throw new ResourceNotFoundException("Invalid Account Number does not belong to this Branch");
		}
		return reportFeign.getCustomizedStatement(accountNumber, from, to);
//		} catch (Exception e) {
//			throw new 
//		}
	}

	@Override
	public Transaction fundTransfer(Transaction transaction, String managerEmail) {
		logger.info("Checking the customer for tranfering the amount");
		Account account = accountRepo.findById(transaction.getFromAccount().getAccountNumber()).get();
		Customer customer = customerRepo.findById(account.getCustomer().getPersonId()).get();
		String emailId = customer.getManager().getEmailId();
		if (managerEmail.equals(emailId)) {
			return transactionFeign.fundTransfer(transaction);
		}
		return null;
	}

	@Override
	public void deleteAccount(String managerEmail, long accountNumber) {
		logger.info("Checking the customer for deleting the account");
		Manager manager = managerRepo.findByEmailId(managerEmail);
		Set<Customer> customers = manager.getCustomers();
		
		Account account = accountRepo.findById(accountNumber).get();
		Customer cus = account.getCustomer();
		if (customers.contains(cus)) {
			 accountFeign.deleteAccount(accountNumber);
		}
		
		
	}
	

}
