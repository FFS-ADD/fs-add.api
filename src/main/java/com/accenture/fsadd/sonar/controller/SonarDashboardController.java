package com.accenture.fsadd.sonar.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;
import com.accenture.fsadd.sonar.controller.model.CoverageModel;
import com.accenture.fsadd.sonar.controller.model.DuplicationModel;
import com.accenture.fsadd.sonar.controller.model.LocModel;
import com.accenture.fsadd.sonar.controller.model.QualityGateModel;
import com.hotpot.core.mvc.model.ApiModel;

@RestController
@RequestMapping("/sonardashboard")
public class SonarDashboardController {

	@Autowired
	SonarDashboardService sonarDashboardService;
    /**
     * get qualityDate data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/getQualityDate")
	public ApiModel<QualityGateModel> getQualityDateAction(){
		
		QualityGateModel model = new QualityGateModel();
		Sonardashboard entity = sonarDashboardService.getSonarDashboard("inventory-aid");
		BeanUtils.copyProperties(entity, model);
		model.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(model);
	}

    /**
     * get loc data from sonar dashboard
     * @param form
     * @return ApiModel<LocModel>
     */
	@RequestMapping("/getLoc")
	public ApiModel<LocModel> getLocAction(){
		LocModel model = new LocModel();
		Sonardashboard entity = sonarDashboardService.getSonarDashboard("inventory-aid");
		BeanUtils.copyProperties(entity, model);
		model.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(model);
	}

    /**
     * get coverage data from sonar dashboard
     * @param form
     * @return ApiModel<CoverageModel>
     */
	@RequestMapping("/getCoverage")
	public ApiModel<CoverageModel> getCoverageAction(){
		CoverageModel model = new CoverageModel();
		Sonardashboard entity = sonarDashboardService.getSonarDashboard("inventory-aid");
		BeanUtils.copyProperties(entity, model);
		model.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(model);
	}
	
    /**
     * get duplication data from sonar dashboard
     * @param form
     * @return ApiModel<DuplicationModel>
     */
	@RequestMapping("/getDuplication")
	public ApiModel<DuplicationModel> getDuplicationModelAction(){
		DuplicationModel model = new DuplicationModel();
		Sonardashboard entity = sonarDashboardService.getSonarDashboard("inventory-aid");
		BeanUtils.copyProperties(entity, model);
		model.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(model);
	}
}
