package com.accenture.fsadd.dashboard.qa.business.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * 
 * Query Answer Daily Summary 
 *
 */
public class QueryAnswerDailySummaryEntity {
	
	@Id
	private LocalDate day;
	
	/**
	 * Summary Information
	 */
	private QueryAnswerSummaryEntity summaryEntity;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public QueryAnswerSummaryEntity getSummaryEntity() {
		return summaryEntity;
	}

	public void setSummaryEntity(QueryAnswerSummaryEntity summaryEntity) {
		this.summaryEntity = summaryEntity;
	}
	

}
