package com.xoriant.bank.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "personId")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class Manager extends PersonalInfo{
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER, mappedBy = "manager")
	private Set<Customer> customers;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "branchId")
	private Branch branch;

	public Manager(int personId, String name, LocalDate dateOfBirth, Gender gender, String emailId, Address address,
			long mobileNo, User user, Set<Customer> customers, Branch branch) {
		super(personId, name, dateOfBirth, gender, emailId, address, mobileNo, user);
		this.customers = customers;
		this.branch = branch;
	}



	public Manager() {
		super();
	}

	

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}
