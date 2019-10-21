package com.hcl.claimprocessing.service;

import java.util.Optional;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.LoginDeniedException;
import com.hcl.claimprocessing.exception.UserNotExistException;

public interface UserService {
	Optional<User> loginUser(UserRequestDto loginRequestDto) throws UserNotExistException,LoginDeniedException;
	
}
