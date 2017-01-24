package com.accenture.fsadd.user.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.user.entity.Role;
import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.user.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	UserService userService;
	//@Test
	public void testRegisterUser() {
		User user = new User();
		user.setEmail("abc@abc.com");
		user.setPassword("abc#123");
		user.setRole(Role.ADMIN.getRoleName());
		userService.registerUser(user);
	}
	
	@Test
	public void testFindAllUser(){
		userService.findAllValidUsers().forEach(user->System.out.println(user));
	}
	
	@Test
	public void testFindOneUser(){
		System.out.println(userService.getUserByEmail("abc@abc.com"));
	}
	

}
