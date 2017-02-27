package com.accenture.fsadd.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.user.controller.model.UserEntryModel;
import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/get")
	public ApiModel<UserEntryModel> getData(@RequestParam(value = "email") String email) {
		User result = userService.getUserByEmail(email);
		UserEntryModel retModel = new UserEntryModel();
		BeanUtils.copyProperties(result, retModel);
		return new ApiModel<>(retModel);
	}
}
