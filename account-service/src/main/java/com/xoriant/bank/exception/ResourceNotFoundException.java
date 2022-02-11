package com.xoriant.bank.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
