package com.accenture.fsadd.dashboard.task.business.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * 
 * Task Daily Summary 
 *
 */
public class TaskDailySummaryEntity {
	
	@Id
	private LocalDate day;
	
	/**
	 * Summary Information
	 */
	private TaskSummaryEntity summaryEntity;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public TaskSummaryEntity getSummaryEntity() {
		return summaryEntity;
	}

	public void setSummaryEntity(TaskSummaryEntity summaryEntity) {
		this.summaryEntity = summaryEntity;
	}
	

}
