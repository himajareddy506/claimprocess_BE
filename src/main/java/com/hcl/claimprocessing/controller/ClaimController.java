package com.hcl.claimprocessing.controller;

import java.util.List;
import java.util.Optional;
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
import com.hcl.claimprocessing.exception.InfoExistException;
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
	 */
	@PostMapping("/claims")
	public ResponseEntity<ClaimResponseDto> applyClaim(@RequestBody ClaimRequestDto claimRequestDto,
			BindingResult result)
			throws ValidInputException, InfoExistException, PolicyNotExistException, UserNotExistException {

		logger.info("Inside Apply Claim");
		Optional<ClaimResponseDto> claimInfo = claimService.applyClaim(claimRequestDto);
		return new ResponseEntity<>(claimInfo.get(), HttpStatus.CREATED);
	}

	/**
	 * This method is used to update the claimInfo of the user who have
	 * policy/insurance .
	 * 
	 * @param claimId,reason,claimStatus,userId
	 * @return It returns CommonResponse
	 * @exception ClaimNotFoundException,UserNotExistException,ValidInputException
	 */
	@PutMapping("/")
	public ResponseEntity<CommonResponse> updateClaimInfo(ClaimUpdateRequestDto claimUpdateInfo, BindingResult result)
			throws ValidInputException, UserNotExistException, ClaimNotFoundException {
		logger.info("Inside Update Claim");
		CommonResponse response = new CommonResponse();
		Optional<Claim> claimInfo = claimService.updateClaimInfo(claimUpdateInfo);
		if (claimInfo.isPresent()) {
			response.setMessage(ClaimConstants.CLAIM_UPDATE_SUCCESS);
			response.setStatusCode(HttpStatus.OK.value());
		}
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
	public ResponseEntity<List<Claim>> getClaimList(@RequestParam("userId") Integer userId,
			@RequestParam("pageNumber") Integer pageNumber)
			throws UserNotExistException, ClaimNotFoundException, UserException {
		logger.info("Inside Get Claim List");
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
