package com.hcl.claimprocessing.exception;

public class LoginDeniedException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginDeniedException(String message) {
		super(message);
	}
}
