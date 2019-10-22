package com.hcl.claimprocessing.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.controller.PolicyController;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.repository.PolicyRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to check whether the user already has the policy
 * 
 * @author Jyoshna
 *
 */

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	/**
	 * This method is used to check whether the user has the policy inorder to claim
	 * 
	 * @param policyId
	 * @return It returns Optional<Policy>
	 * @exception POLICY_NOT_FOUND_EXCEPTION
	 *
	 */

	@Override
	public Optional<Policy> search(Long policyId) {
		logger.info("Inside policy Search");
		Optional<Policy> policy = policyRepository.findById(policyId);
		if (!policy.isPresent())
			throw new PolicyNotFoundException(ClaimConstants.POLICY_NOT_FOUND_EXCEPTION);
		return policy;
	}

}
