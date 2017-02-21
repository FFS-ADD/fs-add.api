package com.accenture.fsadd.dashboard.task.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.qa.controller.model.QaDailySummaryModel;
import com.accenture.fsadd.dashboard.task.business.entity.TaskDailySummaryEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.dashboard.task.controller.model.TaskDailySummaryModel;
import com.accenture.fsadd.dashboard.task.controller.model.TaskDetailModel;
import com.accenture.fsadd.dashboard.task.controller.model.TaskEntryModel;

@RestController
@RequestMapping("/task")
public class TaskDetailController {

	private static int BACK_DAY_COUNT = 6;

	private static String PATTERN_MM_DD = "MM/dd";

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private TaskService taskService;

	@RequestMapping("/detail")
	public ApiModel<TaskDetailModel> getQaDetailAction() {
		TaskDetailModel result = new TaskDetailModel();
		List<TaskEntity> overdueTaskList = taskService.getAllDelayTask();
		List<TaskEntryModel> taskEntryModelList = new ArrayList<>();
		overdueTaskList.forEach((overDueTaskEntity) -> {
			TaskEntryModel overdueTaskModel = new TaskEntryModel();
			BeanUtils.copyProperties(overDueTaskEntity, overdueTaskModel);
			overdueTaskModel.setPlanedStartDateAsString(
					this.getDayAsString(overdueTaskModel.getPlanedStartDate(), PATTERN_YYYMMDD));
			taskEntryModelList.add(overdueTaskModel);
		});
		result.setDailySummaryList(getDailySummaryModelList());
		result.setOverduleList(taskEntryModelList);
		return new ApiModel<>(result);

	}

	private List<TaskDailySummaryModel> getDailySummaryModelList() {
		LocalDate toDay = LocalDate.now();
		LocalDate fromDay = toDay.minusDays(BACK_DAY_COUNT);

		List<TaskDailySummaryEntity> dailySummaryList = taskService.getDailySummaryService(fromDay, toDay);
		List<TaskDailySummaryModel> dailySummaryModelList = new ArrayList<>();
		Map<LocalDate, TaskDailySummaryModel> dailySummaryModleMap = new HashMap<>();
		for (int i = 0; i <= BACK_DAY_COUNT; i++) {
			TaskDailySummaryModel dailySummaryModel = new TaskDailySummaryModel();
			dailySummaryModel.setDay(fromDay.plusDays(i));
			dailySummaryModel.setDayAsString(this.getDayAsString(fromDay.plusDays(i), PATTERN_MM_DD));
			dailySummaryModelList.add(dailySummaryModel);
			dailySummaryModleMap.put(dailySummaryModel.getDay(), dailySummaryModel);
		}
		dailySummaryList.forEach((summaryEntity) -> {
			TaskDailySummaryModel dailySummaryModel = dailySummaryModleMap.get(summaryEntity.getDay());
			if (dailySummaryModel != null) {
				BeanUtils.copyProperties(summaryEntity.getSummaryEntity(), dailySummaryModel);
			}
		});
		return dailySummaryModelList;
	}

	private String getDayAsString(LocalDate date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);

	}

}
