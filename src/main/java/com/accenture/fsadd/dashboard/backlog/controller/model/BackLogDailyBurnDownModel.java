package com.accenture.fsadd.dashboard.backlog.controller.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Backlog Daily burn down Model
 *
 */
public class BackLogDailyBurnDownModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 433898049334100211L;

	/**
	 * Plan Completed Count
	 */
	@JsonProperty("planed")
	private List<Integer> planCompletedCount;

	/**
	 * Actual Completed Count
	 */
	@JsonProperty("actual")
	private List<Integer> actualCompletedCount;

	public List<Integer> getPlanCompletedCount() {
		return planCompletedCount;
	}

	public void setPlanCompletedCount(List<Integer> planCompletedCount) {
		this.planCompletedCount = planCompletedCount;
	}

	public List<Integer> getActualCompletedCount() {
		return actualCompletedCount;
	}

	public void setActualCompletedCount(List<Integer> actualCompletedCount) {
		this.actualCompletedCount = actualCompletedCount;
	}

}
