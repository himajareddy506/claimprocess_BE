package com.hcl.claimprocessing.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.CommonResponse;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.service.PolicyService;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to check the policy of users
 * 
 * @author Jyoshhna
 *
 */
@RestController
@RequestMapping("/api/v1/policy")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class PolicyController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	PolicyService policyService;

	/**
	 * This method is used to check whether the user has the policy inorder to claim
	 * 
	 * @param policyId
	 * @return This method returns the message of whether policy exists or not
	 * @exception POLICY_ID_MANDATORY_EXCEPTION,POLICY_NOT_FOUND_EXCEPTION
	 * @throws PolicyNotFoundException
	 */

	@PostMapping("/")
	public ResponseEntity<CommonResponse> search(@RequestParam("policyId") Long policyId) {
		logger.info("inside search policy controller");
		if (policyId == null) {
			throw new PolicyNotFoundException(ClaimConstants.POLICY_ID_MANDATORY_EXCEPTION);
		}
		CommonResponse policyResponse = new CommonResponse();
		Optional<Policy> policyOptional = policyService.search(policyId);
		if (policyOptional.isPresent()) {
			policyResponse.setMessage(ClaimConstants.POLICY_ID_EXIST);
			policyResponse.setStatusCode(HttpStatus.OK.value());
		} else {
			policyResponse.setMessage(ClaimConstants.POLICY_NOT_FOUND_EXCEPTION);
			policyResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return new ResponseEntity<>(policyResponse, HttpStatus.OK);
	}
}
