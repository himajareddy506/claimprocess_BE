package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.repository.HospitalRepository;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Jyoshna
 */

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

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
		List<Hospital> hospitalList = hospitalRepository.findAll();
		return Optional.of(hospitalList);
	}

}
