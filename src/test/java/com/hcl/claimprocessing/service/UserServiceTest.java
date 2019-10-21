package com.hcl.claimprocessing.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.claimprocessing.dto.UserRequestDto;
import com.hcl.claimprocessing.entity.User;
import com.hcl.claimprocessing.exception.LoginDeniedException;
import com.hcl.claimprocessing.exception.UserNotExistException;
import com.hcl.claimprocessing.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;
	UserRequestDto userDto=new UserRequestDto();
	User user=new User();
	Optional<User> userInfo;
	@Before
	public void initiateData() {
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
	public void testLoginUser() throws UserNotExistException, LoginDeniedException {
		userInfo=Optional.of(user);
		Mockito.when(userRepository.findByEmailIdAndPassCode(Mockito.anyString(), Mockito.anyString())).thenReturn(userInfo);
		Optional<User> userLoginInfo=userServiceImpl.loginUser(userDto);
		assertNotNull(userLoginInfo);
	}
	@Test(expected =UserNotExistException.class )
	public void testLoginUserNotPresent() throws UserNotExistException, LoginDeniedException {
		userInfo=Optional.ofNullable(null);
		Mockito.when(userRepository.findByEmailIdAndPassCode(Mockito.anyString(), Mockito.anyString())).thenReturn(userInfo);
		Optional<User> userLoginInfo=userServiceImpl.loginUser(userDto);
		assertNotNull(userLoginInfo);
	}
	@Test(expected=LoginDeniedException.class)
	public void testLoginRoleNotPresent() throws UserNotExistException, LoginDeniedException {
		userInfo=Optional.of(user);
		userInfo.get().setRoleId(3);
		Mockito.when(userRepository.findByEmailIdAndPassCode(Mockito.anyString(), Mockito.anyString())).thenReturn(userInfo);
		Optional<User> userLoginInfo=userServiceImpl.loginUser(userDto);
		assertNotNull(userLoginInfo);
	}
}
