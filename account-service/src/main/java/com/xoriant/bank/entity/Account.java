package com.xoriant.bank.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@Id
	private long accountNumber;
	private String accountHolderName;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	private LocalDate openDate;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private double balance;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "account_transactions", joinColumns = @JoinColumn(name="account_number")
	, inverseJoinColumns = @JoinColumn(name="transaction_id"))
	private Set<Transaction> transactions;
	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;


	


	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(long accountNumber, String accountHolderName, AccountType accountType, LocalDate openDate,
			AccountStatus accountStatus, double balance,Set<Transaction> transactions, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.openDate = openDate;
		this.accountStatus = accountStatus;
		this.balance = balance;
		this.transactions = transactions;
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


	public Set<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
