package com.accenture.fsadd.dashboard.overview.business.service;

import java.time.LocalDate;
import java.util.List;

import com.accenture.fsadd.dashboard.issues.business.entity.IssueDailySummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;

/**
 * 
 * Overview Service
 *
 */
public interface OverviewService {

	/**
	 * Get Overview
	 * 
	 * @return Overview Information
	 */
	OverviewEntity getOverview();

	/**
	 * Get all events
	 * 
	 * @return All events
	 */
	List<OverviewEventEntity> getAllEvents();

	/**
	 * Get events between from and to day
	 * 
	 * @param fromDay
	 *            From Day
	 * @param toDay
	 *            To day
	 * @return events between from and to day
	 */
	List<OverviewEventEntity> getEventsBetween(LocalDate fromDay, LocalDate toDay);

	/**
	 * aggregate Summary
	 * 
	 * @param executeDate
	 *            Aggregate Date
	 */
	void aggregateService(LocalDate executeDate);

	/**
	 * insert or update a Event
	 * 
	 * @param overviewEventEntity
	 *            a Event
	 */
	void insertEvent(OverviewEventEntity overviewEventEntity);

	/**
	 * Bulk insert or update events
	 * 
	 * @param overviewEventEntityList
	 *            list of events
	 */
	void insertEvent(List<OverviewEventEntity> overviewEventEntityList);

}
