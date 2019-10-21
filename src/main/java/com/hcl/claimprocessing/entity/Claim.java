package com.hcl.claimprocessing.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor

public class Claim {

	@Id
	private Long claimId;
	private LocalDate admitDate;
	private LocalDate dischargeDate;
	private String hospitalName;
	private Double claimAmount;
	private String detailsOfDischargeSummary;
	private String reason;
	private Long policyId;
	private String seniorApproverClaimStatus;
	private String juniorApproverClaimStatus;
	private String seniorApprovedBy;
	private String juniorApprovedBy;
    private Integer userId;
    private Double eligiblityAmount;
}
