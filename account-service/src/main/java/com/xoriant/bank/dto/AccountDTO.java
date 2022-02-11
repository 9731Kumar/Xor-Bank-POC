package com.xoriant.bank.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.xoriant.bank.entity.AccountStatus;
import com.xoriant.bank.entity.AccountType;
import com.xoriant.bank.entity.Customer;

public class AccountDTO {
	
	private long accountNumber;
	private String accountHolderName;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private LocalDate openDate;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private double balance;
	private Customer customer;
	private double dailyLimitAmount;
	private int dailyLimitNoOfTransaction;
	private double minimumBalance;
	private String companyName;
	
	public AccountDTO() {
		super();
	}
	
	public AccountDTO(long accountNumber, String accountHolderName, AccountType accountType, LocalDate openDate,
			AccountStatus accountStatus, double balance, Customer customer, double dailyLimitAmount,
			int dailyLimitNoOfTransaction, double minimumBalance, String companyName) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.openDate = openDate;
		this.accountStatus = accountStatus;
		this.balance = balance;
		this.customer = customer;
		this.dailyLimitAmount = dailyLimitAmount;
		this.dailyLimitNoOfTransaction = dailyLimitNoOfTransaction;
		this.minimumBalance = minimumBalance;
		this.companyName = companyName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public LocalDate getOpenDate() {
		return openDate;
	}
	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getDailyLimitAmount() {
		return dailyLimitAmount;
	}
	public void setDailyLimitAmount(double dailyLimitAmount) {
		this.dailyLimitAmount = dailyLimitAmount;
	}
	public int getDailyLimitNoOfTransaction() {
		return dailyLimitNoOfTransaction;
	}
	public void setDailyLimitNoOfTransaction(int dailyLimitNoOfTransaction) {
		this.dailyLimitNoOfTransaction = dailyLimitNoOfTransaction;
	}
	public double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
