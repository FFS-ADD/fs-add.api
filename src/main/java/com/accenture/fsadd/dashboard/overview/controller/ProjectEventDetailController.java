package com.accenture.fsadd.dashboard.overview.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;
import com.accenture.fsadd.dashboard.overview.business.service.OverviewService;
import com.accenture.fsadd.dashboard.overview.controller.model.ProjectEventModel;
import com.accenture.fsadd.dashboard.overview.controller.model.ProjectEventsModel;

@RestController
@RequestMapping("/project")
public class ProjectEventDetailController {

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private OverviewService overviewService;

	@RequestMapping("/events/all")
	public ApiModel<ProjectEventsModel> getAllEventsAction() {

		List<ProjectEventModel> eventList = new ArrayList<>();
		List<OverviewEventEntity> overviewEntityList = overviewService.getAllEvents();
		if (overviewEntityList.isEmpty()) {
			return new ApiModel<>();
		}
		overviewEntityList.forEach((topEventEntity) -> {
			ProjectEventModel projectEventModel = new ProjectEventModel();
			projectEventModel.setEventLevel(topEventEntity.getEventTye().ordinal());
			projectEventModel.setEventDateAsString(this.getDayAsString(topEventEntity.getEventDate(), PATTERN_YYYMMDD));
			projectEventModel.setEvent(topEventEntity.getEvent());
			eventList.add(projectEventModel);
		});
		ProjectEventsModel result = new ProjectEventsModel();
		result.setProjectEventModels(eventList);

		return new ApiModel<>(result);

	}

	private String getDayAsString(LocalDate date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);

	}

}
