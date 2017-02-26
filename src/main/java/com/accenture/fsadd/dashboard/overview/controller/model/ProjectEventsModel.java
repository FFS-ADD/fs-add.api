package com.accenture.fsadd.dashboard.overview.controller.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectEventsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2704361605124790341L;
	
	/**
	 * Project Events
	 */
	@JsonProperty("events")
	private List<ProjectEventModel> projectEventModels;

	public List<ProjectEventModel> getProjectEventModels() {
		return projectEventModels;
	}

	public void setProjectEventModels(List<ProjectEventModel> projectEventModels) {
		this.projectEventModels = projectEventModels;
	}
	

}
