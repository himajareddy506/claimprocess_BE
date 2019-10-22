package com.hcl.claimprocessing.service;

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
import org.springframework.validation.BindingResult;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.dto.ClaimUpdateRequestDto;
import com.hcl.claimprocessing.entity.Ailments;
import com.hcl.claimprocessing.entity.Claim;
import com.hcl.claimprocessing.entity.Hospital;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.ClaimNotFoundException;
import com.hcl.claimprocessing.exception.InfoException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.repository.AilmentRepository;
import com.hcl.claimprocessing.repository.ClaimRepository;
import com.hcl.claimprocessing.repository.HospitalRepository;import com.hcl.claimprocessing.repository.PolicyRepository;
import com.hcl.claimprocessing.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClaimServiceTest {

	@Mock
	ClaimRepository claimRepository;
	@Mock
	HospitalRepository hospitalRepository;
	@Mock
	AilmentRepository ailmentRepository;
	@Mock
	PolicyRepository policyRepository;
	@Mock
	UserRepository userRepository;
	@InjectMocks
	ClaimServiceImpl claimService;
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
	Hospital hospital=new Hospital();
	Optional<Hospital> hospitalInfo;
	Ailments ailment=new Ailments();
	Optional<Ailments> ailmentInfo;
	Long policyId;
	Policy policy = new Policy();
	Optional<Policy> policyInfo;
	User user=new User();
	Optional<User> userInfo;
	

	@Before
	public void initateData() {
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
		claimUpdateInfo.setUserId(1);
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
		claim.setUserId(2);
		claimList.add(claim);
		pageNumber = 1;
		hospital.setHospitalId(1);
		hospital.setHospitalName("Apollo");
		hospital.setNetworkStatus("In");
		ailment.setAilmentId(1);
		ailment.setAilmentName("Cold");
		ailment.setDiagnosisId(1);
		ailment.setMaxAmount(25000.0);
		policyId = 123456789L;
		policy.setAilmentId(1);
		policy.setPolicyId(123456789L);
		policy.setUserId(2);
		user.setUserId(1);
		user.setEmailId("subashri@gmail.com");
		user.setFirstName("Subashri");
		user.setLastName("S");
		user.setMobileNumber(956683871L);	
		user.setPassCode("12345");
		user.setRoleId(1);
		

	}

	@Test(expected = InfoException.class)
	public void testApplyClaim() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.of(claim);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);
	}
	@Test(expected = InfoException.class)
	public void testApplyClaimHospitalValidation() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.ofNullable(null);
		hospitalInfo=Optional.ofNullable(null);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Mockito.when(hospitalRepository.findByHospitalName(Mockito.anyString())).thenReturn(hospitalInfo);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);
	}
	@Test(expected = InfoException.class)
	public void testApplyClaimAilmentValidation() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.ofNullable(null);
		hospitalInfo=Optional.of(hospital);
		ailmentInfo=Optional.ofNullable(null);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Mockito.when(hospitalRepository.findByHospitalName(Mockito.anyString())).thenReturn(hospitalInfo);
		Mockito.when(ailmentRepository.findByAilmentName(Mockito.anyString())).thenReturn(ailmentInfo);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);
	}
	@Test(expected = PolicyNotExistException.class)
	public void testApplyClaimInfo() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.ofNullable(null);
		hospitalInfo=Optional.of(hospital);
		ailmentInfo=Optional.ofNullable(ailment);
		policyInfo=Optional.ofNullable(null);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Mockito.when(hospitalRepository.findByHospitalName(Mockito.anyString())).thenReturn(hospitalInfo);
		Mockito.when(ailmentRepository.findByAilmentName(Mockito.anyString())).thenReturn(ailmentInfo);
		Mockito.when(claimRepository.save(Mockito.any())).thenReturn(claim);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(policyInfo);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);
	}
	@Test(expected = UserNotExistException.class)
	public void testApplyClaimUserValidation() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.ofNullable(null);
		hospitalInfo=Optional.of(hospital);
		ailmentInfo=Optional.ofNullable(ailment);
		policyInfo=Optional.of(policy);
		userInfo=Optional.ofNullable(null);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Mockito.when(hospitalRepository.findByHospitalName(Mockito.anyString())).thenReturn(hospitalInfo);
		Mockito.when(ailmentRepository.findByAilmentName(Mockito.anyString())).thenReturn(ailmentInfo);
		Mockito.when(claimRepository.save(Mockito.any())).thenReturn(claim);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(policyInfo);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);

	}
	@Test
	public void testApplyClaimSuccess() throws InfoException, PolicyNotExistException, UserNotExistException {
		claimData=Optional.ofNullable(null);
		hospitalInfo=Optional.of(hospital);
		ailmentInfo=Optional.ofNullable(ailment);
		policyInfo=Optional.of(policy);
		userInfo=Optional.ofNullable(user);
		Mockito.when(claimRepository.findByAdmitDateAndDischargeDate(Mockito.any(), Mockito.any())).thenReturn(claimData);
		Mockito.when(hospitalRepository.findByHospitalName(Mockito.anyString())).thenReturn(hospitalInfo);
		Mockito.when(ailmentRepository.findByAilmentName(Mockito.anyString())).thenReturn(ailmentInfo);
		Mockito.when(claimRepository.save(Mockito.any())).thenReturn(claim);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(policyInfo);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Optional<ClaimResponseDto> response=claimService.applyClaim(claimRequestDto);
		assertNotNull(response);

	}
	@Test(expected = UserNotExistException.class)
	public void testUpdateClaimUserValidation() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.ofNullable(null);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Optional<Claim> response=claimService.updateClaimInfo(claimUpdateInfo);
		assertNotNull(response);
	}
	@Test(expected = ClaimNotFoundException.class)
	public void testUpdateClaimInfoValidation() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.of(user);
		claimData=Optional.ofNullable(null);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Mockito.when(claimRepository.findById(Mockito.anyLong())).thenReturn(claimData);
		Optional<Claim> response=claimService.updateClaimInfo(claimUpdateInfo);
		assertNotNull(response);
	}
	@Test
	public void testUpdateClaimRoleValidation() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.of(user);
		claimData=Optional.of(claim);
		user.setRoleId(1);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Mockito.when(claimRepository.findById(Mockito.anyLong())).thenReturn(claimData);
		Optional<Claim> response=claimService.updateClaimInfo(claimUpdateInfo);
		assertNotNull(response);
	}
	@Test
	public void testUpdateClaimApproverValidation() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.of(user);
		claimData=Optional.of(claim);
		user.setRoleId(2);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Mockito.when(claimRepository.findById(Mockito.anyLong())).thenReturn(claimData);
		Optional<Claim> response=claimService.updateClaimInfo(claimUpdateInfo);
		assertNotNull(response);
	}
	@Test(expected = ClaimNotFoundException.class)
	public void testGetClaimListValidation() throws UserNotExistException, ClaimNotFoundException {
		claimData=Optional.ofNullable(null);
		Mockito.when(claimRepository.findByUserId(Mockito.anyInt())).thenReturn(claimData);
		Optional<List<Claim>> claimList=claimService.getClaimList(user.getUserId(), pageNumber);
		assertNotNull(claimList);
	}
	@Test(expected = UserNotExistException.class)
	public void testGetClaimListUserValidation() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.ofNullable(null);
		claimData=Optional.of(claim);	
		Mockito.when(claimRepository.findByUserId(Mockito.anyInt())).thenReturn(claimData);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Optional<List<Claim>> claimList=claimService.getClaimList(user.getUserId(), pageNumber);
		assertNotNull(claimList);
	}
	@Test
	public void testGetClaimList() throws UserNotExistException, ClaimNotFoundException {
		userInfo=Optional.ofNullable(user);
		claimData=Optional.of(claim);	
		Mockito.when(claimRepository.findByUserId(Mockito.anyInt())).thenReturn(claimData);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userInfo);
		Optional<List<Claim>> claimList=claimService.getClaimList(user.getUserId(), pageNumber);
		assertNotNull(claimList);
	}
}
