package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.repository.AilmentRepository;

@Service
public class AilmentServiceImpl implements AilmentService {

	@Autowired
	AilmentRepository ailmentRepository;

	@Override
	public Optional<List<Ailments>> getAilmentList(Integer diagnosisId) {
		return ailmentRepository.findByDiagnosisId(diagnosisId);

	}

}
