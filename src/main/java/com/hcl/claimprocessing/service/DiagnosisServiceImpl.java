package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.controller.ClaimController;
import com.hcl.claimprocessing.entity.Diagnosis;
import com.hcl.claimprocessing.repository.DiagnosisRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Jyoshna
 */

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

	@Autowired
	DiagnosisRepository diagnosisRepository;
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@Override
	public Optional<List<Diagnosis>> getDiagnosis() {
		logger.info(ClaimConstants.GET_DIAGNOSIS_LIST_SERVICE);
		List<Diagnosis> diagnosisList = diagnosisRepository.findAll();
		return Optional.of(diagnosisList);
	}

}
