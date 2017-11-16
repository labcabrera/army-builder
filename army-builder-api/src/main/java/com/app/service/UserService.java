package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.security.domain.User;

@Service
public interface UserService {

	User save(User user);

	void delete(String id);

	List<User> findAll();

	User findById(String id);

	User findByEmail(String email);

	User findByName(String name);
}
