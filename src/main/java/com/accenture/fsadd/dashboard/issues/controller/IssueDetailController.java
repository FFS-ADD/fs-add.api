package com.accenture.fsadd.dashboard.issues.controller;

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
import com.accenture.fsadd.dashboard.issues.business.entity.IssueDailySummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.issues.controller.Model.IssueDailySummaryModel;
import com.accenture.fsadd.dashboard.issues.controller.Model.IssueDetailModel;
import com.accenture.fsadd.dashboard.issues.controller.Model.IssueEntryModle;

@RestController
@RequestMapping("/issue")
public class IssueDetailController {

	private static int BACK_DAY_COUNT = 6;

	private static String PATTERN_MM_DD = "MM/dd";

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private IssueService issueService;

	@RequestMapping("/detail")
	public ApiModel<IssueDetailModel> getQaDetailAction() {
		IssueDetailModel result = new IssueDetailModel();
		List<IssueEntity> opendIssueList = issueService.getAllOpenedIssue();
		List<IssueEntryModle> issueEntryModelList = new ArrayList<>();
		opendIssueList.forEach((openedIssue) -> {
			IssueEntryModle issueEntryModle = new IssueEntryModle();
			BeanUtils.copyProperties(openedIssue, issueEntryModle);
			issueEntryModle.setDueDateAsString(this.getDayAsString(issueEntryModle.getDueDate(), PATTERN_YYYMMDD));
			issueEntryModle.setPriorityAsString(this.getPriorityAsString(issueEntryModle.getPriority()));
			issueEntryModelList.add(issueEntryModle);
		});
		result.setDailySummaryList(getDailySummaryModelList());
		result.setDailyFixSummaryList(result.getDailySummaryList());
		result.setOpenedIssueList(issueEntryModelList);
		result.setDailyDateAsStringList(new ArrayList<>());
		result.getDailySummaryList().forEach((dailySummary) -> {
			result.getDailyDateAsStringList().add(dailySummary.getDayAsString());
		});
		return new ApiModel<>(result);

	}

	private String getPriorityAsString(IssueEntity.Priority priority) {
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

	private List<IssueDailySummaryModel> getDailySummaryModelList() {
		LocalDate toDay = LocalDate.now();
		LocalDate fromDay = toDay.minusDays(BACK_DAY_COUNT);

		List<IssueDailySummaryEntity> dailySummaryList = issueService.getDailySummaryService(fromDay, toDay);
		List<IssueDailySummaryModel> dailySummaryModelList = new ArrayList<>();
		Map<LocalDate, IssueDailySummaryModel> dailySummaryModleMap = new HashMap<>();
		for (int i = 0; i <= BACK_DAY_COUNT; i++) {
			IssueDailySummaryModel dailySummaryModel = new IssueDailySummaryModel();
			dailySummaryModel.setDay(fromDay.plusDays(i));
			dailySummaryModel.setDayAsString(this.getDayAsString(fromDay.plusDays(i), PATTERN_MM_DD));
			dailySummaryModelList.add(dailySummaryModel);
			dailySummaryModleMap.put(dailySummaryModel.getDay(), dailySummaryModel);
		}
		dailySummaryList.forEach((dailySummaryEntity) -> {
			IssueDailySummaryModel dailySummaryModel = dailySummaryModleMap.get(dailySummaryEntity.getDay());
			if (dailySummaryModel != null) {
				BeanUtils.copyProperties(dailySummaryEntity.getSummaryEntity(), dailySummaryModel);
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
