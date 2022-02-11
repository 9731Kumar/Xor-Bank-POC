package com.xoriant.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "branches")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "branchId")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	private String branchName;
	private String branchAddress;
	private String ifscCode;
	private long micrNo;
	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Branch(int branchId, String branchName, String branchAddress, String ifscCode, long micrNo) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.ifscCode = ifscCode;
		this.micrNo = micrNo;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public long getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(long micrNo) {
		this.micrNo = micrNo;
	}

}
