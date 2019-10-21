package com.hcl.claimprocessing.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private String message;
	private Integer code;
	public ErrorResponse(String message, int value) {
		this.message=message;
		this.code=value;
	}

}
