package com.accenture.fsadd.dashboard.task.business.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.accenture.fsadd.dashboard.task.business.entity.TaskDailySummaryEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;

/**
 * 
 * Task Service
 *
 */
public interface TaskService {

	/**
	 * Get Task summary
	 * 
	 * @return Task summary
	 */
	TaskSummaryEntity getSummaryService();

	/**
	 * Get List of Delay Task
	 * 
	 * @return List of Delay Task
	 */
	List<TaskEntity> getAllDelayTask();

	/**
	 * Get the summary information of Task between from and to day
	 * 
	 * @param fromDay
	 *            From Day
	 * @param toDay
	 *            To day
	 * @return summary information of Task
	 */
	List<TaskDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay);

	/**
	 * aggregate Summary
	 * @param executedDay aggregate day
	 */
	void aggregateSummaryService(LocalDate executedDay);

	/**
	 * insert or update a Task
	 * 
	 * @param issueEntity
	 *            a Issue 
	 */
	void save(TaskEntity taskEntity);

	/**
	 * Bulk insert or update tasks
	 * 
	 * @param issueList
	 *            list of issue
	 */
	void save(List<TaskEntity> issueList);
	
	/**
	 * Delete the task entity by Id
	 * @param id Id of specified taskEntity
	 */
	void delete(BigInteger id);
	
	/**
	 * Update the backLog Id of specified task 
	 * @param id task Id 
	 * @param backLogId backLog Id
	 */
	void updateBackLogId(BigInteger id, BigInteger backLogId);
	
	/**
	 * Get all task under the backLog
	 * @param backlogId BackLog ID
	 * @return all task under the backLogId
	 */
	List<TaskEntity> getBacklogTask(BigInteger backlogId);

}
