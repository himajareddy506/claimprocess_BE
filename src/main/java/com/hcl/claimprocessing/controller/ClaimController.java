package com.hcl.claimprocessing.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.exception.InfoExistException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.exception.ValidInputException;
import com.hcl.claimprocessing.service.ClaimService;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Subashri
 */
@RestController
@RequestMapping("/api/v1/claims")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ClaimController {
	@Autowired
	ClaimService claimService;

	/**
	 * This method is used to avail claim by the user who have policy/insurance .
	 * 
	 * @param policyId,admitDate,dischargeDate,hospitalName,totalAmount,detailsOfDischargeSummary,natureOfAilment,diagnosis
	 * @exception ValidInputException,InfoExistException,PolicyNotExistException,UserNotExistException
	 */
	@PostMapping("/")
	public ResponseEntity<ClaimResponseDto> applyClaim(@RequestBody ClaimRequestDto claimRequestDto,
			BindingResult result)
			throws ValidInputException, InfoExistException, PolicyNotExistException, UserNotExistException {
		if (result.hasErrors()) {
			throw new ValidInputException(
					result.getFieldError().getField() + ":" + result.getFieldError().getDefaultMessage());
		}
		Optional<ClaimResponseDto> claimInfo = claimService.applyClaim(claimRequestDto);
		return new ResponseEntity<>(claimInfo.get(), HttpStatus.CREATED);
	}
}
