package com.hcl.claimprocessing.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.hcl.claimprocessing.service.ClaimServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClaimControllerTest {
	@Mock
	ClaimServiceImpl claimService;
	@InjectMocks
	ClaimController claimController;
	ClaimRequestDto claimRequestDto = new ClaimRequestDto();
	ClaimResponseDto claimResponse = new ClaimResponseDto();
	ClaimUpdateRequestDto claimUpdateInfo = new ClaimUpdateRequestDto();
	Claim claim = new Claim();
	Optional<Claim> claimData;
	Optional<ClaimResponseDto> claimInfo;
	BindingResult result;
	List<Claim> claimList = new ArrayList<>();
	Optional<List<Claim>> claimListInfo;
	Integer pageNumber = 0;

	@Before
	public void initiateData() {

		claimRequestDto.setAdmitDate("2019/06/21");
		claimRequestDto.setDischargeDate("2019/06/23");
		claimRequestDto.setClaimStatus("Pending");
		claimRequestDto.setDetailsOfDischargeSummary("discharged");
		claimRequestDto.setDiagnosis("Respiratory");
		claimRequestDto.setHospitalName("Apollo");
		claimRequestDto.setNatureOfAilment("cough");
		claimRequestDto.setPolicyId(123456789L);
		claimRequestDto.setReason("Claim");
		claimRequestDto.setTotalAmount(8000.0);
		claimResponse.setClaimId(1983647L);
		claimResponse.setEmailId("subashri@gmail.com");
		claimResponse.setFirstName("Subashri");
		claimResponse.setLastName("S");
		claimResponse.setMessage("Claim Applied Successfully");
		claimResponse.setPolicyNumber(123456789L);
		claimResponse.setStatusCode(200);
		claimUpdateInfo.setClaimId(1983647L);
		claimUpdateInfo.setClaimStatus("Pending");
		claimUpdateInfo.setReason("Valid");
		claimUpdateInfo.setRoleId(1);
		claim.setAdmitDate(LocalDate.parse("2019-05-04"));
		claim.setClaimAmount(2000.0);
		claim.setClaimId(345678L);
		claim.setDetailsOfDischargeSummary("Discharged");
		claim.setDischargeDate(LocalDate.parse("2019-05-07"));
		claim.setEligiblityAmount(1500.0);
		claim.setHospitalId(1);
		claim.setJuniorApprovedBy("Keshav");
		claim.setSeniorApprovedBy("Kabhil");
		claim.setJuniorApproverClaimStatus("Pending");
		claim.setPolicyId(1234569L);
		claim.setReason("Valid");
		claim.setRoleId(1);
		claimList.add(claim);
		pageNumber = 1;

	}

	@Test
	public void testApplyClaim() throws InfoException, PolicyNotExistException, UserNotExistException,
			ValidInputException, ClaimNotFoundException {
		claimInfo = Optional.of(claimResponse);
		Mockito.when(claimService.applyClaim(Mockito.any())).thenReturn(claimInfo);
		ResponseEntity<ClaimResponseDto> claimResponses = claimController.applyClaim(claimRequestDto, result);
		assertNotNull(claimResponses);
	}

	@Test(expected = ClaimNotFoundException.class)
	public void testNegativeApplyClaim() throws InfoException, PolicyNotExistException, UserNotExistException,
			ValidInputException, ClaimNotFoundException {
		claimResponse = null;
		claimInfo = Optional.ofNullable(claimResponse);
		Mockito.when(claimService.applyClaim(Mockito.any())).thenReturn(claimInfo);
		ResponseEntity<ClaimResponseDto> claimResponses = claimController.applyClaim(claimRequestDto, result);
		assertNotNull(claimResponses);
	}

	@Test
	public void testUpdateClaimInfo()
			throws UserNotExistException, ClaimNotFoundException, ValidInputException, InfoException {
		claimData = Optional.of(claim);
		Mockito.when(claimService.updateClaimInfo(claimUpdateInfo)).thenReturn(claimData);
		ResponseEntity<CommonResponse> response = claimController.updateClaimInfo(claimUpdateInfo, result);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
	}

	@Test(expected = ClaimNotFoundException.class)
	public void testNegativeUpdateClaimInfo()
			throws UserNotExistException, ClaimNotFoundException, ValidInputException, InfoException {
		claim = null;
		claimData = Optional.ofNullable(claim);
		Mockito.when(claimService.updateClaimInfo(claimUpdateInfo)).thenReturn(claimData);
		ResponseEntity<CommonResponse> response = claimController.updateClaimInfo(claimUpdateInfo, result);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testGetClaimList() throws UserNotExistException, ClaimNotFoundException, UserException {
		claimListInfo = Optional.of(claimList);
		Mockito.when(claimService.getClaimList(Mockito.anyInt(), Mockito.any())).thenReturn(claimListInfo);
		ResponseEntity<List<Claim>> claimResponseList = claimController.getClaimList(claim.getRoleId(), pageNumber);
		assertNotNull(claimResponseList);
	}

	@Test(expected = ClaimNotFoundException.class)
	public void testNegativeGetClaimList() throws UserNotExistException, ClaimNotFoundException, UserException {
		claimList = null;
		claimListInfo = Optional.ofNullable(claimList);
		Mockito.when(claimService.getClaimList(Mockito.anyInt(), Mockito.any())).thenReturn(claimListInfo);
		ResponseEntity<List<Claim>> claimResponseList = claimController.getClaimList(claim.getRoleId(), pageNumber);
		assertNotNull(claimResponseList);
	}

	@Test(expected = UserException.class)
	public void testGetClaimIdNull() throws UserNotExistException, ClaimNotFoundException, UserException {
		claimListInfo = Optional.of(claimList);
		pageNumber = -1;
		ResponseEntity<List<Claim>> claimResponseList = claimController.getClaimList(claim.getRoleId(), pageNumber);
		assertNotNull(claimResponseList);

	}

	@Test(expected = UserException.class)
	public void testNegativeGetClaimIdNull() throws UserNotExistException, ClaimNotFoundException, UserException {
		claimListInfo = Optional.of(claimList);
		pageNumber = null;
		ResponseEntity<List<Claim>> claimResponseList = claimController.getClaimList(claim.getRoleId(), pageNumber);
		assertNotNull(claimResponseList);

	}
}
