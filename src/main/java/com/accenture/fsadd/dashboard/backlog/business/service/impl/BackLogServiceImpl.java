package com.accenture.fsadd.dashboard.backlog.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogDailySummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity.Status;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.repository.BackLogDailySummaryRepository;
import com.accenture.fsadd.dashboard.backlog.business.repository.BackLogRepository;
import com.accenture.fsadd.dashboard.backlog.business.repository.BackLogSummaryRepository;
import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;

@Service
public class BackLogServiceImpl implements BackLogService {

	@Autowired
	private BackLogDailySummaryRepository backLogDailySummaryRepository;

	@Autowired
	private BackLogRepository backLogRepository;

	@Autowired
	private TaskService taskSevice;

	@Autowired
	private BackLogSummaryRepository backLogSummaryRepository;

	@Override
	public BackLogSummaryEntity getSummaryService() {
		return backLogSummaryRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<BackLogEntity> getAllOpenedBackLog() {
		List<BackLogEntity> openedBackLogs = backLogRepository
				.findByNotInStatus(new Status[] { Status.CLOSED, Status.PENDING });
		if (openedBackLogs == null) {
			openedBackLogs = new ArrayList<>();
		}
		openedBackLogs.sort((opendBackLog1, opendBackLog2) -> {
			int result = opendBackLog2.getPriority().compareTo(opendBackLog1.getPriority());
			if (result == 0) {
				result = opendBackLog1.getPlanedEndDate().compareTo(opendBackLog2.getPlanedEndDate());
			}
			return result;
		});
		return openedBackLogs;
	}

	@Override
	public List<BackLogDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay) {
		List<BackLogDailySummaryEntity> dailySummaryList = backLogDailySummaryRepository
				.findByDayBetween(fromDay.minusDays(1), toDay.plusDays(1));
		if (dailySummaryList == null) {
			dailySummaryList = new ArrayList<>();
		}
		dailySummaryList.sort((dailySummary1, dailySummary2) -> {
			return dailySummary1.getDay().compareTo(dailySummary2.getDay());
		});
		return dailySummaryList;
	}

	@Override
	public void aggregateSummaryService(LocalDate executedDay) {
		// Get all BackLogEnity
		List<BackLogEntity> backLogEnityList = backLogRepository.findAll();
		BackLogDailySummaryEntity dailySummaryEntity = new BackLogDailySummaryEntity();
		BackLogSummaryEntity summaryEntity = new BackLogSummaryEntity();
		dailySummaryEntity.setDay(executedDay);
		dailySummaryEntity.setSummaryEntity(summaryEntity);
		// Loop the backLogEnity
		for (BackLogEntity backLogEnity : backLogEnityList) {
			// find all task of the backlog
			List<TaskEntity> taskEntityList = taskSevice.getBacklogTask(backLogEnity.getId());
			// Plan start and end date
			LocalDate plannedStartDate = null;
			LocalDate plannedEndDate = null;
			// actual Start and end date
			LocalDate actualStartDate = null;
			LocalDate actualEndDate = null;
			// Last Stage task
			TaskEntity lastStageTaskEnity = null;
			// Current Stage Task
			TaskEntity currentStageTaskEntity = null;
			// Pending taskCount
			int pendingCount = 0;
			// Estimate Hours
			int estimatedHours = 0;
			// actualHours Hours
			int actualHours = 0;
			// earned Hours
			int earnedHours = 0;

			for (TaskEntity taskEntity : taskEntityList) {
				// Pending task will exclude from calculation
				if (taskEntity.getStatus() == TaskEntity.Status.PENDING) {
					pendingCount++;
					continue;
				}
				// Estimate Hours
				estimatedHours = estimatedHours + taskEntity.getEstimatedHours();
				// Actual Hours
				actualHours = actualHours + taskEntity.getActualHours();
				// Plan start and end date
				plannedStartDate = getPlannedStartDate(taskEntity, plannedStartDate);
				plannedEndDate = getPlannedEndDate(taskEntity, plannedEndDate);
				// Actual Start and End Date
				actualStartDate = getActualStartDate(taskEntity, actualStartDate);
				actualEndDate = getActualEndDate(taskEntity, actualEndDate);
				// Get the Last Stage Task
				lastStageTaskEnity = getLastStageTaskEntity(taskEntity, lastStageTaskEnity);

				if (taskEntity.getStatus() != TaskEntity.Status.CLOSED) {
					// Get the current stage task
					currentStageTaskEntity = getCurrentStageTaskEntity(taskEntity, currentStageTaskEntity);
				} else {
					// Closed task
					earnedHours = earnedHours + taskEntity.getEstimatedHours();
				}
			}
			// Child task is existed(Should not be exists!)
			if (!taskEntityList.isEmpty()) {
				// Set the backLog Entity
				setBackLogEntityByCalculatedValue(backLogEnity, taskEntityList.size(), executedDay, plannedStartDate,
						plannedEndDate, pendingCount, currentStageTaskEntity, actualStartDate, actualEndDate,
						estimatedHours, actualHours, lastStageTaskEnity, earnedHours);
			}
			// Calculate Summary
			calculateSummary(backLogEnity, summaryEntity);
			// Save the BackLog Entity
			this.save(backLogEnity);
		}
		// Save Summary
		backLogDailySummaryRepository.save(dailySummaryEntity);
		backLogSummaryRepository.save(summaryEntity);

	}

