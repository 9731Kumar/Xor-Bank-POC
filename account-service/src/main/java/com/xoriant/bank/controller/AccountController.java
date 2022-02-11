package com.xoriant.bank.controller;

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

import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;
import com.xoriant.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@GetMapping("/{accountNumber}")
	public Double getBalanceByAccountNumber(@PathVariable long accountNumber) {
		log.info("Fetching the balance for account number: "+accountNumber);
		return accountService.getBalance(accountNumber);
	}
	
	@GetMapping("balanceByManager/{managerEmail}/{accountNumber}")
	public ResponseEntity<?> getBalanceByManager1(@PathVariable String managerEmail,@PathVariable long accountNumber) {
		log.info("Fetching the balance for account number: "+accountNumber);
		return new ResponseEntity<Double>(accountService.getBalanceByManager(managerEmail,accountNumber),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public Account addAccount(@RequestBody AccountDTO accountDto) {
		log.info("Creating the new account for the customer");
		return accountService.saveNewAccount(accountDto);
		//System.out.println(accountDto);
	}
	
	@PutMapping("/update")
	public Account updateAccount(@RequestBody AccountDTO accountDto) {
		log.info("updating the account for the customer");
		return  accountService.updateAccount(accountDto);
		//System.out.println(accountDto);
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public void deleteAccount(@PathVariable long accountNumber) {
		 log.info("deleting the account of "+accountNumber);
		 accountService.deleteTheAccountB(accountNumber);
	}

}
