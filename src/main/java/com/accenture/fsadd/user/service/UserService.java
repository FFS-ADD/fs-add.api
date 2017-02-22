package com.accenture.fsadd.user.service;

import java.util.List;

import com.accenture.fsadd.user.entity.User;

public interface UserService {
	
	List<User> findAllValidUsers();
	
	User registerUser(User user);
	
	User getUserByEmail(String email);

	User saveUser(User user);

	void deleteUser(User user);
}
