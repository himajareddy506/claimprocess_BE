package com.hcl.claimprocessing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Ailments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ailmentId;
	private String ailmentName;
	private Double maxAmount;
	private Integer diagnosisId;
}