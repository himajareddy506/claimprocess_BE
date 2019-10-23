package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Diagnosis;
import com.hcl.claimprocessing.repository.DiagnosisRepository;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Jyoshna
 */

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

	@Autowired
	DiagnosisRepository diagnosisRepository;

	@Override
	public Optional<List<Diagnosis>> getDiagnosis() {
		List<Diagnosis> diagnosisList = diagnosisRepository.findAll();
		return Optional.of(diagnosisList);
	}

}
