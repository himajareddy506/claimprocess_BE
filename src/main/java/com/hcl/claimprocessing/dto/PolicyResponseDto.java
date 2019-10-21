package com.hcl.claimprocessing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyResponseDto {

	private Integer statusCode;
	private String message;
}
