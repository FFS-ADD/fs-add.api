package com.accenture.fsadd.dashboard.qa.business.service;

import java.time.LocalDate;
import java.util.List;

import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerDailySummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;

/**
 * 
 * Query Answer Service
 *
 */
public interface QueryAnswerService {

	/**
	 * Get query answer summary
	 * 
	 * @return query answer summary
	 */
	QueryAnswerSummaryEntity getSummaryService();

	/**
	 * Get List of overdue query answer list.
	 * 
	 * @return List of overdue query answer list
	 */
	List<QueryAnswerEntity> getAllOverDueQAService();

	/**
	 * Get the summary information of QA between from and to day
	 * 
	 * @param fromDay
	 *            From Day
	 * @param toDay
	 *            To day
	 * @return summary information of QA
	 */
	List<QueryAnswerDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay);

	/**
	 * aggregate Summary
	 * @param executedDate Executed Date
	 */
	void aggregateSummaryService(LocalDate executedDate);

	/**
	 * insert or update a Query answer information
	 * 
	 * @param queryAnswerEntity
	 *            Query answer information
	 */
	void save(QueryAnswerEntity queryAnswerEntity);

	/**
	 * Bulk insert or update a Query answer information
	 * 
	 * @param queryAnswerEntityList
	 *            list of query answer information
	 */
	void save(List<QueryAnswerEntity> queryAnswerEntityList);

}
