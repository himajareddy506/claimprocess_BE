package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Hospital;
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

	@Override
	public Optional<List<Hospital>> getHospitals() {
		List<Hospital> hospitalList = hospitalRepository.findAll();
		return Optional.of(hospitalList);
	}

}
