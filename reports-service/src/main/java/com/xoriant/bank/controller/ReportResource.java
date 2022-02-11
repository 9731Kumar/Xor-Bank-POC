package com.xoriant.bank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.bank.dto.DateDto;
import com.xoriant.bank.entity.Transaction;
import com.xoriant.bank.service.TransactionService;



@RestController
@RequestMapping("/report")
public class ReportResource {
	
	@Autowired
	private TransactionService transactionService;
	
	private static final Logger log = LoggerFactory.getLogger(ReportResource.class);
	
	@GetMapping("/miniStatement/{accountNumber}")
	public List<Transaction> getMiniStatementForAccount(@PathVariable long accountNumber ) {
		log.info("Fetching the ministatement for the account number: "+accountNumber);
		return transactionService.getMiniStatement(accountNumber);
	}
	
	@GetMapping("/customizedStatement/{accountNumber}/{from}/{to}")
	public List<Transaction> getCustomizedStatement(@PathVariable long accountNumber,@PathVariable String from,@PathVariable String to) {
		log.info("Fetching the CustomizedStatement for the account number: "+accountNumber);
		return transactionService.getCustomizedStatement(accountNumber, from,to);
	}

}
