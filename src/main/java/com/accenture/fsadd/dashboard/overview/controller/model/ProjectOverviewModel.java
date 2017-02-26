package com.accenture.fsadd.dashboard.overview.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project Overview Model
 *
 */
public class ProjectOverviewModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3136081297418267247L;

	/**
	 * Project Name
	 */
	@JsonProperty("projectName")
	private String projectName;


	/**
	 * Last Update Time
	 */
	@JsonProperty("lastUpdateTime")
	private String lastUpdateTime;

	/**
	 * Project Status
	 * 0:Good, 1:Warning, 2:bad
	 */
	@JsonProperty("projectStatus")
	private int projectStatus;
	



	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
