package com.hcl.claimprocessing.service;

import java.util.Optional;

import com.hcl.claimprocessing.dto.ClaimRequestDto;
import com.hcl.claimprocessing.dto.ClaimResponseDto;
import com.hcl.claimprocessing.exception.InfoExistException;
import com.hcl.claimprocessing.exception.PolicyNotExistException;
import com.hcl.claimprocessing.exception.UserNotExistException;

public interface ClaimService {
		public Optional<ClaimResponseDto> applyClaim(ClaimRequestDto claimRequestDto) throws InfoExistException,PolicyNotExistException,UserNotExistException;
}
