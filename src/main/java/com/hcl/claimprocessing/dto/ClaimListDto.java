package com.hcl.claimprocessing.dto;

import java.util.List;

import com.hcl.claimprocessing.entity.Claim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimListDto {
	private List<Claim> claim;
	private String message;
	private Integer statusCode;
	
}
