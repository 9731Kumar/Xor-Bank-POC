package com.xoriant.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.entity.Customer;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.entity.User;
import com.xoriant.bank.exception.ResourceNotFoundException;
import com.xoriant.bank.feignclients.AccountFeign;
import com.xoriant.bank.feignclients.CustomerFeign;
import com.xoriant.bank.service.ManagerServcies;


@RestController
@RequestMapping("/api/manager")
public class ManagerServiceResource {

	@Autowired
	private ManagerServcies managerServices;

	@Autowired
	private CustomerFeign customerFeign;

	@Autowired
	private AccountFeign accountFeign;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceResource.class);

	

	@PostMapping("/saveCustomer/{managerEmail}")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer, @PathVariable String managerEmail) {

		try {
			logger.info("Saving the new Customer");
			Customer saveCustomer = customerFeign.saveCustomer(customer, managerEmail);

			return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Try Again");
		}

	}

	@PutMapping("/updateCustomer/{managerEmail}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String managerEmail) {

		try {
			logger.info("Updating the new Customer");
			Customer saveCustomer = managerServices.updateCustomerDetails(customer, managerEmail);

			return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Deosn't updated");
		}
	}

	@PostMapping("/saveAccount/{managerEmail}")
	public ResponseEntity<?> saveNewAccount(@RequestBody AccountDTO accountDto, @PathVariable String managerEmail) {

		try {
			logger.info("Creating the new Account for customer");
			Account account = managerServices.saveNewAccount(accountDto, managerEmail);

			return new ResponseEntity<Account>(account, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Try Again");
		}

	}

	@PutMapping("/updateAccount/{managerEmail}")
	public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDto, @PathVariable String managerEmail) {

		try {
			logger.info("Updating the Account of the customer");
			Account account = managerServices.updateAccount(accountDto, managerEmail);

			return new ResponseEntity<Account>(account, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Doesn't updated");
		}

	}

	@PostMapping("/deposit/{managerEmail}")
	public String depositAmount(@RequestBody Transaction transaction, @PathVariable String managerEmail) {
		logger.info("depositing the amount in Customer Account");
		return managerServices.depositAmount(transaction, managerEmail);
	}

	@DeleteMapping("/delete/{managerEmail}/{accountNumber}")
	public void deleteAccount(@PathVariable String managerEmail, @PathVariable long accountNumber) {
		logger.info("deleting the account");
		managerServices.deleteAccount(managerEmail, accountNumber);
	}

	@PostMapping("/withdraw/{managerEmail}")
	public ResponseEntity<?> withdrawTheAmount(@RequestBody Transaction transaction,
			@PathVariable String managerEmail) {

		try {
			logger.info("Withdrawing the amount from the account: "+transaction.getFromAccount().getAccountNumber());
			Transaction withdrawAmount = managerServices.withdrawAmount(transaction, managerEmail);
			if (withdrawAmount != null) {
				return new ResponseEntity<Transaction>(withdrawAmount, HttpStatus.CREATED);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account No Doesn't exist in this branch");
			}

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@PostMapping("/fundTransfer/{managerEmail}")
	public ResponseEntity<?> fundTransfer(@RequestBody Transaction transaction, @PathVariable String managerEmail) {

		try {
			logger.info("Fund transfering ");
			Transaction transfer = managerServices.fundTransfer(transaction, managerEmail);
			if (transfer != null) {
				return new ResponseEntity<Transaction>(transfer, HttpStatus.CREATED);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account No Doesn't exist in this branch");
			}

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@PutMapping("/changePassword/{managerEmail}")
	public String changePassword(@RequestBody User user, @PathVariable String managerEmail) {
		logger.info("Changing the password");
		return managerServices.changePassword(user, managerEmail);
	}

	@GetMapping("/balanceEnquiry/{managerEmail}/{accountNumber}")
	public ResponseEntity<?> balanceEnquiry(@PathVariable String managerEmail, @PathVariable long accountNumber) {

		try {
			logger.info("fetching the balance");
			return accountFeign.getBalanceByManager1(managerEmail, accountNumber);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResourceNotFoundException("Invalid Account Number");
		}

	}

	@GetMapping("/miniStatement/{managerEmail}/{accountNumber}")
	public ResponseEntity<?> getMiniStatement(@PathVariable String managerEmail, @PathVariable long accountNumber) {

		try {
			logger.info("fetching the statement");
			List<Transaction> list = managerServices.getMiniStatement(managerEmail, accountNumber);

			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}

	}

	@GetMapping("/customizedStatement/{managerEmail}/{accountNumber}/{from}/{to}")
	public ResponseEntity<?> getCustomizedStatement(@PathVariable String managerEmail, @PathVariable long accountNumber,
			@PathVariable String from, @PathVariable String to) {

		try {
			logger.info("fetching the statement");
			List<Transaction> list = managerServices.getCustomizedStatement(managerEmail, accountNumber, from, to);

			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Execption due to "+e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Account Number");
		}
	}

}
