package com.accenture.fsadd.dashboard.qa.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.qa.controller.model.QaOverviewModel;

@RestController
@RequestMapping("/qa")
public class QaOverviewController {

	@Autowired
	private QueryAnswerService queryAnswerService;

	@RequestMapping("/overview")
	public ApiModel<QaOverviewModel> getOverviewAction() {
		QueryAnswerSummaryEntity queryAnswerSummaryEntity = queryAnswerService.getSummaryService();
		if (queryAnswerSummaryEntity == null) {
			queryAnswerSummaryEntity = new QueryAnswerSummaryEntity();
		}
		QaOverviewModel result = new QaOverviewModel();
		BeanUtils.copyProperties(queryAnswerSummaryEntity, result);
		return new ApiModel<>(result);
		

	}

}
