package com.hcl.claimprocessing.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.dto.ClaimUpdateRequestDto;
import com.hcl.claimprocessing.dto.CommonResponse;
import com.hcl.claimprocessing.entity.Claim;
import com.hcl.claimprocessing.exception.ClaimNotFoundException;
import com.hcl.claimprocessing.exception.InfoException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.exception.ValidInputException;
import com.hcl.claimprocessing.service.ClaimService;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to avail claim by the user. The claim Status can be
 * updated by the Approver/PO The List of claim under an Approver can be
 * retrieved
 * 
 * @author Subashri
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ClaimController {
	@Autowired
	ClaimService claimService;
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	/**
	 * This method is used to avail claim by the user who have policy/insurance .
	 * 
	 * @param policyId,admitDate,dischargeDate,hospitalName,totalAmount,detailsOfDischargeSummary,natureOfAilment,diagnosis
	 * @exception ValidInputException,InfoExistException,PolicyNotExistException,UserNotExistException
	 * @return It returns ClaimResponseDto
	 * @throws ClaimNotFoundException
	 */
	@PostMapping("/claims")
	public ResponseEntity<ClaimResponseDto> applyClaim(@Valid @RequestBody ClaimRequestDto claimRequestDto,
			BindingResult result) throws InfoException, PolicyNotExistException, UserNotExistException,
			ClaimNotFoundException, ValidInputException {
		logger.info(ClaimConstants.APPLY_CLAIM_CONTROLLER);
		if (result.hasErrors()) {
			throw new ValidInputException(result.getFieldError().getField() + ClaimConstants.SEPERATOR
					+ result.getFieldError().getDefaultMessage());
		}
		Optional<ClaimResponseDto> claimInfo = claimService.applyClaim(claimRequestDto);
		if (!claimInfo.isPresent()) {
			throw new ClaimNotFoundException(ClaimConstants.CLAIM_INFO_NOT_EXIST);
		}
		ClaimResponseDto claimResponse = claimInfo.get();
		return new ResponseEntity<>(claimResponse, HttpStatus.CREATED);
	}

	/**
	 * This method is used to update the claimInfo of the user who have
	 * policy/insurance .
	 * 
	 * @param claimId,reason,claimStatus,userId
	 * @return It returns CommonResponse
	 * @exception ClaimNotFoundException,UserNotExistException,ValidInputException
	 * @throws InfoException
	 * @throws ValidInputException
	 */

	@PutMapping("/")
	public ResponseEntity<CommonResponse> updateClaimInfo(@Valid @RequestBody ClaimUpdateRequestDto claimUpdateInfo,
			BindingResult result)
			throws UserNotExistException, ClaimNotFoundException, InfoException, ValidInputException {

		logger.info(ClaimConstants.UPDATE_CLAIM_CONTROLLER);
		if (result.hasErrors()) {
			throw new ValidInputException(result.getFieldError().getField() + ClaimConstants.SEPERATOR
					+ result.getFieldError().getDefaultMessage());
		}
		CommonResponse response = new CommonResponse();
		Optional<Claim> claimInfo = claimService.updateClaimInfo(claimUpdateInfo);
		if (!claimInfo.isPresent()) {
			throw new ClaimNotFoundException(ClaimConstants.CLAIM_INFO_NOT_EXIST);
		}
		response.setMessage(ClaimConstants.CLAIM_UPDATE_SUCCESS);
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method is used to avail claim info of the logged-in use Approver/Senior
	 * Approver .
	 * 
	 * @param userId,pageNumber
	 * @exception UserNotExistException,ClaimNotFoundException,UserException
	 * @return It returns <List<Claim>
	 */

	@GetMapping("/")
	public ResponseEntity<List<Claim>> getClaimList(@RequestParam("roleId") Integer roleId,
			@RequestParam("pageNumber") Integer pageNumber)
			throws UserNotExistException, ClaimNotFoundException, UserException {
		logger.info(ClaimConstants.GET_CLAIM_CONTROLLER);
		if (pageNumber == null || pageNumber < 0) {
			throw new UserException(ClaimConstants.INVALID_INPUTS);
		}
		Optional<List<Claim>> claimList = claimService.getClaimList(roleId, pageNumber);
		if (!claimList.isPresent()) {
			throw new ClaimNotFoundException(ClaimConstants.CLAIM_INFO_NOT_EXIST);
		}
		return new ResponseEntity<>(claimList.get(), HttpStatus.OK);

	}
}
