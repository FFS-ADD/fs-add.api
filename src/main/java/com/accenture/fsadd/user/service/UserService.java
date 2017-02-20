package com.accenture.fsadd.user.service;

import java.util.List;

import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.web.author.form.WebDashboardLoginForm;

public interface UserService {
	
	List<User> findAllValidUsers();
	
	User registerUser(User user);
	
	User getUserByEmail(String email);

	boolean userLogin(WebDashboardLoginForm form);
}
