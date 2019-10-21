package com.hcl.claimprocessing.service;

import java.util.List;
import java.util.Optional;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.dto.ClaimUpdateRequestDto;
import com.hcl.claimprocessing.entity.Claim;
import com.hcl.claimprocessing.exception.ClaimNotFoundException;
import com.hcl.claimprocessing.exception.InfoExistException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserNotExistException;

public interface ClaimService {

	public Optional<ClaimResponseDto> applyClaim(ClaimRequestDto claimRequestDto)
			throws InfoExistException, PolicyNotExistException, UserNotExistException;

<<<<<<< HEAD
	public Optional<List<Claim>> getClaimList(Integer userId, Integer pageNumber) throws UserNotExistException,ClaimNotFoundException;
=======
	public Optional<Claim> updateClaimInfo(ClaimUpdateRequestDto claimUpdateInfo)
			throws UserNotExistException, ClaimNotFoundException;

	public Optional<List<Claim>> getClaimList(Integer userId, Integer pageNumber) throws UserNotExistException;

>>>>>>> 41f15c964ef7ae1cd1026ff00b2bac09481e7308
}
