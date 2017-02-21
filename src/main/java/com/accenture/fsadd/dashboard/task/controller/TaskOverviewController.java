package com.accenture.fsadd.dashboard.task.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.dashboard.task.controller.model.TaskOverviewModel;

@RestController
@RequestMapping("/task")
public class TaskOverviewController {

	@Autowired
	private TaskService taskService;

	@RequestMapping("/overview")
	public ApiModel<TaskOverviewModel> getOverviewAction() {
		TaskSummaryEntity summaryEntity = taskService.getSummaryService();
		if (summaryEntity == null) {
			summaryEntity = new TaskSummaryEntity();
		}
		TaskOverviewModel result = new TaskOverviewModel();
		BeanUtils.copyProperties(summaryEntity, result);
		return new ApiModel<>(result);
		

	}

}
