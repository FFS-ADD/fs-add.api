package com.accenture.fsadd.web.setting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;
import com.accenture.fsadd.web.setting.form.ProjectInfoForm;
import com.accenture.fsadd.web.setting.form.ThresholdProfileForm;
import com.accenture.fsadd.web.setting.service.SettingService;

@RestController
@CrossOrigin(origins="http://localhost:5555")
@RequestMapping("/websetting")
public class SettingDashboardController {

	@Autowired
	private SettingService settingService;

	@RequestMapping(value = "/getProjectsInfo", method = RequestMethod.GET)
	public ApiModel<List<ProjectModel>> getProjectsInfo(){
		List<ProjectModel> entity = settingService.findAllValidProjectsInfo();
		ApiModel<List<ProjectModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/createProject", method = RequestMethod.POST)
	public ApiModel<List<ProjectModel>> createProject(@RequestBody ProjectInfoForm from){
		settingService.createProject(from);
		List<ProjectModel> entity = settingService.findAllValidProjectsInfo();
		ApiModel<List<ProjectModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	public ApiModel<List<ProjectModel>> updateProject(@RequestBody ProjectInfoForm from){
		settingService.updateProject(from);
		List<ProjectModel> entity = settingService.findAllValidProjectsInfo();
		ApiModel<List<ProjectModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
	
	@RequestMapping(value = "/getThresholdInfo", method = RequestMethod.GET)
	public ApiModel<List<ThresholdModel>> getThresholdInfo(){
		List<ThresholdModel> entity = settingService.findAllValidThresholdsInfo();
		ApiModel<List<ThresholdModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/createThreshold", method = RequestMethod.POST)
	public ApiModel<List<ThresholdModel>> createThreshold(@RequestBody ThresholdProfileForm from){
		settingService.createThreshold(from);
		List<ThresholdModel> entity = settingService.findAllValidThresholdsInfo();
		ApiModel<List<ThresholdModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
	
	@RequestMapping(value = "/updateThreshold", method = RequestMethod.POST)
	public ApiModel<List<ThresholdModel>> updateThreshold(@RequestBody ThresholdProfileForm from){
		settingService.updateThreshold(from);
		List<ThresholdModel> entity = settingService.findAllValidThresholdsInfo();
		ApiModel<List<ThresholdModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
	
	@RequestMapping(value = "/deleteThreshold", method = RequestMethod.POST)
	public ApiModel<List<ThresholdModel>> deleteThreshold(@RequestBody ThresholdProfileForm from){
		settingService.deleteThreshold(from);
		List<ThresholdModel> entity = settingService.findAllValidThresholdsInfo();
		ApiModel<List<ThresholdModel>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
}
