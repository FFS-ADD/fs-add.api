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
import com.accenture.fsadd.dashboard.codequality.business.service.SonarDashboardService;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.overview.business.HealthType;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.ThresholdLimitEnity;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewEventRepository;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewRepository;
import com.accenture.fsadd.dashboard.overview.business.service.OverviewService;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
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
		BigInteger id = BigInteger.valueOf(overviewEventRepository.count());
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
					overviewEventEntity = new OverviewEventEntity();
					overviewEventEntity.setEventTye(OverviewEventEntity.EventType.WARNING);
					overviewEventEntity.setEvent(thresholdLimitEnity.getCriticalMessage());
				}
			}
			if (overviewEventEntity != null) {
				id = id.add(BigInteger.ONE);
				overviewEventEntity.setId(id);
				overviewEventEntity.setEventDate(executeDateTime.toLocalDate());
				overviewEventRepository.insert(overviewEventEntity);

			}

		}
		List<ProjectModel> projectModelList = settingService.findAllValidProjectsInfo();
		String projectName = "fsadd";
		if (projectModelList.size() > 0) {
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

	protected List<ThresholdLimitEnity> getThresholdLimitListService() {
		// TODO
		List<ThresholdLimitEnity> result = new ArrayList<>();
		ThresholdLimitEnity thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThresholdLimitEnity.ThreshLimitType.BACKLOG);
		thresholdLimitEntity.setLowerLimit(0.08);
		thresholdLimitEntity.setUpperLimit(0.1);
		thresholdLimitEntity.setWarningMessage("Warning: The rate of the delayed backlog has been over lower limit!");
		thresholdLimitEntity.setCriticalMessage("Critical:The rate of the delayed backlog has been over upper limit!");
		result.add(thresholdLimitEntity);

		thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThresholdLimitEnity.ThreshLimitType.BUG);
		thresholdLimitEntity.setLowerLimit(0.08);
		thresholdLimitEntity.setUpperLimit(0.1);
		thresholdLimitEntity.setWarningMessage("Warning: The rate of the delayed bug has been over lower limit!");
		thresholdLimitEntity.setCriticalMessage("Critical:The rate of the delayed bug has been over upper limit!");
		result.add(thresholdLimitEntity);

		thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThresholdLimitEnity.ThreshLimitType.QA);
		thresholdLimitEntity.setLowerLimit(0.08);
		thresholdLimitEntity.setUpperLimit(0.1);
		thresholdLimitEntity.setWarningMessage("Warning: The rate of the delayed QA has been over lower limit!");
		thresholdLimitEntity.setCriticalMessage("Critical:The rate of the delayed QA has been over upper limit!");
		result.add(thresholdLimitEntity);

		thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThresholdLimitEnity.ThreshLimitType.TASK);
		thresholdLimitEntity.setLowerLimit(0.08);
		thresholdLimitEntity.setUpperLimit(0.1);
		thresholdLimitEntity.setWarningMessage("Warning: The rate of the delayed task has been over lower limit!");
		thresholdLimitEntity.setCriticalMessage("Critical:The rate of the delayed task has been over upper limit!");
		result.add(thresholdLimitEntity);

		thresholdLimitEntity = new ThresholdLimitEnity();
		thresholdLimitEntity.setType(ThresholdLimitEnity.ThreshLimitType.SOURCE_HEALTH);
		thresholdLimitEntity.setLowerLimit(0);
		thresholdLimitEntity.setUpperLimit(0);
		thresholdLimitEntity.setLowerLimitString("warning");
		thresholdLimitEntity.setUpperLimitString("warning");
		thresholdLimitEntity.setWarningMessage("Warning: Health check of source code was failed!");
		thresholdLimitEntity.setWarningMessage("Warning: Health check of source code was failed!");
		result.add(thresholdLimitEntity);

		return result;

	}

	protected HealthType checkBacklogHealthService(ThresholdLimitEnity entity) {

		BackLogSummaryEntity backLogSummaryEntity = backLogSerivce.getSummaryService();
		double delayRate = backLogSummaryEntity.getDelayCount()*1.0
				/ (backLogSummaryEntity.getTotalCount());
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
		double delayRate = taskSummaryEntity.getDelayCount()*1.0
				/ (taskSummaryEntity.getTotalCount());
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
		double delayRate = queryAnswerSummaryEntity.getOverdueCount()*1.0
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
		double delayRate = issueSummaryEntity.getDelayedCount()*1.0
				/ (issueSummaryEntity.getTotalCount());
		if (delayRate >= entity.getLowerLimit() && delayRate < entity.getUpperLimit()) {
			return HealthType.WARNING;
		}
		if (delayRate >= entity.getUpperLimit()) {
			return HealthType.BAD;
		}
		return HealthType.GOOD;
	}

	protected HealthType checkSourceHealthService(ThresholdLimitEnity entity) {
		//TODO
//		Sonardashboard sonardashboard = sonarDashboardService.getSonarDashboard("");
//		if (entity.getLowerLimitString().equals(sonardashboard.getQualityGateStatus())) {
//			return HealthType.WARNING;
//		}
//		if (entity.getUpperLimitString().equals(sonardashboard.getQualityGateStatus())) {
//			return HealthType.BAD;
//		}

		return HealthType.GOOD;

	}

	// private void insertEvent(OverviewEventEntity.EventType type, String
	// noticeMessage, LocalDate eventOccuredDate) {
	// BigInteger = int overviewEventRepository.count();
	// OverviewEventEntity overviewEventEntity = new OverviewEventEntity();
	// overviewEventEntity.setEventTye(type);
	// overviewEventEntity.setEventDate(eventOccuredDate);
	//
	// }

}
