package com.accenture.fsadd.dashboard.overview.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.codequality.business.entity.Sonardashboard;
import com.accenture.fsadd.dashboard.codequality.business.service.SonarDashboardService;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.overview.business.HealthType;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.ThresholdLimitEnity;
import com.accenture.fsadd.dashboard.overview.business.entity.ThresholdLimitEnity.ThreshLimitType;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewEventRepository;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewRepository;
import com.accenture.fsadd.dashboard.overview.business.service.OverviewService;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;
import com.accenture.fsadd.web.setting.service.SettingService;

@Service
public class OverviewServiceImpl implements OverviewService {

	@Autowired
	private OverviewEventRepository overviewEventRepository;

	@Autowired
	private OverviewRepository overviewRepository;

	@Autowired
	private BackLogService backLogSerivce;

	@Autowired
	private TaskService taskService;

	@Autowired
	private QueryAnswerService queryAnswerService;

	@Autowired
	private IssueService issueSerivce;

	@Autowired
	private SonarDashboardService sonarDashboardService;

	@Autowired
	private SettingService settingService;

	@Override
	public OverviewEntity getOverview() {
		return overviewRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<OverviewEventEntity> getAllEvents() {
		List<OverviewEventEntity> result = overviewEventRepository.findAll(new Sort(Sort.Direction.DESC, "eventDate"));
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}

	@Override
	public List<OverviewEventEntity> getEventsBetween(LocalDate fromDay, LocalDate toDay) {
		List<OverviewEventEntity> result = overviewEventRepository.findByEventDateBetween(fromDay, toDay,
				new Sort("eventDate"));
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}

	@Override
	public void aggregateService(LocalDateTime executeDateTime) {
		List<ThresholdLimitEnity> thresholdLimitEnityList = this.getThresholdLimitListService();
		HealthType projectHealthType = HealthType.GOOD;
		for (ThresholdLimitEnity thresholdLimitEnity : thresholdLimitEnityList) {
			HealthType healthType = null;
			switch (thresholdLimitEnity.getType()) {
			case BACKLOG:
				healthType = this.checkBacklogHealthService(thresholdLimitEnity);
				break;
			case BUG:
				healthType = this.checkBugHealthService(thresholdLimitEnity);
				break;
			case QA:
				healthType = this.checkQaHealthService(thresholdLimitEnity);
				break;
			case TASK:
				healthType = this.checkTaskHealthService(thresholdLimitEnity);
				break;
			case SOURCE_HEALTH:
				healthType = this.checkSourceHealthService(thresholdLimitEnity);
				break;
			}
			OverviewEventEntity overviewEventEntity = null;
			if (healthType == HealthType.BAD) {
				projectHealthType = HealthType.BAD;
				overviewEventEntity = new OverviewEventEntity();
				overviewEventEntity.setEventTye(OverviewEventEntity.EventType.WARNING);
				overviewEventEntity.setEvent(thresholdLimitEnity.getCriticalMessage());
			} else if (healthType == HealthType.WARNING) {
				if (projectHealthType != HealthType.BAD) {
					projectHealthType = HealthType.WARNING;
				}
				overviewEventEntity = new OverviewEventEntity();
				overviewEventEntity.setEventTye(OverviewEventEntity.EventType.WARNING);
				overviewEventEntity.setEvent(thresholdLimitEnity.getCriticalMessage());

			}
			if (overviewEventEntity != null && healthType != null) {
				String id = executeDateTime.toLocalDate().toString() + "@" + thresholdLimitEnity.getType() + "@"
						+ healthType.toString();
				overviewEventEntity.setId(id);
				overviewEventEntity.setEventDate(executeDateTime.toLocalDate());
				overviewEventRepository.save(overviewEventEntity);

			}

		}

		List<ProjectModel> projectModelList = settingService.findAllValidProjectsInfo();
		String projectName = "fsadd";
		if (!projectModelList.isEmpty()) {
			projectName = projectModelList.get(0).getProjectName();
		}
		OverviewEntity overviewEntity = new OverviewEntity();
		overviewEntity.setProjectName(projectName);
		overviewEntity.setHealth(projectHealthType);
		overviewEntity.setLastUpdateTimeOn(executeDateTime);
		overviewRepository.save(overviewEntity);

	}

	@Override
	public void insertEvent(OverviewEventEntity overviewEventEntity) {
		overviewEventRepository.insert(overviewEventEntity);

	}

	@Override
	public void insertEvent(List<OverviewEventEntity> overviewEventEntityList) {
		overviewEventEntityList.forEach((entity) -> this.insertEvent(entity));

	}

	private ThresholdLimitEnity covertFromThreshodModelToThesholdLimit(ThresholdModel model) {

		ThresholdLimitEnity thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThreshLimitType.valueOf(model.getSystem().toUpperCase()));
		thresholdLimitEntity.setLowerLimit(model.getOverCast());
		thresholdLimitEntity.setUpperLimit(model.getRain());
		thresholdLimitEntity.setWarningMessage("Warning: " + model.getNoticeMsg());
		thresholdLimitEntity.setWarningMessage("Critical: " + model.getNoticeMsg());
		return new ThresholdLimitEnity();

	}

