package com.hcl.claimprocessing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.entity.Claim;
import com.hcl.claimprocessing.entity.Policy;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.ClaimNotFoundException;
import com.hcl.claimprocessing.exception.InfoExistException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.repository.ClaimRepository;
import com.hcl.claimprocessing.repository.PolicyRepository;
import com.hcl.claimprocessing.repository.UserRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class is used to avail claim by the user.
 * 
 * @author Subashri
 */

@Service
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	ClaimRepository claimRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PolicyRepository policyRepository;

	/**
	 * This method is used to avail claim by the user who have policy/insurance .
	 * 
	 * @param policyId,admitDate,dischargeDate,hospitalName,totalAmount,detailsOfDischargeSummary,natureOfAilment,diagnosis
	 * @exception InfoExistException,PolicyNotExistException,UserNotExistException
	 * 
	 */

	@Override
	public Optional<ClaimResponseDto> applyClaim(ClaimRequestDto claimRequestDto)
			throws InfoExistException, PolicyNotExistException, UserNotExistException {
		ClaimResponseDto claimResponse = new ClaimResponseDto();
		Claim claim = new Claim();
		Optional<User> user = null;
		BeanUtils.copyProperties(claimRequestDto, claim);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate admitDate = LocalDate.parse(claimRequestDto.getAdmitDate(), formatter);
		LocalDate dischargeDate = LocalDate.parse(claimRequestDto.getDischargeDate(), formatter);
		Optional<Claim> claimInfo = claimRepository.findByAdmitDateAndDischargeDate(admitDate, dischargeDate);
		if (claimInfo.isPresent()) {
			throw new InfoExistException(ClaimConstants.CLAIM_INFO_EXIST);
		}
		long policyNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		claim.setClaimId(policyNumber);
		claim.setAdmitDate(admitDate);
		claim.setDischargeDate(dischargeDate);
		claim.setClaimAmount(claimRequestDto.getTotalAmount());

		claim.setSeniorApproverClaimStatus(ClaimConstants.PENDING_STATUS);
		claim.setJuniorApproverClaimStatus(ClaimConstants.PENDING_STATUS);

		claimRepository.save(claim);
		Optional<Policy> policy = policyRepository.findById(claimRequestDto.getPolicyId());
		if (!policy.isPresent()) {
			throw new PolicyNotExistException(ClaimConstants.POLICY_INFO_DOESNOT_EXIST);
		}

		user = userRepository.findById(policy.get().getUserId());
		if (!user.isPresent()) {
			throw new UserNotExistException(ClaimConstants.USER_NOT_FOUND);
		}

		claimResponse.setClaimId(claim.getClaimId());
		claimResponse.setEmailId(user.get().getEmailId());
		claimResponse.setFirstName(user.get().getFirstName());
		claimResponse.setLastName(user.get().getLastName());
		claimResponse.setPolicyNumber(policy.get().getPolicyId());
		claimResponse.setMessage(ClaimConstants.CLAIM_APPLIED);
		claimResponse.setStatusCode(HttpStatus.CREATED.value());

		return Optional.of(claimResponse);
	}

	/**
	 * This method is used to avail claim info of the logged-in use Approver/Senior
	 * Approver .
	 * 
	 * @param userId,pageNumber
	 * @exception UserNotExistException,ClaimNotFoundException
	 * @return Optional<List<Claim>>
	 */
	@Override
	public Optional<List<Claim>> getClaimList(Integer userId, Integer pageNumber)
			throws UserNotExistException, ClaimNotFoundException {

		Optional<Claim> claims = claimRepository.findByUserId(userId);
		if (!claims.isPresent()) {
			throw new ClaimNotFoundException(ClaimConstants.CLAIM_INFO_NOT_EXIST);
		}
		Optional<User> user = userRepository.findById(claims.get().getUserId());
		if (!user.isPresent()) {
			throw new UserNotExistException(ClaimConstants.USER_NOT_FOUND);
		}
		Integer role = user.get().getRoleId();
		List<Claim> claimInfos = claimRepository.findAll();
		List<Claim> claimResponse = new ArrayList<>();
		if (role == ClaimConstants.seniorApprover) {
			claimInfos.forEach(claimInfo -> {
				if (claimInfo.getJuniorApproverClaimStatus().equals(ClaimConstants.ESCALATED_STATUS)) {
					claimResponse.add(claimInfo);
				}
			});

		}
		if (role == ClaimConstants.Approver) {
			claimInfos.forEach(claimInfo -> {
				if (claimInfo.getJuniorApproverClaimStatus().equals(ClaimConstants.ESCALATED_STATUS)) {
					claimResponse.add(claimInfo);
				}
			});
		}

		return Optional.of(claimResponse);
	}

}
