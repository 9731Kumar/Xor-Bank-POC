package com.xoriant.bank.service;

import com.xoriant.bank.dto.AccountDTO;
import com.xoriant.bank.entity.Account;

public interface AccountService {
	
	 Double getBalance(long accountNumber);

	Account saveNewAccount(AccountDTO accountDto);

	Account updateAccount(AccountDTO accountDto);

	void deleteTheAccountB(long accountNumber);

	Double getBalanceByManager(String managerEmail, long accountNumber);
	
	

}
