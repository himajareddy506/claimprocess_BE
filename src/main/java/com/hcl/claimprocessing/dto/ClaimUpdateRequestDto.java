package com.hcl.claimprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimUpdateRequestDto {
	private Long claimId;
	private String reason;
	private String claimStatus;
	private Integer userId;
}
