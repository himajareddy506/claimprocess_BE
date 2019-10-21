package com.hcl.claimprocessing.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.repository.PolicyRepository;

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {
	@Mock
	PolicyRepository policyRepository;
	@InjectMocks
	PolicyServiceImpl policyService;
	Long policyId;
	Policy policy = new Policy();
	Optional<Policy> policyInfo;

	@Before
	public void initData() {
		policyId = 123456789L;
		policy.setAilmentId(1);
		policy.setPolicyId(123456789L);
		policy.setUserId(2);

	}
	@Test
	public void testSearch() {
		policyInfo=Optional.of(policy);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(policyInfo);
		Optional<Policy> policyDetail=policyService.search(policyId);
		assertNotNull(policyDetail);
	}
	@Test(expected = PolicyNotFoundException.class)
	public void testSearchNull() {
		policyInfo=Optional.ofNullable(null);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(policyInfo);
		Optional<Policy> policyDetail=policyService.search(policyId);
		assertNotNull(policyDetail);
	}
}
