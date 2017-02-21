package com.accenture.fsadd.dashboard.backlog.controller.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity.Stage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BackLogEntryModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2856888979821810011L;

	/**
	 * ID
	 */
	@JsonProperty("backLogId")
	private BigInteger id;

	/**
	 * title
	 */
	@JsonProperty("ticket")
	private String title;

	/**
	 * owner
	 */
	@JsonProperty("owner")
	private String owner;

	/**
	 * Done Ratio
	 */
	@JsonProperty("progress")
	private int doneRatio;

	/**
	 * Stage
	 */
	@JsonProperty("stage")
	private Stage stage;

	/**
	 * Priority As String
	 */
	@JsonProperty("priority")
	private String priorityAsString;

	/**
	 * Planned Start Date
	 */
	@JsonProperty("startDay")
	private String planedStartDateAsString;

	/**
	 * Planned End Date As String
	 */
	@JsonProperty("endDay")
	private String planedEndDateAsString;

	/**
	 * Estimate Hours
	 */
	@JsonProperty("estimate")
	private int estimatedHours;

	/**
	 * Actual Hours
	 */
	@JsonProperty("reported")
	private int actualHours;

	/**
	 * Actual Hours
	 */
	@JsonProperty("difference")
	private int diffHours;

	/**
	 * Status of BackLog
	 */
	@JsonProperty("status")
	private boolean status;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(int doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public String getPriorityAsString() {
		return priorityAsString;
	}

	public void setPriorityAsString(String priorityAsString) {
		this.priorityAsString = priorityAsString;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public int getActualHours() {
		return actualHours;
	}

	public void setActualHours(int actualHours) {
		this.actualHours = actualHours;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPlanedStartDateAsString() {
		return planedStartDateAsString;
	}

	public void setPlanedStartDateAsString(String planedStartDateAsString) {
		this.planedStartDateAsString = planedStartDateAsString;
	}

	public String getPlanedEndDateAsString() {
		return planedEndDateAsString;
	}

	public void setPlanedEndDateAsString(String planedEndDateAsString) {
		this.planedEndDateAsString = planedEndDateAsString;
	}

	public int getDiffHours() {
		return diffHours;
	}

	public void setDiffHours(int diffHours) {
		this.diffHours = diffHours;
	}

}
