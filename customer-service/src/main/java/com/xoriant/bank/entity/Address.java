package com.xoriant.bank.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private int flatNo;
	private String flatName;
	private String streetName;
	private String city;
	private String state;
	private int pinCode;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int flatNo, String flatName, String streetName, String city, String state, int pinCode) {
		super();
		this.flatNo = flatNo;
		this.flatName = flatName;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getFlatName() {
		return flatName;
	}
	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

}
