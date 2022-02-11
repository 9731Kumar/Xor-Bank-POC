package com.xoriant.bank.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "saving_accounts")
@PrimaryKeyJoinColumn(name = "account_number", referencedColumnName = "accountNumber")
public class SavingAccount extends Account {
	
	private double dailyLimitAmount;
	private int dailyLimitNoOfTransaction;
	
	
	public SavingAccount() {
		super();
	}
	public SavingAccount(long accountNumber, String accountHolderName, AccountType accountType, LocalDate openDate,
			AccountStatus accountStatus, double balance, Set<Transaction> transactions, Customer customer,
			double dailyLimitAmount, int dailyLimitNoOfTransaction) {
		super(accountNumber, accountHolderName, accountType, openDate, accountStatus, balance, transactions, customer);
		this.dailyLimitAmount = dailyLimitAmount;
		this.dailyLimitNoOfTransaction = dailyLimitNoOfTransaction;
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
	@Override
	public String toString() {
		return "SavingAccount [dailyLimitAmount=" + dailyLimitAmount + ", dailyLimitNoOfTransaction="
				+ dailyLimitNoOfTransaction + ", getAccountNumber()=" + getAccountNumber() + ", getAccountHolderName()="
				+ getAccountHolderName() + ", getAccountType()=" + getAccountType() + ", getOpenDate()=" + getOpenDate()
				+ ", getAccountStatus()=" + getAccountStatus() + ", getBalance()=" + getBalance()
				+ ", getTransactions()=" + getTransactions() + ", getCustomer()=" + getCustomer() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
