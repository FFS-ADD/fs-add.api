package com.accenture.fsadd.dashboard.overview.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.dashboard.overview.business.HealthType;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;
import com.accenture.fsadd.dashboard.overview.business.service.OverviewService;
import com.accenture.fsadd.dashboard.overview.controller.model.ProjectEventModel;
import com.accenture.fsadd.dashboard.overview.controller.model.ProjectOverviewModel;

@RestController
@RequestMapping("/project")
public class ProjectOverviewController {

	private static String PATTERN_YYYMMDDMISS = "yyyy/MM/dd HH:mm:SS";

	private static String PATTERN_YYYMMDD = "yyyy/MM/dd";

	@Autowired
	private OverviewService overviewService;

	@RequestMapping("/overview")
	public ApiModel<ProjectOverviewModel> getOverviewAction() {
		OverviewEntity overviewEntity = overviewService.getOverview();
		if (overviewEntity == null) {
			overviewEntity = new OverviewEntity();
		}
		ProjectOverviewModel result = new ProjectOverviewModel();
		result.setProjectName(overviewEntity.getProjectName());
		result.setLastUpdateTime(getDayAsString(overviewEntity.getLastUpdateTimeOn(), PATTERN_YYYMMDDMISS));
		result.setProjectStatus(getProjectStatus(overviewEntity.getHealth()));
		return new ApiModel<>(result);

	}

	@RequestMapping("/events/top")
	public ApiModel<ProjectEventModel> getTopEventAction() {
		List<OverviewEventEntity> overviewEntityList = overviewService.getAllEvents();
		if (overviewEntityList.isEmpty()) {
			return new ApiModel<>();
		}
		OverviewEventEntity topEventEntity = overviewEntityList.get(0);
		ProjectEventModel projectEventModel = new ProjectEventModel();
		projectEventModel.setEventLevel(topEventEntity.getEventTye().ordinal());
		projectEventModel.setEventDateAsString(this.getDayAsString(topEventEntity.getEventDate(), PATTERN_YYYMMDD));
		projectEventModel.setEvent(topEventEntity.getEvent());
		return new ApiModel<>(projectEventModel);

	}

	private String getDayAsString(LocalDateTime date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);

	}

	private int getProjectStatus(HealthType healthType) {
		return healthType != null ? healthType.ordinal() : 0;
	}

	private String getDayAsString(LocalDate date, String pattern) {
		if (date == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(date);

	}

}
