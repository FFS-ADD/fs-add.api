package com.accenture.fsadd.dashboard.issues.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.issues.controller.Model.IssueOverviewModel;

@RestController
@RequestMapping("/issue")
public class IssueOverviewController {

	@Autowired
	private IssueService issueService;

	@RequestMapping("/overview")
	public ApiModel<IssueOverviewModel> getOverviewAction() {

		IssueSummaryEntity issueSummaryEntity = issueService.getSummaryService();
		if (issueSummaryEntity == null) {
			issueSummaryEntity = new IssueSummaryEntity();
		}
		IssueOverviewModel result = new IssueOverviewModel();
		BeanUtils.copyProperties(issueSummaryEntity, result);
		return new ApiModel<>(result);

	}

}
