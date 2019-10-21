package com.hcl.claimprocessing.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ClaimRequestDto {
		@NotNull
		private Long policyId;
		@NotNull
		private String admitDate;
		@NotNull
		private String dischargeDate;
		@NotNull
		private String hospitalName;
		@NotNull
		private Double totalAmount;
		@NotNull
		private String detailsOfDischargeSummary;
		@NotNull
		private String natureOfAilment;
		@NotNull
		private String diagnosis;
		@NotNull
		private String reason;
		@NotNull
		private String claimStatus;
		
		
}