	protected List<ThresholdLimitEnity> getThresholdLimitListService() {

		List<ThresholdModel> thresholds = settingService.findAllValidThresholdsInfo();

		List<ThresholdLimitEnity> result = new ArrayList<>();

		for (ThresholdModel threshold : thresholds) {
			ThresholdLimitEnity thresholdLimitEntity = covertFromThreshodModelToThesholdLimit(threshold);
			result.add(thresholdLimitEntity);
		}

		return result;

	}

	protected HealthType checkBacklogHealthService(ThresholdLimitEnity entity) {

		BackLogSummaryEntity backLogSummaryEntity = backLogSerivce.getSummaryService();
		double delayRate = backLogSummaryEntity.getDelayCount() * 1.0 / (backLogSummaryEntity.getTotalCount());
		if (delayRate >= entity.getLowerLimit() && delayRate < entity.getUpperLimit()) {
			return HealthType.WARNING;
		}
		if (delayRate >= entity.getUpperLimit()) {
			return HealthType.BAD;
		}
		return HealthType.GOOD;

	}

	protected HealthType checkTaskHealthService(ThresholdLimitEnity entity) {

		TaskSummaryEntity taskSummaryEntity = taskService.getSummaryService();
		double delayRate = taskSummaryEntity.getDelayCount() * 1.0 / (taskSummaryEntity.getTotalCount());
		if (delayRate >= entity.getLowerLimit() && delayRate < entity.getUpperLimit()) {
			return HealthType.WARNING;
		}
		if (delayRate >= entity.getUpperLimit()) {
			return HealthType.BAD;
		}
		return HealthType.GOOD;

	}

	protected HealthType checkQaHealthService(ThresholdLimitEnity entity) {
		QueryAnswerSummaryEntity queryAnswerSummaryEntity = queryAnswerService.getSummaryService();
		double delayRate = queryAnswerSummaryEntity.getOverdueCount() * 1.0
				/ (queryAnswerSummaryEntity.getTotalCount());
		if (delayRate >= entity.getLowerLimit() && delayRate < entity.getUpperLimit()) {
			return HealthType.WARNING;
		}
		if (delayRate >= entity.getUpperLimit()) {
			return HealthType.BAD;
		}
		return HealthType.GOOD;

	}

	protected HealthType checkBugHealthService(ThresholdLimitEnity entity) {

		IssueSummaryEntity issueSummaryEntity = issueSerivce.getSummaryService();
		double delayRate = issueSummaryEntity.getDelayedCount() * 1.0 / (issueSummaryEntity.getTotalCount());
		if (delayRate >= entity.getLowerLimit() && delayRate < entity.getUpperLimit()) {
			return HealthType.WARNING;
		}
		if (delayRate >= entity.getUpperLimit()) {
			return HealthType.BAD;
		}
		return HealthType.GOOD;
	}

	protected HealthType checkSourceHealthService(ThresholdLimitEnity entity) {
		Sonardashboard sonardashboard = sonarDashboardService.getSonarDashboard();
		if (entity.getUpperLimitString().equals(sonardashboard.getQualityGateStatus())) {
			return HealthType.BAD;
		}
		if (entity.getLowerLimitString().equals(sonardashboard.getQualityGateStatus())) {
			return HealthType.WARNING;
		}

		return HealthType.GOOD;

	}
}
