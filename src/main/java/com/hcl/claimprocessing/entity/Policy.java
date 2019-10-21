package com.hcl.claimprocessing.entity;

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

public class Policy {
	@Id
	private Long policyId;
	private Integer ailmentId;
	private Integer userId;
}