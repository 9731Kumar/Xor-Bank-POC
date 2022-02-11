package com.xoriant.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.xoriant.bank.controller.CustomerServiceResource;
import com.xoriant.bank.customerclients.CustomerReportFeign;
import com.xoriant.bank.dto.DateDto;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Branch;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Manager;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;
import com.xoriant.bank.repository.AccountRepository;
import com.xoriant.bank.repository.BranchRepository;
import com.xoriant.bank.repository.CustomerRepository;
import com.xoriant.bank.repository.ManagerRepository;
import com.xoriant.bank.repository.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private ManagerRepository managerRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private CustomerReportFeign cusRepFeign;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	

	@Override
	public List<Transaction> getMiniStatement(long accountNumber) {
		logger.info("Fetching the mini statement for customer using acc.no.: "+accountNumber);
		return cusRepFeign.getMiniStatementForAccount(accountNumber);
		
	}

	@Override
	public User changeCustomerPassword(User user) {
		logger.info("changing the password of the customer");
		User user2 = userRepo.findByUserName(user.getUserName());
		if (user2.getRole().toString().equals("CUSTOMER") ) {
			user2.setPassword(user.getPassword());
			User save = userRepo.save(user2);
			return save;
		} 
		return null;
	}

	@Override
	public Customer saveTheCustomer(Customer customer,String email) {
		logger.info("saving the customer");
		Manager id = managerRepo.findByEmailId(email);
		int managerId = id.getPersonId();
		Branch branch = id.getBranch();
		// Manager manager = managerRepo.findById(managerId).get();
		 
		 customer.setManager(id);
		 customer.setBranch(branch);
		 
		return customerRepo.save(customer);
		
	}

	@Override
	public Customer updateTheCustomer(Customer customer, String email) {
		logger.info("updating the customer");
		 Customer updateCus = customerRepo.findById(customer.getPersonId()).get();
		 
		 customer.setUser(updateCus.getUser());
		 Manager id = managerRepo.findByEmailId(email);
			int managerId = id.getPersonId();
			Branch branch = id.getBranch();
			// Manager manager = managerRepo.findById(managerId).get();
			 
			 customer.setManager(id);
			 customer.setBranch(branch);
			Customer save = customerRepo.save(customer);
			if (save!=null) {
				return save;
			} else {
				return null;
			}
	}

	@Override
	@Transactional
	public void deleteCustomer(String customerEmail) {
		logger.info("deleting the customer");
		Customer customer = customerRepo.findByEmailId(customerEmail);
		int userId = customer.getUser().getUserId();
		customerRepo.deleteByEmailId(customerEmail);
		userRepo.deleteById(userId);
		
		
	}

	@Override
	public List<Transaction> getCustomizedStatement(long accountNumber, String from, String to) {
		logger.info("Fetching the Customized statement for customer using acc.no.: "+accountNumber);
		return cusRepFeign.getCustomizedStatement(accountNumber, from,to);
	}

}
