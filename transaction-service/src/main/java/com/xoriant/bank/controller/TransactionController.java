package com.xoriant.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@PostMapping("/{accountNumber}")
	public Transaction doTransaction(@RequestBody Transaction transaction, @PathVariable long accountNumber) {	
		log.info("Amount transferring from the acc.no.: "+accountNumber);
		return transactionService.doTransaction(transaction, accountNumber);
	}
	
	@PostMapping("/deposit")
	//@ResponseStatus(value=HttpStatus.CREATED)
	public String depositTheAmount(@RequestBody Transaction transaction) throws Exception {
		log.info("Amount deposit to the acc.no.: "+transaction.getToAccount().getAccountNumber());
		    return transactionService.depositAmount(transaction);			
		    
	}
	
	
	@PostMapping("/withdraw")
	public Transaction withdrawTheAmount(@RequestBody Transaction transaction) {
		log.info("Amount withdraw the acc.no.: "+transaction.getFromAccount().getAccountNumber());
		return transactionService.withdrawAmount(transaction);
	}
	
	@PostMapping("/fundTransfer")
	public Transaction fundTransfer( @RequestBody Transaction transaction) {
		log.info("Amount transferred");
		return transactionService.fundTransfer(transaction);
	}
	
	

}
