package org.lab.armybuilder.service;

import java.util.List;

import org.lab.armybuilder.exception.UserNotFoundException;
import org.lab.armybuilder.repository.UserRepository;
import org.lab.armybuilder.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService provides basic CRUD funtionality for User entity
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	/**
	 * Injects UserRepository instance
	 * @param userRepository to inject
	 */
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Returns all users
	 * @return List of users
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Returns user with given id
	 * @param id to look for
	 * @return user with given id
	 * @throws UserNotFoundException if user with given id does not exists
	 */
	@Override
	public User findById(String id) {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		}
		else {
			throw new UserNotFoundException();
		}
	}

	/**
	 * Returns user with given email
	 * @param email to look for
	 * @return user with given email
	 * @throws UserNotFoundException if user with given email does not exists
	 */
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Returns user with given name
	 * @param name to look for
	 * @return name with given email
	 * @throws UserNotFoundException if user with given name does not exists
	 */
	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	/**
	 * Adds or updates user. If user with following id already exists it will be updated elsewhere added as the new one.
	 * @param user to add or update
	 * @return Added or updated user
	 */
	@Override
	public User save(User user) {
		if (user.getId() == null || !userRepository.existsById(user.getId())) {
			user.setPassword(passwordEncoder().encode(user.getPassword()));
		}
		return userRepository.save(user);
	}

	/**
	 * Deletes user by given id
	 * @param id to look for
	 * @throws UserNotFoundException if user with given id does not exists
	 */
	@Override
	public void delete(String id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
		else {
			throw new UserNotFoundException();
		}
	}

}
