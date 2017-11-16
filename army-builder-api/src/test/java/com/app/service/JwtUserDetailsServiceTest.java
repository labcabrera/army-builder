package com.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.BaseTest;
import com.app.repository.UserRepository;
import com.app.security.domain.Role;
import com.app.security.domain.User;

public class JwtUserDetailsServiceTest extends BaseTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(timeout = 3000)
	public void loadUserByUsernameTest() {
		User user = User.builder().roles(Arrays.asList(Role.USER)).build();
		when(userRepository.findByName(anyString())).thenReturn(user);
		UserDetails fetchedUserDetails = jwtUserDetailsService.loadUserByUsername("random name");

		verify(userRepository, times(1)).findByName(anyString());
		assertNotNull("Fetched user details shouldn't be NULL", fetchedUserDetails);
		assertEquals("Should return appropriate username", user.getName(), fetchedUserDetails.getUsername());
		assertEquals("Should return appropriate password", user.getPassword(), fetchedUserDetails.getPassword());
	}

	@Test(timeout = 3000, expected = UsernameNotFoundException.class)
	public void loadUserWhichNotExistsTest() {
		when(userRepository.findByName(anyString())).thenThrow(UsernameNotFoundException.class);
		jwtUserDetailsService.loadUserByUsername("random name");
		verify(userRepository, times(1)).findByName(anyString());
		verifyNoMoreInteractions();
	}

}
