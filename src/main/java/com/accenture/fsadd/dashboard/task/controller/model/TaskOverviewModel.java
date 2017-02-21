package com.accenture.fsadd.dashboard.task.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QA Overview Model
 *
 */
public class TaskOverviewModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3136081297418267247L;

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
