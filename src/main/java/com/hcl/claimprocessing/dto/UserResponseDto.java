package com.hcl.claimprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
	private Integer statusCode;
	private String message;
	private Integer userId;
	private Integer roleId;
}
