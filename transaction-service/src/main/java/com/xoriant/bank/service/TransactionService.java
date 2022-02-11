package com.xoriant.bank.service;

import com.xoriant.bank.entity.Transaction;

public interface TransactionService {

	Transaction doTransaction(Transaction transaction, long accountNumber);

	String depositAmount(Transaction transaction) throws Exception;

	Transaction withdrawAmount(Transaction transaction);

	Transaction fundTransfer(Transaction transaction);

}
