package com.accenture.fsadd.dashboard.backlog.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QA Overview Model
 *
 */
public class BackLogOverviewModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3136081297418267247L;

	/**
	 * Initial Estimated Hours
	 */
	@JsonProperty("initValue")
	private int initEstimatedHours;

	/**
	 * Plan Estimated Hours
	 */
	@JsonProperty("planedValue")
	private int PlannedEstimatedHours;

	/**
	 * ReportedHours
	 */
	@JsonProperty("reportedValue")
	private int reportedHours;

	/**
	 * Total
	 */
	@JsonProperty("backLogTotal")
	private int totalCount;

	/**
	 * Closed count
	 */
	@JsonProperty("completed")
	private int closedCount;

	/**
	 * Warning Status Or not
	 */
	@JsonProperty("balanceStatus")
	private boolean warningStatus = false;

	public int getInitEstimatedHours() {
		return initEstimatedHours;
	}

	public void setInitEstimatedHours(int initEstimatedHours) {
		this.initEstimatedHours = initEstimatedHours;
	}

	public int getPlannedEstimatedHours() {
		return PlannedEstimatedHours;
	}

	public void setPlannedEstimatedHours(int plannedEstimatedHours) {
		PlannedEstimatedHours = plannedEstimatedHours;
	}

	public int getReportedHours() {
		return reportedHours;
	}

	public void setReportedHours(int reportedHours) {
		this.reportedHours = reportedHours;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}

	public boolean isWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(boolean warningStatus) {
		this.warningStatus = warningStatus;
	}

}
