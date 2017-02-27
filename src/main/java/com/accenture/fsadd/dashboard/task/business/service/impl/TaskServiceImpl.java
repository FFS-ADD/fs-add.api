package com.accenture.fsadd.dashboard.task.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.task.business.entity.TaskDailySummaryEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity.Status;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;
import com.accenture.fsadd.dashboard.task.business.repository.TaskDailySummaryRepository;
import com.accenture.fsadd.dashboard.task.business.repository.TaskRepository;
import com.accenture.fsadd.dashboard.task.business.repository.TaskSummaryRepository;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	// TODO
	private final int WORK_HOURS = 9;

	@Autowired
	private TaskDailySummaryRepository taskDailySummaryRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskSummaryRepository taskSummaryRepository;

	@Override
	public TaskSummaryEntity getSummaryService() {
		return taskSummaryRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<TaskEntity> getAllDelayTask() {
		List<TaskEntity> dalayTaskList = taskRepository.findAllDelayTasks(LocalDate.now());
		if (dalayTaskList == null) {
			dalayTaskList = new ArrayList<>();
		}
		dalayTaskList.sort((dalayTask1, dalayTask2) -> {
			int result = dalayTask2.getPriority().compareTo(dalayTask1.getPriority());
			if (result == 0) {
				result = dalayTask1.getPlanedEndDate().compareTo(dalayTask2.getPlanedEndDate());
			}
			return result;
		});
		return dalayTaskList;
	}

	@Override
	public List<TaskDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay) {
		List<TaskDailySummaryEntity> dailySummaryList = taskDailySummaryRepository
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
		// Get all task
		List<TaskEntity> taskList = taskRepository.findAll();
		// Get the daily summary Entity
		TaskDailySummaryEntity daily = new TaskDailySummaryEntity();
		daily.setDay(executedDay);
		TaskSummaryEntity dailySummary = new TaskSummaryEntity();
		daily.setSummaryEntity(dailySummary);
		for (TaskEntity task : taskList) {
			// Setting the count in according to status
			if (task.getStatus() == Status.NEW || task.getStatus() == Status.INPROGRESS) {
				if (executedDay.isAfter(task.getPlanedEndDate())) {
					dailySummary.setDelayCount(dailySummary.getDelayCount() + 1);
				} else {
					dailySummary.setOnSheduleCount(dailySummary.getOnSheduleCount() + 1);
				}

			}
			if (task.getStatus() == Status.CLOSED) {
				dailySummary.setClosedCount(dailySummary.getClosedCount() + 1);
			}
			if (task.getStatus() == Status.PENDING) {
				dailySummary.setPendingCount(dailySummary.getPendingCount() + 1);
			}
			// normalize the task entity
			if (normalizeTaskEntity(task)) {
				this.save(task);
			}

		}
		dailySummary.setTotalCount(dailySummary.getDelayCount() + dailySummary.getOnSheduleCount()
				+ dailySummary.getPendingCount() + dailySummary.getClosedCount());
		// Save the daily Summary
		taskDailySummaryRepository.save(daily);
		// Save the Summary
		taskSummaryRepository.save(dailySummary);
	}

	@Override
	public void save(TaskEntity taskEnity) {
		taskRepository.save(taskEnity);

	}

	@Override
	public void save(List<TaskEntity> issueEnityList) {
		issueEnityList.forEach((entity) -> this.save(entity));

	}

	@Override
	public List<TaskEntity> getBacklogTask(BigInteger backlogId) {
		List<TaskEntity> backLogTaskList = taskRepository.findByBackLogId(backlogId);
		if (backLogTaskList == null) {
			backLogTaskList = new ArrayList<>();
		}
		return backLogTaskList;
	}

	private boolean normalizeTaskEntity(TaskEntity task) {
		// normalize the task entity
		boolean needSaveEntity = false;
		if (task.getEstimatedHours() == 0) {
			if (task.getPlanedStartDate() != null && task.getPlanedEndDate() != null) {
				long days = ChronoUnit.DAYS.between(task.getPlanedStartDate(), task.getPlanedEndDate()) + 1;
				long estimateHours = days * WORK_HOURS;
				task.setEstimatedHours((int) estimateHours);
				needSaveEntity = true;
			}
		}
		if (task.getActualHours() == 0) {
			if (task.getDoneRatio() != 0) {
				task.setActualHours(task.getEstimatedHours() * task.getDoneRatio() / 100);
				needSaveEntity = true;
			}
		}
		if (task.getStatus() == Status.CLOSED) {
			if (task.getActualEndDate() == null) {
				task.setActualEndDate(task.getUpdatedOn());
				needSaveEntity = true;
			}
		}
		if (task.getStatus() == Status.INPROGRESS) {
			if (task.getActualStartDate() == null) {
				task.setActualStartDate(task.getUpdatedOn());
				needSaveEntity = true;
			}
		}
		return needSaveEntity;
	}

	@Override
	public void delete(BigInteger id) {
		taskRepository.delete(id);

	}

	@Override
	public void updateBackLogId(BigInteger id, BigInteger backLogId) {
		TaskEntity taskEntity = taskRepository.findOne(id);
		taskEntity.setBackLogId(backLogId);
		taskRepository.save(taskEntity);

	}

}
