package com.accenture.fsadd.dashboard.issues.controller.Model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueDailySummaryModel implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	@JsonProperty("news")
	private int newCount;

	/**
	 * In progressing count
	 */
	@JsonProperty("inProgress")
	private int inProgressIngCount;

	/**
	 * Fixed count
	 */
	@JsonProperty("fixed")
	private int fixedCount;
	/**
	 * Fixed count
	 */
	@JsonProperty("retesting")
	private int retestingCount;
	/**
	 * Closed count
	 */
	@JsonProperty("close")
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
	public int getFixedCount() {
		return fixedCount;
	}
	public void setFixedCount(int fixedCount) {
		this.fixedCount = fixedCount;
	}
	public int getRetestingCount() {
		return retestingCount;
	}
	public void setRetestingCount(int retestingCount) {
		this.retestingCount = retestingCount;
	}
	public int getClosedCount() {
		return closedCount;
	}
	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}
}
