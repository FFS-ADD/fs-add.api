package com.accenture.fsadd.web.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.user.service.UserService;
import com.accenture.fsadd.web.author.form.WebDashboardLoginForm;

@RestController
@RequestMapping("/webauthor")
public class AuthorDashboardController {

	@Autowired
	private UserService userService;
	
	@Value("${fsadd.sonarqube.project}")
	private String projectKey;

	   /**
     * get qualityDate data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/login")
	public void loginAction(@RequestBody WebDashboardLoginForm form){
		userService.userLogin(form);
	}
}
