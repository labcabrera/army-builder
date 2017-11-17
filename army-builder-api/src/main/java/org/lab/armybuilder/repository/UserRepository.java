package org.lab.armybuilder.repository;

import org.lab.armybuilder.security.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides basic CURD operations with User entity
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * Finds user by email
	 * 
	 * @param email to look for
	 * @return user by given email
	 */
	User findByEmail(String email);

	/**
	 * Finds user by name
	 * 
	 * @param name to look for
	 * @return user by given name
	 */
	User findByName(String name);
}
