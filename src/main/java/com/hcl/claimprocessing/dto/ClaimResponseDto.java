package com.hcl.claimprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimResponseDto {
	private String firstName;
	private String lastName;
	private String emailId;
	private Long policyNumber;
	private Long claimId;
	private String message;
	private Integer statusCode;
}