	@Override
	public void save(BackLogEntity backlogEntity) {
		backLogRepository.save(backlogEntity);

	}

	@Override
	public void save(List<BackLogEntity> backlogEntityList) {
		backlogEntityList.forEach((entity) -> this.save(entity));

	}

	private void setStageByTaskType(BackLogEntity backLogEnity, TaskEntity taskEntity) {
		switch (taskEntity.getType()) {
		case ANALYSIS:
			backLogEnity.setStage(BackLogEntity.Stage.ANALYSIS);
			break;
		case DESGIN:
			backLogEnity.setStage(BackLogEntity.Stage.DESGIN);
			break;
		case DEVELOPMENT:
			backLogEnity.setStage(BackLogEntity.Stage.DEPLOYMENT);
			break;
		case TEST:
			backLogEnity.setStage(BackLogEntity.Stage.TEST);
			break;
		case DEPLOYMENT:
			backLogEnity.setStage(BackLogEntity.Stage.DEPLOYMENT);
			break;
		default:
			backLogEnity.setStage(BackLogEntity.Stage.DEPLOYMENT);
			break;
		}

	}

	private LocalDate getPlannedStartDate(TaskEntity taskEntity, LocalDate plannedStartDate) {

		if (taskEntity.getPlanedStartDate() != null) {
			if (plannedStartDate == null || taskEntity.getPlanedStartDate().isBefore(plannedStartDate)) {
				plannedStartDate = taskEntity.getPlanedStartDate();
			}

		}
		return plannedStartDate;
	}

	private LocalDate getPlannedEndDate(TaskEntity taskEntity, LocalDate plannedEndDate) {
		if (taskEntity.getPlanedEndDate() != null) {
			if (plannedEndDate == null || taskEntity.getPlanedEndDate().isAfter(plannedEndDate)) {
				plannedEndDate = taskEntity.getPlanedEndDate();
			}
		}
		return plannedEndDate;
	}

	private LocalDate getActualStartDate(TaskEntity taskEntity, LocalDate actualStartDate) {

		if (taskEntity.getActualStartDate() != null) {
			if (actualStartDate == null || taskEntity.getActualStartDate().isBefore(actualStartDate)) {
				actualStartDate = taskEntity.getActualStartDate();
			}

		}
		return actualStartDate;
	}

	private LocalDate getActualEndDate(TaskEntity taskEntity, LocalDate actualEndDate) {

		if (taskEntity.getActualEndDate() != null) {
			if (actualEndDate == null || taskEntity.getActualEndDate().isAfter(actualEndDate)) {
				actualEndDate = taskEntity.getActualEndDate();
			}
		}
		return actualEndDate;
	}

	private TaskEntity getLastStageTaskEntity(TaskEntity taskEntity, TaskEntity lastStageTaskEnity) {
		// Get the Last Stage Task
		if (lastStageTaskEnity == null) {
			lastStageTaskEnity = taskEntity;
		} else if (taskEntity.getType().compareTo(lastStageTaskEnity.getType()) > 0) {
			lastStageTaskEnity = taskEntity;
		}
		return lastStageTaskEnity;
	}

