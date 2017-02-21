package com.accenture.fsadd.dashboard.issues.controller.Model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueDailySummaryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * News count
	 */
	@JsonProperty("news")
	private List<Integer> newCountList;

	/**
	 * In progressing Count
	 */
	@JsonProperty("inProgress")
	private List<Integer> inProgressingCountList;

	/**
	 * Fixed Count
	 */
	@JsonProperty("fixed")
	private List<Integer> fixedCountList;

	/**
	 * Retesting Count
	 */
	@JsonProperty("reTesting")
	private List<Integer> retestingCountList;

	/**
	 * Closed Count
	 */
	@JsonProperty("closed")
	private List<Integer> closedCountList;

	public List<Integer> getNewCountList() {
		return newCountList;
	}

	public void setNewCountList(List<Integer> newCountList) {
		this.newCountList = newCountList;
	}

	public List<Integer> getInProgressingCountList() {
		return inProgressingCountList;
	}

	public void setInProgressingCountList(List<Integer> inProgressingCountList) {
		this.inProgressingCountList = inProgressingCountList;
	}

	public List<Integer> getFixedCountList() {
		return fixedCountList;
	}

	public void setFixedCountList(List<Integer> fixedCountList) {
		this.fixedCountList = fixedCountList;
	}

	public List<Integer> getRetestingCountList() {
		return retestingCountList;
	}

	public void setRetestingCountList(List<Integer> retestingCountList) {
		this.retestingCountList = retestingCountList;
	}

	public List<Integer> getClosedCountList() {
		return closedCountList;
	}

	public void setClosedCountList(List<Integer> closedCountList) {
		this.closedCountList = closedCountList;
	}
}
