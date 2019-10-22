package com.hcl.claimprocessing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.LoginDeniedException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.repository.UserRepository;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class will be used for the userLogin based on the Role
 * 
 * @author Subashri
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method will be used for the userLogin based on the Role
	 * 
	 * @param emailId , passcode
	 * @exception It handles UserNotExistException,LoginDeniedException
	 * 
	 */

	@Override
	public Optional<User> loginUser(UserRequestDto loginRequestDto) throws UserNotExistException, LoginDeniedException {

		Optional<User> user = userRepository.findByEmailIdAndPassCode(loginRequestDto.getEmailId(),loginRequestDto.getPassCode());
		if (!user.isPresent()) {
			throw new UserNotExistException(ClaimConstants.INVALID_CREDENTIAL);
		}
		if (!(user.get().getRoleId().equals(ClaimConstants.Approver))
				&& !(user.get().getRoleId().equals(ClaimConstants.seniorApprover))) {
			throw new LoginDeniedException(ClaimConstants.LOGIN_DENIED);
		}

		return user;
	}

}
