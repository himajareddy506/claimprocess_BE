package com.hcl.claimprocessing.exception;

public class PolicyNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public PolicyNotExistException(String message) {
		super(message);
	}
}
