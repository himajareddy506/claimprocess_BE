package com.hcl.claimprocessing.exception;

public class PolicyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PolicyNotFoundException(String message) {
		super(message);
	}
}
