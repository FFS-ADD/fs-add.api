package com.accenture.fsadd.dashboard.task.controller.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2704361605124790341L;
	
	/**
	 * Task Daily Summary List
	 */
	@JsonProperty("taskHistory")
	private List<TaskDailySummaryModel> dailySummaryList;
	
	/**
	 * Overdue Task list
	 */
	@JsonProperty("delayTaskList")
	private List<TaskEntryModel> overduleList;

	public List<TaskDailySummaryModel> getDailySummaryList() {
		return dailySummaryList;
	}

	public void setDailySummaryList(List<TaskDailySummaryModel> dailySummaryList) {
		this.dailySummaryList = dailySummaryList;
	}

	public List<TaskEntryModel> getOverduleList() {
		return overduleList;
	}

	public void setOverduleList(List<TaskEntryModel> overduleList) {
		this.overduleList = overduleList;
	}

}
