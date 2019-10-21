package com.hcl.claimprocessing.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.dto.UserResponseDto;
import com.hcl.claimprocessing.exception.LoginDeniedException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.exception.ValidInputException;
import com.hcl.claimprocessing.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	UserServiceImpl userService;
	@InjectMocks
	UserController userController;
	UserRequestDto userDto=new UserRequestDto();
	BindingResult result;
	@Before
	public void initiateDate() {
		userDto.setEmailId("himaja.reddy@hcl.com");
		userDto.setPassCode("12345");
	}
	@Test
	public void testLoginUser() throws ValidInputException, UserNotExistException, LoginDeniedException {
		ResponseEntity<UserResponseDto> userInfo=userController.loginUser(userDto, result);
		assertNotNull(userInfo);
		assertEquals(200, userInfo.getStatusCode().value());
		
	}
}
