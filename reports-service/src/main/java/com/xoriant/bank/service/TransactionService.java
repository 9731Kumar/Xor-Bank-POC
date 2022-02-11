package com.xoriant.bank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.xoriant.bank.dto.DateDto;
import com.xoriant.bank.entity.Transaction;

public interface TransactionService {

	List<Transaction> getMiniStatement(long accountNumber);

	List<Transaction> getCustomizedStatement(long accountNumber, String from, String to);

}
