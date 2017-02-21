package com.accenture.fsadd.dashboard.issues.business.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * 
 * Issue Daily Summary 
 *
 */
public class IssueDailySummaryEntity {
	
	@Id
	private LocalDate day;
	
	/**
	 * Summary Information
	 */
	private IssueSummaryEntity summaryEntity;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public IssueSummaryEntity getSummaryEntity() {
		return summaryEntity;
	}

	public void setSummaryEntity(IssueSummaryEntity summaryEntity) {
		this.summaryEntity = summaryEntity;
	}
	

}