	private TaskEntity getCurrentStageTaskEntity(TaskEntity taskEntity, TaskEntity currentStageTaskEntity) {
		if (currentStageTaskEntity == null) {
			currentStageTaskEntity = taskEntity;
		} else if (taskEntity.getType().compareTo(currentStageTaskEntity.getType()) < 0) {
			currentStageTaskEntity = taskEntity;
		}
		return currentStageTaskEntity;
	}

	private void setBackLogEntityByCalculatedValue(BackLogEntity backLogEnity, int childTaskSize, LocalDate executedDay,
			LocalDate plannedStartDate, LocalDate plannedEndDate, int pendingCount, TaskEntity currentStageTaskEntity,
			LocalDate actualStartDate, LocalDate actualEndDate, int estimatedHours, int actualHours,
			TaskEntity lastStageTaskEnity, int earnedHours) {
		// Set status and actual start and end
		if (childTaskSize == pendingCount) {
			backLogEnity.setStatus(BackLogEntity.Status.PENDING);
		} else if (currentStageTaskEntity == null) {
			backLogEnity.setStatus(BackLogEntity.Status.CLOSED);
			backLogEnity.setActualStartDate(actualStartDate);
			backLogEnity.setActualEndDate(actualEndDate);
			backLogEnity.setClosedOn(actualEndDate);
		} else if (actualStartDate != null && !actualStartDate.isAfter(executedDay)) {
			backLogEnity.setStatus(BackLogEntity.Status.INPROGRESS);
			backLogEnity.setActualStartDate(actualStartDate);
			backLogEnity.setActualEndDate(null);
		} else {
			backLogEnity.setStatus(BackLogEntity.Status.NEW);
			backLogEnity.setActualStartDate(null);
			backLogEnity.setActualEndDate(null);
		}
		backLogEnity.setEstimatedHours(estimatedHours);

		// We will not calculate the other value if backlog is pending
		if (backLogEnity.getStatus() == BackLogEntity.Status.PENDING) {
			return;
		}
		if (backLogEnity.getStatus() != BackLogEntity.Status.CLOSED && plannedEndDate.isBefore(executedDay)) {
			backLogEnity.setStatus(BackLogEntity.Status.DELAY);
		}

		// Set Plan start Day and end Day
		backLogEnity.setPlanedStartDate(plannedStartDate);
		backLogEnity.setPlanedEndDate(plannedEndDate);

		// Set Done Ratio
		backLogEnity.setActualHours(actualHours);
		if (estimatedHours != 0) {
			backLogEnity.setDoneRatio(earnedHours / estimatedHours * 100);
		} else {
			backLogEnity.setDoneRatio(0);
		}

		// Set Stage
		if (currentStageTaskEntity != null) {
			setStageByTaskType(backLogEnity, currentStageTaskEntity);
		} else {
			setStageByTaskType(backLogEnity, lastStageTaskEnity);
		}
	}

	private void calculateSummary(BackLogEntity backLogEnity, BackLogSummaryEntity summaryEntity) {
		summaryEntity.setInitEstimatedHours(summaryEntity.getInitEstimatedHours() + backLogEnity.getEstimatedHours());
		if (backLogEnity.getStatus() != BackLogEntity.Status.PENDING) {
			summaryEntity.setPlannedEstimatedHours(
					summaryEntity.getPlannedEstimatedHours() + backLogEnity.getEstimatedHours());
			summaryEntity.setReportedHours(summaryEntity.getReportedHours() + backLogEnity.getActualHours());
		}
		summaryEntity.setTotalCount(summaryEntity.getTotalCount() + 1);
		switch (backLogEnity.getStatus()) {
		case NEW:
			summaryEntity.setNewCount(summaryEntity.getNewCount() + 1);
			break;
		case INPROGRESS:
			summaryEntity.setInProgressIngCount(summaryEntity.getInProgressIngCount() + 1);
			break;
		case PENDING:
			summaryEntity.setPendingCount(summaryEntity.getPendingCount() + 1);
			break;
		case CLOSED:
			summaryEntity.setClosedCount(summaryEntity.getClosedCount() + 1);
			break;
		case DELAY:
			summaryEntity.setDelayCount(summaryEntity.getDelayCount() + 1);
			break;
		}

	}

}
