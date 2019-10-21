package com.hcl.claimprocessing.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.PolicyResponseDto;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.service.PolicyService;
import com.hcl.claimprocessing.utils.ClaimConstants;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class PolicyController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	PolicyService policyService;

	@PostMapping("/policy")
	public ResponseEntity<PolicyResponseDto> search(@RequestParam("policyId") Long policyId) {
		logger.info("inside search policy controller");
		if (policyId == null) {
			throw new PolicyNotFoundException(ClaimConstants.POLICY_ID_MANDATORY_EXCEPTION);
		}
		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		Optional<Policy> policyOptional = policyService.search(policyId);
		if (policyOptional.isPresent()) {
			policyResponseDto.setMessage(ClaimConstants.POLICY_ID_EXIST);
			policyResponseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			policyResponseDto.setMessage(ClaimConstants.POLICY_NOT_FOUND_EXCEPTION);
			policyResponseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return new ResponseEntity<>(policyResponseDto, HttpStatus.OK);
	}
}
