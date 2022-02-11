package com.xoriant.bank.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.xoriant.bank.customerclients.CustomerAccountFeign;
import com.xoriant.bank.customerclients.CustomerReportFeign;
import com.xoriant.bank.customerclients.CustomerTransactionFeign;
import com.xoriant.bank.dto.DateDto;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;
import com.xoriant.bank.exception.ResourceNotFoundException;
import com.xoriant.bank.repository.CustomerRepository;
import com.xoriant.bank.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerServiceResource {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerTransactionFeign cusTraFeign;

	@Autowired
	private CustomerAccountFeign cusAccFeign;

	@Autowired
	private CustomerReportFeign cusRepFeign;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceResource.class);

	@PostMapping("/fundTransfer/{accountNumber}")
	public ResponseEntity<Transaction> doTranfer(@RequestBody Transaction transaction,
			@PathVariable long accountNumber) {
		try {
			logger.info("Transfering the amount by the customer from acc no.:"+accountNumber);
			Transaction tr = cusTraFeign.doTransaction(transaction, accountNumber);
			return new ResponseEntity<Transaction>(tr, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ivalid Account Number");
		}

	}

	@GetMapping("/balance/{accountNumber}")
	public ResponseEntity<?> checkBalance(@PathVariable long accountNumber) {

		try {
			logger.info("Fetching the balance of the customer by acc.no.: "+accountNumber);
			Double balance = cusAccFeign.getBalanceByAccountNumber(accountNumber);
			return new ResponseEntity<Double>(balance, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@GetMapping("/miniStatement/{accountNumber}")
	public ResponseEntity<?> getMiniStatement(@PathVariable long accountNumber) {
		try {
			logger.info("Fetching the mini statement for customer using acc.no.: "+accountNumber);
			List<Transaction> list = cusRepFeign.getMiniStatementForAccount(accountNumber);
			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@GetMapping("/customizedStatement/{accountNumber}/{from}/{to}")
	public ResponseEntity<?> getCustomizedStatement(@PathVariable long accountNumber, @PathVariable String from,
			@PathVariable String to) {
		try {
			logger.info("Fetching the Customized statement for customer using acc.no.: "+accountNumber);
			List<Transaction> list = cusRepFeign.getCustomizedStatement(accountNumber, from, to);
			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody User user) {
		try {
			logger.info("changing the password of the customer");
			User password = customerService.changeCustomerPassword(user);
			return new ResponseEntity<User>(password, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Name");
		}
	}

	@PostMapping("/saveCustomer/{email}")
	public Customer saveCustomer(@RequestBody Customer customer, @PathVariable String email) {
		logger.info("saving the customer");
		return customerService.saveTheCustomer(customer, email);
	}

	@PutMapping("/updateCustomer/{email}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable String email) {
		logger.info("updating the customer");
		return customerService.updateTheCustomer(customer, email);
	}

	@DeleteMapping("/deleteCustomer/{customerEmail}")
	public void deleteCustomer(@PathVariable String customerEmail) {
		logger.info("deleting the customer");
		customerService.deleteCustomer(customerEmail);
	}

}
