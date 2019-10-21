package com.hcl.claimprocessing.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import com.hcl.claimprocessing.dto.CommonResponse;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.exception.PolicyNotFoundException;
import com.hcl.claimprocessing.service.PolicyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PolicyControllerTest {
	@Mock
	PolicyServiceImpl policyService;
	@InjectMocks
	PolicyController policyController;
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

	@Test(expected = PolicyNotFoundException.class)
	public void testSearchPolicyNull() {
		policyId = null;
		ResponseEntity<CommonResponse> response = policyController.search(policyId);
		assertNotNull(response);
	}

	@Test
	public void testSearch() {
		policyInfo = Optional.of(policy);
		Mockito.when(policyService.search(Mockito.anyLong())).thenReturn(policyInfo);
		ResponseEntity<CommonResponse> response = policyController.search(policyId);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
	}
	@Test
	public void testSearchNull() {
		policyInfo = Optional.ofNullable(null);
		Mockito.when(policyService.search(Mockito.anyLong())).thenReturn(policyInfo);
		ResponseEntity<CommonResponse> response = policyController.search(policyId);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
	}
}
