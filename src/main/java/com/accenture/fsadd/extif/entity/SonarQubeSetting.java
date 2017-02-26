package com.accenture.fsadd.extif.entity;

import com.accenture.fsadd.common.FsaddConstant;

public class SonarQubeSetting {
	private String rootUrl;
	private String measuresUrl;
	private String projectKey;
	private String dashboardCollectionName;
	private String issuesCollectionName;
	private String sonarqubeUrl;
	
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public String getMeasuresUrl() {
		return measuresUrl;
	}
	public void setMeasuresUrl(String measuresUrl) {
		this.measuresUrl = measuresUrl;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	public String getDashboardCollectionName() {
		return dashboardCollectionName;
	}
	public void setDashboardCollectionName(String dashboardCollectionName) {
		this.dashboardCollectionName = dashboardCollectionName;
	}
	public String getIssuesCollectionName() {
		return issuesCollectionName;
	}
	public void setIssuesCollectionName(String issuesCollectionName) {
		this.issuesCollectionName = issuesCollectionName;
	}
	public String getSonarqubeUrl() {
		return this.rootUrl + this.measuresUrl+this.projectKey;
	}
	public void setSonarqubeUrl(String sonarqubeUrl) {
		this.sonarqubeUrl = sonarqubeUrl;
	}
}
