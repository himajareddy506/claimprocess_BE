package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.controller.HospitalController;
import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.repository.HospitalRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Jyoshna
 */

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	/**
	 * This method is used to get the list of hospitals .
	 * 
	 * @param noparams
	 * @exception HOSPITAL_INFO_NOT_EXIST
	 * @return This method returns the list of hospitals
	 * @throws HospitalNotFoundException
	 */

	@Override
	public Optional<List<Hospital>> getHospitals() {
		logger.info(ClaimConstants.GET_HOSPITAL_LIST_SERVICE);
		List<Hospital> hospitalList = hospitalRepository.findAll();
		return Optional.of(hospitalList);
	}

}
