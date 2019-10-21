package com.hcl.claimprocessing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.repository.PolicyRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	@Override
	public Optional<Policy> search(Long policyId) {
		Optional<Policy> policy = policyRepository.findById(policyId);
		if (!policy.isPresent())
			throw new PolicyNotFoundException(ClaimConstants.POLICY_NOT_FOUND_EXCEPTION);
		return policy;
	}

}
