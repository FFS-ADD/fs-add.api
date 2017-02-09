package com.accenture.fsadd.sonar.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;
import com.accenture.fsadd.sonar.controller.model.CoverageModel;
import com.accenture.fsadd.sonar.controller.model.DuplicationModel;
import com.accenture.fsadd.sonar.controller.model.HistModel;
import com.accenture.fsadd.sonar.controller.model.LocModel;
import com.accenture.fsadd.sonar.controller.model.QualityGateModel;
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
		histModel.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(histModel);
	}
}
