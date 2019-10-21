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

public class Hospital {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer hospitalId;
		private String hospitalName;
		private String networkStatus;
}