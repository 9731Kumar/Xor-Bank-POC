package com.xoriant.bank.dto;

import java.time.LocalDate;

public class DateDto {
	
	private LocalDate fromDate;
	public DateDto() {
		super();
	}
	private LocalDate toDate;
	
	public DateDto(LocalDate fromDate, LocalDate toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	

}
