package com.xoriant.bank.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	private long transactionId;
	private LocalDate transactionDate;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	private double transactionAmount;
	private String transactionDescription;
	
	//@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "from_account_number")
	private Account fromAccount;
	
	//@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "to_account_number")
	private Account toAccount;

	public Transaction() {
		super();
	}

	public Transaction(long transactionId, LocalDate transactionDate, TransactionType transactionType,
			double transactionAmount, String transactionDescription, Account fromAccount, Account toAccount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionDescription = transactionDescription;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

}
