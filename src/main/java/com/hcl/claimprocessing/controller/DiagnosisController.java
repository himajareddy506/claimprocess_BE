package com.hcl.claimprocessing.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.entity.Diagnosis;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.DiagnosisService;
import com.hcl.claimprocessing.utils.ClaimConstants;

@RestController
@RequestMapping("/api/v1/diagnosis")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class DiagnosisController {
	@Autowired
	DiagnosisService diagnosisService;
	private static final Logger logger = LoggerFactory.getLogger(DiagnosisController.class);

	@GetMapping("/")
	public ResponseEntity<List<Diagnosis>> getDaignosisList() throws HospitalNotFoundException {
		logger.info(ClaimConstants.GET_DIAGNOSIS_LIST_CONTROLLER);
		Optional<List<Diagnosis>> diagnosisList = diagnosisService.getDiagnosis();
		if (!diagnosisList.isPresent()) {
			throw new HospitalNotFoundException(ClaimConstants.DIAGNOSIS_INFO_NOT_EXIST);
		}
		return new ResponseEntity<>(diagnosisList.get(), HttpStatus.OK);

	}
}
