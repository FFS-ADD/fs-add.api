package com.accenture.fsadd.web.setting.form;

import java.time.LocalDate;

public class ProjectInfoForm {
	private String id;
	private String projectName;
	private String projectStatus;
	private LocalDate updateDay;
	private LocalDate endDay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public LocalDate getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(LocalDate updateDay) {
		this.updateDay = updateDay;
	}

	public LocalDate getEndDay() {
		return endDay;
	}

	public void setEndDay(LocalDate endDay) {
		this.endDay = endDay;
	}
}
