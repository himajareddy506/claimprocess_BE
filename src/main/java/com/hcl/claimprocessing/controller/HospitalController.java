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

import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.HospitalService;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to get the list of hospitals
 * 
 * 
 * @author Jyoshna
 */
@RestController
@RequestMapping("/api/v1/hospitals")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class HospitalController {

	@Autowired
	HospitalService hospitalService;

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	/**
	 * This method is used to get the list of hospitals .
	 * 
	 * @param noparams
	 * @exception HOSPITAL_INFO_NOT_EXIST
	 * @return This method returns the list of hospitals
	 * @throws HospitalNotFoundException
	 */

	@GetMapping("/")
	public ResponseEntity<List<Hospital>> getHospitalList() throws HospitalNotFoundException {
		logger.info("Inside Get Hospital List");
		Optional<List<Hospital>> hospitalList = hospitalService.getHospitals();
		if (!hospitalList.isPresent()) {
			throw new HospitalNotFoundException(ClaimConstants.HOSPITAL_INFO_NOT_EXIST);
		}
		return new ResponseEntity<>(hospitalList.get(), HttpStatus.OK);

	}

}
