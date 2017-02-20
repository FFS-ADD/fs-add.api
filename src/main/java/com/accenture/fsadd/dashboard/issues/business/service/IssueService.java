package com.accenture.fsadd.dashboard.issues.business.service;

import java.time.LocalDate;
import java.util.List;

import com.accenture.fsadd.dashboard.issues.business.entity.IssueDailySummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;

/**
 * 
 * Issue Service
 *
 */
public interface IssueService {

	/**
	 * Get Issue summary
	 * 
	 * @return Issue summary
	 */
	IssueSummaryEntity getSummaryService();

	/**
	 * Get List of opened Issue
	 * 
	 * @return List of opened Issue
	 */
	List<IssueEntity> getAllOpenedIssue();

	/**
	 * Get the summary information of Issue between from and to day
	 * 
	 * @param fromDay
	 *            From Day
	 * @param toDay
	 *            To day
	 * @return summary information of Issue
	 */
	List<IssueDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay);

	/**
	 * aggregate Summary
	 * @param executeDate Execute Date
	 */
	void aggregateSummaryService(LocalDate executeDate);

	/**
	 * insert or update a issue
	 * 
	 * @param issueEntity
	 *            a Issue 
	 */
	void save(IssueEntity issueEntity);

	/**
	 * Bulk insert or update issues
	 * 
	 * @param issueList
	 *            list of issue
	 */
	void save(List<IssueEntity> issueList);

}
