package com.hcl.claimprocessing.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.validation.FieldError;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.dto.UserResponseDto;
import com.hcl.claimprocessing.entity.User;
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
	User user=new User();
	Optional<User> userInfo;
	BindingResult bindingResult;
	FieldError fieldError;
	@Before
	public void initiateDate() {
		user.setUserId(1);
		user.setEmailId("subashri@gmail.com");
		user.setFirstName("Subashri");
		user.setLastName("S");
		user.setMobileNumber(956683871L);	
		user.setPassCode("12345");
		user.setRoleId(1);
		userDto.setEmailId("subashri@gmail.com");
		userDto.setPassCode("12345");
	}
	@Test
	public void testLoginUser() throws UserNotExistException, LoginDeniedException, ValidInputException  {
		userInfo=Optional.of(user);
		Mockito.when(userService.loginUser(Mockito.any())).thenReturn(userInfo);
		ResponseEntity<UserResponseDto> userInfo=userController.loginUser(userDto, bindingResult);
		assertNotNull(userInfo);
		assertEquals(200, userInfo.getStatusCode().value());
		
	}
	
	
}
