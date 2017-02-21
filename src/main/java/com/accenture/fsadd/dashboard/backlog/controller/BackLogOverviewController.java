package com.accenture.fsadd.dashboard.backlog.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.backlog.controller.model.BackLogOverviewModel;

@RestController
@RequestMapping("/backlog")
public class BackLogOverviewController {

	@Autowired
	private BackLogService backLogService;

	@RequestMapping("/overview")
	public ApiModel<BackLogOverviewModel> getOverviewAction() {
		BackLogSummaryEntity summaryEntity = backLogService.getSummaryService();
		if (summaryEntity == null) {
			summaryEntity = new BackLogSummaryEntity();
		}
		BackLogOverviewModel result = new BackLogOverviewModel();
		BeanUtils.copyProperties(summaryEntity, result);
		return new ApiModel<>(result);
		

	}

}
