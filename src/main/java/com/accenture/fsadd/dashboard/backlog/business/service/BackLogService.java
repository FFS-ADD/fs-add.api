package com.accenture.fsadd.dashboard.backlog.business.service;

import java.time.LocalDate;
import java.util.List;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogDailySummaryEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;

/**
 * 
 * BackLog Service
 *
 */
public interface BackLogService {

	/**
	 * Get BackLog summary
	 * 
	 * @return Issue summary
	 */
	BackLogSummaryEntity getSummaryService();

	/**
	 * Get List of opened BackLog
	 * 
	 * @return List of opened BackLog
	 */
	List<BackLogEntity> getAllOpenedBackLog();

	/**
	 * Get the summary information of Backlog between from and to day
	 * 
	 * @param fromDay
	 *            From Day
	 * @param toDay
	 *            To day
	 * @return summary information of Backlog
	 */
	List<BackLogDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay);

	/**
	 * aggregate Summary
	 * 
	 * @param executedDay
	 *            aggregate day
	 */
	void aggregateSummaryService(LocalDate executedDay);

	/**
	 * insert or update a backlog
	 * 
	 * @param backLogEntity
	 *            a backlog
	 */
	void save(BackLogEntity backLogEntity);

	/**
	 * Bulk insert or update backlogs
	 * 
	 * @param backLogList
	 *            list of backlog
	 */
	void save(List<BackLogEntity> backLogList);

}
