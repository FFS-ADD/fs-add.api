package com.accenture.fsadd.dashboard.backlog.business.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * 
 * BackLog Daily Summary 
 *
 */
public class BackLogDailySummaryEntity {
	
	@Id
	private LocalDate day;
	
	/**
	 * Summary Information
	 */
	private BackLogSummaryEntity summaryEntity;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public BackLogSummaryEntity getSummaryEntity() {
		return summaryEntity;
	}

	public void setSummaryEntity(BackLogSummaryEntity summaryEntity) {
		this.summaryEntity = summaryEntity;
	}
	

}
