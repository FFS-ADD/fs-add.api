package com.accenture.fsadd.sonar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.sonar.controller.model.CoverageModel;
import com.accenture.fsadd.sonar.controller.model.DuplicationModel;
import com.accenture.fsadd.sonar.controller.model.LocModel;
import com.accenture.fsadd.sonar.controller.model.QualityGateModel;
import com.hotpot.core.mvc.model.ApiModel;

@RestController
@RequestMapping("/sonardashboard")
public class SonarDashboardController {

    /**
     * get qualityDate data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping("/getQualityDate")
	public ApiModel<QualityGateModel> getQualityDateAction(){
		
		QualityGateModel model = new QualityGateModel();
		model.setBugs("60");
		model.setCodeSmells("2.1K");
		model.setQualityGateStatus("success");
		model.setVulneralilities("30");
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
		model.setLine("200K");
		model.setCodeLines("20K");
		model.setStatement("100K");
		model.setFile("30K");
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
		model.setTests("200");
		model.setSuccess("198");
		model.setFailures("2");
		model.setCoverage("99%");
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
		model.setLine("200K");
		model.setBlocks("1.9K");
		model.setFile("20");
		model.setDuplication("12.1%");
		model.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return new ApiModel<>(model);
	}
}
