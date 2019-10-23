package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.controller.ClaimController;
import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.repository.AilmentRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

@Service
public class AilmentServiceImpl implements AilmentService {

	@Autowired
	AilmentRepository ailmentRepository;
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@Override
	public Optional<List<Ailments>> getAilmentList(Integer diagnosisId) {
		logger.info(ClaimConstants.GET_AILMENT_LIST_SERVICE);
		return ailmentRepository.findByDiagnosisId(diagnosisId);

	}

}
