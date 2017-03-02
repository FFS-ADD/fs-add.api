package com.accenture.fsadd.dashboard.task.controller.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * QA Daily Summary Model
 *
 */
public class TaskDailySummaryModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 433898049334100211L;
	
	/**
	 * Date of the summary 
	 */
	private LocalDate day;
	
	/**
	 * Date of the summary 
	 */
	@JsonProperty("date")
	private String dayAsString;

	/**
	 * On Schedule Count
	 */
	@JsonProperty("onSchedule")
	private int onSheduleCount;

	/**
	 * delay count
	 */
	@JsonProperty("delay")
	private int delayCount;

	/**
	 * Pending count
	 */
	@JsonProperty("pending")
	private int pendingCount;

	/**
	 * Closed count
	 */
	@JsonProperty("closed")
	private int closedCount;

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public String getDayAsString() {
		return dayAsString;
	}

	public void setDayAsString(String dayAsString) {
		this.dayAsString = dayAsString;
	}

	public int getOnSheduleCount() {
		return onSheduleCount;
	}

	public void setOnSheduleCount(int onSheduleCount) {
		this.onSheduleCount = onSheduleCount;
	}

	public int getDelayCount() {
		return delayCount;
	}

	public void setDelayCount(int delayCount) {
		this.delayCount = delayCount;
	}

	public int getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}

	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}


}
