package com.hcl.claimprocessing.dto;

import java.time.LocalDate;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ClaimRequestDto {
		@NotNull
		private Long policyNumber;
		@NotNull
		private LocalDate admitDate;
		@NotNull
		private LocalDate dischargeDate;
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
}
