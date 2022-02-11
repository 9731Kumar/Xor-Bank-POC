package com.xoriant.bank.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "personId")
public class Customer extends PersonalInfo {

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<Account> accounts;

	//@JsonIdentityReference(alwaysAsId = true)
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "branchId")
	private Branch branch;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	//@JsonIdentityReference(alwaysAsId = true)
	private Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Customer() {
		super();
	}

	public Customer(int personId, String name, LocalDate dateOfBirth, Gender gender, String emailId, Address address,
			long mobileNo, User user, Set<Account> accounts, Branch branch, Manager manager) {
		super(personId, name, dateOfBirth, gender, emailId, address, mobileNo, user);
		this.accounts = accounts;
		this.branch = branch;
		this.manager = manager;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}
