package com.hcl.claimprocessing.exception;

public class ErrorResponse {

	private String message;
	private int statusCode;
	private String uri;

	public ErrorResponse(String message, int statusCode, String uri) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.uri = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ErrorResponse() {
		super();
	}

}
