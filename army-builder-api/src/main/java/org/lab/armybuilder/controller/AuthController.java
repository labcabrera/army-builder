package org.lab.armybuilder.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.lab.armybuilder.exception.InvalidPasswordException;
import org.lab.armybuilder.exception.UserAlreadyExistsException;
import org.lab.armybuilder.exception.UserNotFoundException;
import org.lab.armybuilder.security.auth.JwtAuthenticationRequest;
import org.lab.armybuilder.security.auth.JwtAuthenticationResponse;
import org.lab.armybuilder.security.auth.JwtUser;
import org.lab.armybuilder.security.auth.JwtUtil;
import org.lab.armybuilder.security.domain.Role;
import org.lab.armybuilder.security.domain.User;
import org.lab.armybuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthController provides signup, signin and token refresh methods
 */
@RestController
@Slf4j
public class AuthController {

	@Value("${auth.header}")
	private String tokenHeader;

	public final static String SIGNUP_URL = "/api/auth/signup";
	public final static String SIGNIN_URL = "/api/auth/signin";
	public final static String REFRESH_TOKEN_URL = "/api/auth/token/refresh";

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;
	private UserService userService;

	/**
	 * Injects AuthenticationManager instance
	 * 
	 * @param authenticationManager to inject
	 */
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Injects JwtUtil instance
	 * 
	 * @param jwtUtil to inject
	 */
	@Autowired
	public void setJwtTokenUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	/**
	 * Injects UserDetailsService instance
	 * 
	 * @param userDetailsService to inject
	 */
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Injects UserService instance
	 * 
	 * @param userService to inject
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Adds new user and returns authentication token
	 * 
	 * @param authenticationRequest request with username, email and password fields
	 * @return generated JWT
	 * @throws AuthenticationException
	 */
	@PostMapping(SIGNUP_URL)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
		throws AuthenticationException {

		final String name = authenticationRequest.getUsername();
		final String email = authenticationRequest.getEmail();
		final String password = authenticationRequest.getPassword();
		log.info("[POST] CREATING TOKEN FOR User " + name);

		if (this.userService.findByName(name) != null) {
			throw new UserAlreadyExistsException();
		}

		if (this.userService.findByEmail(email) != null) {
			throw new UserAlreadyExistsException();
		}

		// @formatter:off
		User user = User.builder().name(name).roles(Arrays.asList(Role.USER)).email(email).password(password).build();
		// @formatter:on
		userService.save(user);

		JwtUser userDetails;
		try {
			userDetails = (JwtUser) userDetailsService.loadUserByUsername(name);
		}
		catch (UsernameNotFoundException ex) {
			log.error(ex.getMessage());
			throw new UserNotFoundException();
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

		final Authentication authentication = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(name, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	/**
	 * Returns authentication token for given user
	 * 
	 * @param authenticationRequest with username and password
	 * @return generated JWT
	 * @throws AuthenticationException
	 */
	@PostMapping(SIGNIN_URL)
	public ResponseEntity<?> getAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
		throws AuthenticationException {

		final String email = authenticationRequest.getUsername();
		final String password = authenticationRequest.getPassword();
		log.info("[POST] GETTING TOKEN FOR User " + email);
		JwtUser userDetails;

		try {
			userDetails = (JwtUser) userDetailsService.loadUserByUsername(email);
			// } catch (UsernameNotFoundException | NoResultException ex) {
			// log.error(ex.getMessage());
			// throw new UserNotFoundException();
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}

		if (!passwordEncoder().matches(password, userDetails.getPassword())) {
			throw new InvalidPasswordException();
		}

		final Authentication authentication = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	/**
	 * Refreshes token
	 * 
	 * @param request with old JWT
	 * @return Refreshed JWT
	 */
	@PostMapping(REFRESH_TOKEN_URL)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		log.info("[POST] REFRESHING TOKEN");
		String refreshedToken = jwtUtil.refreshToken(token);
		return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
	}

}
