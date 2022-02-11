package com.xoriant.bank.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "current_accounts")
public class CurrentAccount extends Account{
	
	private double minimumBalance;
	private String companyName;
	
	public CurrentAccount() {
		super();
	}
	public CurrentAccount(long accountNumber, String accountHolderName, AccountType accountType, LocalDate openDate,
			AccountStatus accountStatus, double balance, Set<Transaction> transactions, Customer customer,
			double minimumBalance, String companyName) {
		super(accountNumber, accountHolderName, accountType, openDate, accountStatus, balance, transactions, customer);
		this.minimumBalance = minimumBalance;
		this.companyName = companyName;
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
