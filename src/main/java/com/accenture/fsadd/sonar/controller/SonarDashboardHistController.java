package com.accenture.fsadd.sonar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;
import com.accenture.fsadd.sonar.controller.model.HistModel;

@RestController
@RequestMapping("/sonardashboard")
public class SonarDashboardHistController {

	@Autowired
	private SonarDashboardService sonarDashboardService;
	
	@Value("${fsadd.sonarqube.project}")
	private String projectKey;
    /**
     * get weekly history data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/getHist")
	public ApiModel<HistModel> getHistAction(){
		List<Sonardashboard> entityList = sonarDashboardService.getSonarDashboardHist(projectKey);
		HistModel histModel = new HistModel();
		histModel.setSonardashboardlList(entityList);
		ApiModel<HistModel> apiMdole = new ApiModel<>(histModel); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
}
