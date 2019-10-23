package com.hcl.claimprocessing.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.dto.UserResponseDto;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.LoginDeniedException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.exception.ValidInputException;
import com.hcl.claimprocessing.service.UserService;
import com.hcl.claimprocessing.utils.ClaimConstants;

/**
 * This class will be used for the userLogin based on the Role
 * 
 * @author Subashri
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {
	@Autowired
	UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);
	/**
	 * This method will be used for the userLogin based on the Role
	 * 
	 * @param emailId , passcode
	 * @return It returns UserResponseDto
	 * @throws ValidInputException 
	 * @exception It handles
	 *               ValidInputException,UserNotExistException,LoginDeniedException
	 */

	@PostMapping("/")
	public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody UserRequestDto loginRequestDto, BindingResult result)
			throws UserNotExistException, LoginDeniedException, ValidInputException {
		logger.info("inside the login");
		if(result.hasErrors()) {
			throw new ValidInputException(result.getFieldError().getField() +":"+result.getFieldError().getDefaultMessage());
		}
		UserResponseDto userResponse = new UserResponseDto();
		Optional<User> user = userService.loginUser(loginRequestDto);
		user.ifPresent(users -> {
			userResponse.setUserId(users.getUserId());
			userResponse.setRoleId(user.get().getRoleId());
			userResponse.setMessage(ClaimConstants.LOGIN_SUCCESS);
			userResponse.setStatusCode(HttpStatus.OK.value());
		});
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
}
