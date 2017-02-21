package com.accenture.fsadd.dashboard.backlog.controller.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackLogDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2704361605124790341L;
	
	/**
	 * Task Daily Summary List
	 */
	@JsonProperty("backLogCanvasData")
	private BackLogDailyBurnDownModel backLogDailyBurnDownModel;
	
	/**
	 * Overdue Task list
	 */
	@JsonProperty("bugDetailsHistoryData")
	private List<BackLogEntryModel> openBackLogList;
	
	/**
	 * Backlog date List
	 */
	@JsonProperty("historyDate")
	private List<String> detailDateList;

	public BackLogDailyBurnDownModel getBackLogDailyBurnDownModel() {
		return backLogDailyBurnDownModel;
	}

	public void setBackLogDailyBurnDownModel(BackLogDailyBurnDownModel backLogDailyBurnDownModel) {
		this.backLogDailyBurnDownModel = backLogDailyBurnDownModel;
	}

	public List<BackLogEntryModel> getOpenBackLogList() {
		return openBackLogList;
	}

	public void setOpenBackLogList(List<BackLogEntryModel> openBackLogList) {
		this.openBackLogList = openBackLogList;
	}

	public List<String> getDetailDateList() {
		return detailDateList;
	}

	public void setDetailDateList(List<String> detailDateList) {
		this.detailDateList = detailDateList;
	}



}
