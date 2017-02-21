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
	private IssueDailySummaryModel dailySummary;

	@JsonProperty("bugFixHistoryData")
	private IssueDailySummaryModel dailyFixSummary;

	@JsonProperty("redmindingBugList")
	private List<IssueEntryModle> openedIssueList;

	@JsonProperty("historyDate")
	private List<String> dailyDateAsStringList;

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

	public IssueDailySummaryModel getDailySummary() {
		return dailySummary;
	}

	public void setDailySummary(IssueDailySummaryModel dailySummary) {
		this.dailySummary = dailySummary;
	}

	public IssueDailySummaryModel getDailyFixSummary() {
		return dailyFixSummary;
	}

	public void setDailyFixSummary(IssueDailySummaryModel dailyFixSummary) {
		this.dailyFixSummary = dailyFixSummary;
	}


}
