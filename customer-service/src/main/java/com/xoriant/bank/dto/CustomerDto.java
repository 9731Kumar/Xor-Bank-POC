package com.xoriant.bank.dto;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.xoriant.bank.entity.Address;
import com.xoriant.bank.entity.Gender;

public class CustomerDto {
	
	private String userName;
	private String password;
	private String name;
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String emailId;
	
	@Embedded
	private Address address;
	
	private long mobileNo;

	public CustomerDto(String userName, String password, String name, LocalDate dateOfBirth, Gender gender,
			String emailId, Address address, long mobileNo) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.emailId = emailId;
		this.address = address;
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	

}
