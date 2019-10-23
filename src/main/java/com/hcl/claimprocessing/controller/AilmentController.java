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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.exception.HospitalNotFoundException;
import com.hcl.claimprocessing.service.AilmentService;
import com.hcl.claimprocessing.utils.ClaimConstants;

@RestController
@RequestMapping("/api/v1/ailments")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class AilmentController {

	@Autowired
	AilmentService ailmentService;

	private static final Logger logger = LoggerFactory.getLogger(AilmentController.class);

	@GetMapping("/{diagnosisId}")
	public ResponseEntity<List<Ailments>> getAilmentList(@PathVariable Integer diagnosisId)
			throws HospitalNotFoundException {
		logger.info("Inside Get Ailments List");
		Optional<List<Ailments>> ailmentList = ailmentService.getAilmentList(diagnosisId);
		if (!ailmentList.isPresent()) {
			throw new HospitalNotFoundException(ClaimConstants.AILMENT_INFO_NOT_EXIST);
		}
		return new ResponseEntity<>(ailmentList.get(), HttpStatus.OK);

	}

}
