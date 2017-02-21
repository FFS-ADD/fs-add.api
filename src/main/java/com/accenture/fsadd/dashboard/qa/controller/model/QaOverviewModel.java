package com.accenture.fsadd.dashboard.qa.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QA Overview Model
 *
 */
public class QaOverviewModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3136081297418267247L;

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

}
