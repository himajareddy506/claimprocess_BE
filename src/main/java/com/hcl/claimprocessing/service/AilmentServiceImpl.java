package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.repository.AilmentRepository;

/**
 * This class is used to get the list of ailments based on selected diagnosis
 * 
 * 
 * @author Jyoshna
 */
@Service
public class AilmentServiceImpl implements AilmentService {

	@Autowired
	AilmentRepository ailmentRepository;

	private static final Logger logger = LoggerFactory.getLogger(AilmentServiceImpl.class);

	/**
	 * This method is used to get the list of ailments based on selected diagnosis .
	 * 
	 * @param diagnosisId
	 * @return This method returns the list of ailments
	 *
	 */

	@Override
	public Optional<List<Ailments>> getAilmentList(Integer diagnosisId) {
		logger.info("inside ailment service");
		return ailmentRepository.findByDiagnosisId(diagnosisId);

	}

}
