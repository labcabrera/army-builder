package org.lab.armybuilder.service;

import org.lab.armybuilder.repository.UserRepository;
import org.lab.armybuilder.security.auth.JwtUserFactory;
import org.lab.armybuilder.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * JwtUserDetailsServiceImp is used to find UserDetails by name
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	/**
	 * Injects UserRepository instance
	 * @param userRepository to inject
	 */
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Finds UserDetails by given username
	 * @param username which is used to search user
	 * @return UserDetails
	 * @throws UsernameNotFoundException if user with given name does not exists
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
		return JwtUserFactory.create(user);
	}
}
