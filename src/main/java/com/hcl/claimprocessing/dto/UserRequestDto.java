package com.hcl.claimprocessing.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
	@Email
	@NotNull
	@Column(unique = true)
	private String emailId;
	@NotNull
	private String passCode;
}
