package com.accenture.fsadd.web.setting.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.web.setting.form.ProjectInfoForm;
import com.accenture.fsadd.web.setting.form.ThresholdProfileForm;

@RestController
@RequestMapping("/websetting")
public class SettingDashboardController {

//	@Autowired
//	private UserService userService;


	@RequestMapping("/getProjectsInfo")
	public void getProjectsInfo(){
	}
	
	@RequestMapping("/getThresholdInfo")
	public void getThresholdInfo(){
	}
	
	@RequestMapping("/saveProjectInfo")
	public void saveProjectInfo(@RequestBody ProjectInfoForm form){
	}
	
	@RequestMapping("/saveThresholdInfo")
	public void saveThresholdInfo(@RequestBody ThresholdProfileForm from){
	}
	
	@RequestMapping("/deleteThresholdItem")
	public void deleteThresholdItem(@RequestBody ThresholdProfileForm from){
	}
}
