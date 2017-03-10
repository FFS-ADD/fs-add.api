package com.accenture.fsadd.dashboard.backlog.controller;

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
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogDailySummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.backlog.controller.model.BackLogDailyBurnDownModel;
import com.accenture.fsadd.dashboard.backlog.controller.model.BackLogDetailModel;
import com.accenture.fsadd.dashboard.backlog.controller.model.BackLogEntryModel;

@RestController
@RequestMapping("/backlog")
public class BackLogDetailController {

	private static int BACK_DAY_COUNT = 6;

	private static String PATTERN_MM_DD = "MM/dd";

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private BackLogService backLogService;

	@RequestMapping("/detail")
	public ApiModel<BackLogDetailModel> getQaDetailAction() {
		BackLogDetailModel result = new BackLogDetailModel();
		List<BackLogEntity> openedBackLogList = backLogService.getAllOpenedBackLog();
		List<BackLogEntryModel> backlogEntryModelList = new ArrayList<>();
		openedBackLogList.forEach((openBackLog) -> {
			BackLogEntryModel backLogEntryModel = new BackLogEntryModel();
			BeanUtils.copyProperties(openBackLog, backLogEntryModel);
			backLogEntryModel
					.setPlanedStartDateAsString(this.getDayAsString(openBackLog.getPlanedStartDate(), PATTERN_YYYMMDD));
			backLogEntryModel
					.setPlanedEndDateAsString(this.getDayAsString(openBackLog.getPlanedEndDate(), PATTERN_YYYMMDD));
			backLogEntryModel.setPriorityAsString(this.getPriorityAsString(openBackLog.getPriority()));
			backLogEntryModel.setDiffHours(openBackLog.getEstimatedHours() - openBackLog.getActualHours());
			backlogEntryModelList.add(backLogEntryModel);
		});
		result.setOpenBackLogList(backlogEntryModelList);
		this.setDailyBurnDownDetail(result);
		return new ApiModel<>(result);

	}

	private String getPriorityAsString(BackLogEntity.Priority priority) {
		switch (priority) {
		case LOW:
			return "Low";
		case MEDIUM:
			return "Middle";
		case HIGH:
			return "High";
		case CRITICAL:
			return "Critial";
		}
		return "";
	}

	private void setDailyBurnDownDetail(BackLogDetailModel backLogDetailModel) {
		LocalDate toDay = LocalDate.now();
		LocalDate fromDay = toDay.minusDays(BACK_DAY_COUNT);

		List<BackLogDailySummaryEntity> dailySummaryList = backLogService.getDailySummaryService(fromDay, toDay);
		Map<LocalDate, BackLogSummaryEntity> dailySummaryModelMap = new HashMap<>();
		dailySummaryList.forEach((backLogDailySummaryEntity) -> {
			dailySummaryModelMap.put(backLogDailySummaryEntity.getDay(), backLogDailySummaryEntity.getSummaryEntity());
		});
		BackLogDailyBurnDownModel backLogDailyBurnDownModel = new BackLogDailyBurnDownModel();
		backLogDailyBurnDownModel.setActualCompletedCount(new ArrayList<>());
		backLogDailyBurnDownModel.setPlanCompletedCount(new ArrayList<>());
		backLogDetailModel.setDetailDateList(new ArrayList<>());

		for (int i = 0; i <= BACK_DAY_COUNT; i++) {
			BackLogSummaryEntity backLogDailySummaryEntity = dailySummaryModelMap.get(fromDay.plusDays(i));
			if (backLogDailySummaryEntity != null) {
              backLogDailyBurnDownModel.getPlanCompletedCount().add(backLogDailySummaryEntity.getTotalCount()
                      - backLogDailySummaryEntity.getClosedCount());
              backLogDailyBurnDownModel.getActualCompletedCount()
                      .add(backLogDailySummaryEntity.getInProgressIngCount() + backLogDailySummaryEntity.getDelayCount() + backLogDailySummaryEntity.getPendingCount());
          } else {
				backLogDailyBurnDownModel.getPlanCompletedCount().add(0);
				backLogDailyBurnDownModel.getActualCompletedCount().add(0);
			}
			backLogDetailModel.getDetailDateList().add(getDayAsString(fromDay.plusDays(i), PATTERN_MM_DD));
			backLogDetailModel.setBackLogDailyBurnDownModel(backLogDailyBurnDownModel);
		}
	}

	private String getDayAsString(LocalDate date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);

	}

}
