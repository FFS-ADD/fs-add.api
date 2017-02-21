package com.accenture.fsadd.dashboard.issues.controller.Model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueDetailModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1890025718129265627L;
	
	@JsonProperty("bugDetailsHistoryData")
	private List<IssueDailySummaryModel> dailySummaryList;
	
	@JsonProperty("bugFixHistoryData")
	private List<IssueDailySummaryModel> dailyFixSummaryList;
	
	@JsonProperty("redmindingBugList")
	private List<IssueEntryModle> openedIssueList;
	
	@JsonProperty("historyDate")
	private List<String> dailyDateAsStringList;

	public List<IssueDailySummaryModel> getDailySummaryList() {
		return dailySummaryList;
	}

	public void setDailySummaryList(List<IssueDailySummaryModel> dailySummaryList) {
		this.dailySummaryList = dailySummaryList;
	}

	public List<IssueDailySummaryModel> getDailyFixSummaryList() {
		return dailyFixSummaryList;
	}

	public void setDailyFixSummaryList(List<IssueDailySummaryModel> dailyFixSummaryList) {
		this.dailyFixSummaryList = dailyFixSummaryList;
	}

	public List<IssueEntryModle> getOpenedIssueList() {
		return openedIssueList;
	}

	public void setOpenedIssueList(List<IssueEntryModle> openedIssueList) {
		this.openedIssueList = openedIssueList;
	}

	public List<String> getDailyDateAsStringList() {
		return dailyDateAsStringList;
	}

	public void setDailyDateAsStringList(List<String> dailyDateAsStringList) {
		this.dailyDateAsStringList = dailyDateAsStringList;
	}

}
