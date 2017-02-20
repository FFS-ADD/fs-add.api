package com.accenture.fsadd.dashboard.qa.controller;

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
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerDailySummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.qa.controller.model.QaDailySummaryModel;
import com.accenture.fsadd.dashboard.qa.controller.model.QaDetailModel;
import com.accenture.fsadd.dashboard.qa.controller.model.QaEntryModel;

@RestController
@RequestMapping("/qa")
public class QaDetailController {

	private static int BACK_DAY_COUNT = 6;

	private static String PATTERN_MM_DD = "MM/dd";

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private QueryAnswerService queryAnswerService;

	@RequestMapping("/detail")
	public ApiModel<QaDetailModel> getQaDetailAction() {
		QaDetailModel result = new QaDetailModel();
		List<QueryAnswerEntity> qaOverdueList = queryAnswerService.getAllOverDueQAService();
		List<QaEntryModel> qaEntryModelList = new ArrayList<>();
		qaOverdueList.forEach((qaOverDue) -> {
			QaEntryModel qaOverDueModel = new QaEntryModel();
			BeanUtils.copyProperties(qaOverDue, qaOverDueModel);
			qaOverDueModel.setExpectedAnswerOnAsString(
					this.getDayAsString(qaOverDueModel.getExpectedAnswerOn(), PATTERN_YYYMMDD));
			qaEntryModelList.add(qaOverDueModel);
		});
		result.setDailySummaryList(getDailySummaryModelList());
		result.setOverduleList(qaEntryModelList);
		return new ApiModel<>(result);

	}

	private List<QaDailySummaryModel> getDailySummaryModelList() {
		LocalDate toDay = LocalDate.now();
		LocalDate fromDay = toDay.minusDays(BACK_DAY_COUNT);

		List<QueryAnswerDailySummaryEntity> dailySummaryList = queryAnswerService.getDailySummaryService(fromDay,
				toDay);
		List<QaDailySummaryModel> dailySummaryModelList = new ArrayList<>();
		Map<LocalDate, QaDailySummaryModel> dailySummaryModleMap = new HashMap<>();
		for (int i = 0; i <= BACK_DAY_COUNT; i++) {
			QaDailySummaryModel dailySummaryModel = new QaDailySummaryModel();
			dailySummaryModel.setDay(fromDay.plusDays(i));
			dailySummaryModel.setDayAsString(this.getDayAsString(fromDay.plusDays(i), PATTERN_MM_DD));
			dailySummaryModelList.add(dailySummaryModel);
			dailySummaryModleMap.put(dailySummaryModel.getDay(), dailySummaryModel);
		}
		dailySummaryList.forEach((qaSummaryEntity) -> {
			QaDailySummaryModel dailySummaryModel = dailySummaryModleMap.get(qaSummaryEntity.getDay());
			if (dailySummaryModel != null) {
				BeanUtils.copyProperties(qaSummaryEntity.getSummaryEntity(), dailySummaryModel);
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
