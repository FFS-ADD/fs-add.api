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
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;
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
		result.setOpenedIssueList(issueEntryModelList);
		setDailySummaryModelList(result);
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

	private void setDailySummaryModelList(IssueDetailModel detailModel) {
		LocalDate toDay = LocalDate.now();
		LocalDate fromDay = toDay.minusDays(BACK_DAY_COUNT);

		List<IssueDailySummaryEntity> dailySummaryList = issueService.getDailySummaryService(fromDay, toDay);
		Map<LocalDate, IssueSummaryEntity> summaryEntityMap = new HashMap<>();
		dailySummaryList.forEach((issueDailySummaryEntity) -> {
			summaryEntityMap.put(issueDailySummaryEntity.getDay(), issueDailySummaryEntity.getSummaryEntity());
		});

		IssueDailySummaryModel dailySummary = new IssueDailySummaryModel();
		dailySummary.setNewCountList(new ArrayList<>());
		dailySummary.setInProgressingCountList(new ArrayList<>());
		dailySummary.setRetestingCountList(new ArrayList<>());
		dailySummary.setClosedCountList(new ArrayList<>());
		dailySummary.setFixedCountList(new ArrayList<>());
		detailModel.setDailySummary(dailySummary);
		detailModel.setDailyFixSummary(dailySummary);

		detailModel.setDailyDateAsStringList(new ArrayList<>());

		for (int i = 0; i <= BACK_DAY_COUNT; i++) {
			detailModel.getDailyDateAsStringList().add(this.getDayAsString(fromDay.plusDays(i), PATTERN_MM_DD));
			IssueSummaryEntity issueSummaryEntity = summaryEntityMap.get(fromDay.plusDays(i));
			if (issueSummaryEntity == null) {
				dailySummary.getClosedCountList().add(0);
				dailySummary.getFixedCountList().add(0);
				dailySummary.getInProgressingCountList().add(0);
				dailySummary.getNewCountList().add(0);
				dailySummary.getRetestingCountList().add(0);

			} else {
				dailySummary.getClosedCountList().add(issueSummaryEntity.getClosedCount());
				dailySummary.getFixedCountList().add(issueSummaryEntity.getFixedCount());
				dailySummary.getInProgressingCountList().add(issueSummaryEntity.getInProgressIngCount());
				dailySummary.getNewCountList().add(issueSummaryEntity.getNewCount());
				dailySummary.getRetestingCountList().add(issueSummaryEntity.getRetestingCount());
			}
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
