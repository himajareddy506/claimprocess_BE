package com.hcl.claimprocessing.service;

import java.util.Optional;

import com.hcl.claimprocessing.entity.Policy;

public interface PolicyService {

	Optional<Policy> search(Long policyId);
}
