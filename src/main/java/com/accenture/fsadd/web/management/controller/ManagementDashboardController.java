package com.accenture.fsadd.web.management.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.sonar.controller.model.QualityGateModel;
import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.user.service.UserService;

@RestController
@RequestMapping("/webmanagement")
public class ManagementDashboardController {

	@Autowired
	private UserService userService;
	
	@Value("${fsadd.sonarqube.project}")
	private String projectKey;

	   /**
     * get qualityDate data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/getUserListInfo")
	public ApiModel<QualityGateModel> getUserListInfo(){
		QualityGateModel model = new QualityGateModel();
		List<User> entity = userService.findAllValidUsers();
		if(entity != null){
			BeanUtils.copyProperties(entity, model);
		}
		ApiModel<QualityGateModel> apiMdole = new ApiModel<>(model); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
}
