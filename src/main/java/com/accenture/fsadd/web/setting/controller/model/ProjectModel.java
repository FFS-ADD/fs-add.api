package com.accenture.fsadd.web.setting.controller.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ProjectModel {
    @Id
    private String id;
    private String projectName;
    private String projectStatus;
    private Date updateDay;
    private Date endDay;

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
	public Date getUpdateDay() {
		return updateDay;
	}
	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
}
