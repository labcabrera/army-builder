package org.lab.armybuilder.service;

import java.util.List;

import org.lab.armybuilder.security.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	User save(User user);

	void delete(String id);

	List<User> findAll();

	User findById(String id);

	User findByEmail(String email);

	User findByName(String name);
}
