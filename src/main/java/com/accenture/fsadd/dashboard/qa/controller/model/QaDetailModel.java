package com.accenture.fsadd.dashboard.qa.controller.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QaDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2704361605124790341L;
	
	/**
	 * QA Daily Summary List
	 */
	@JsonProperty("qaHistory")
	private List<QaDailySummaryModel> dailySummaryList;
	
	/**
	 * Overdue QA list
	 */
	@JsonProperty("overdueQaList")
	private List<QaEntryModel> overduleList;

	public List<QaDailySummaryModel> getDailySummaryList() {
		return dailySummaryList;
	}

	public void setDailySummaryList(List<QaDailySummaryModel> dailySummaryList) {
		this.dailySummaryList = dailySummaryList;
	}

	public List<QaEntryModel> getOverduleList() {
		return overduleList;
	}

	public void setOverduleList(List<QaEntryModel> overduleList) {
		this.overduleList = overduleList;
	}

}
