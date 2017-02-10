package com.accenture.fsadd.sonar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;
import com.accenture.fsadd.sonar.controller.model.HistModel;
import com.hotpot.core.mvc.model.ApiModel;

@RestController
@RequestMapping("/sonardashboard")
public class SonarDashboardHistController {

	@Autowired
	SonarDashboardService sonarDashboardService;
	
    /**
     * get weekly history data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/getHist")
	public ApiModel<HistModel> getHistAction(){
		List<Sonardashboard> entityList = sonarDashboardService.getSonarDashboardHist("inventory-aid");
		HistModel histModel = new HistModel();
		histModel.setSonardashboardlList(entityList);
		ApiModel<HistModel> apiMdole = new ApiModel<>(histModel); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}
}
