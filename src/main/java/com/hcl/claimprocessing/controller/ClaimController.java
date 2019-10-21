package com.hcl.claimprocessing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.entity.Claim;
import com.hcl.claimprocessing.exception.ClaimNotFoundException;
import com.hcl.claimprocessing.exception.InfoExistException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.exception.ValidInputException;
import com.hcl.claimprocessing.service.ClaimService;
import com.hcl.claimprocessing.utils.ClaimConstants;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ClaimController {
	@Autowired
	ClaimService claimService;

	@PostMapping("/claims")
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

	@GetMapping("/")
	public ResponseEntity<List<Claim>> getClaimList(@RequestParam("userId") Integer userId,
			@RequestParam("pageNumber") Integer pageNumber)
			throws UserNotExistException, ClaimNotFoundException, UserException {
		if (pageNumber == null || pageNumber < 0) {
			throw new UserException(ClaimConstants.INVALID_INPUTS);
		}
		Optional<List<Claim>> claimList = claimService.getClaimList(userId, pageNumber);
		if (!claimList.isPresent()) {
			throw new ClaimNotFoundException(ClaimConstants.CLAIM_INFO_NOT_EXIST);
		}
		return new ResponseEntity<>(claimList.get(), HttpStatus.OK);
	}

}
