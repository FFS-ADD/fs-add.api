package com.accenture.fsadd.dashboard.qa.controller.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * QA Daily Summary Model
 *
 */
public class QaDailySummaryModel implements Serializable{

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
	 * New count
	 */
	@JsonProperty("new")
	private int newCount;

	/**
	 * In progressing count
	 */
	@JsonProperty("inProgress")
	private int inProgressIngCount;

	/**
	 * Overdue count
	 */
	@JsonProperty("overdue")
	private int overdueCount;

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

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public int getInProgressIngCount() {
		return inProgressIngCount;
	}

	public void setInProgressIngCount(int inProgressIngCount) {
		this.inProgressIngCount = inProgressIngCount;
	}

	public int getOverdueCount() {
		return overdueCount;
	}

	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
	}

	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}

	public String getDayAsString() {
		return dayAsString;
	}

	public void setDayAsString(String dayAsString) {
		this.dayAsString = dayAsString;
	}


}
