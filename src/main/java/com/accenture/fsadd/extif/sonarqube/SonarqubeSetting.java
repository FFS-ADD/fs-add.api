package com.accenture.fsadd.extif.sonarqube;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class SonarqubeSetting {
	
	/**
	 * Sonarqube issues URL
	 */
	@Value("${fsadd.sonarqube.issues.urll}")
	private String sonarqubeIssuesUrl;
	
	private String sonarqubeServeUrl;
	
	private String projectKey;

	/**
	 * Sonarqube issues collection name
	 */
	@Value("${fsadd.sonarqube.mongodb.collection.issues}")
	private String issueCollectionName;

	public String getSonarqubeIssuesUrl() {
		return this.sonarqubeIssuesUrl+this.sonarqubeIssuesUrl+this.projectKey;
	}

	public void setSonarqubeIssuesUrl(String sonarqubeIssuesUrl) {
		this.sonarqubeIssuesUrl = sonarqubeIssuesUrl;
	}

	public String getSonarqubeServeUrl() {
		return sonarqubeServeUrl;
	}

	public void setSonarqubeServeUrl(String sonarqubeServeUrl) {
		this.sonarqubeServeUrl = sonarqubeServeUrl;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getIssueCollectionName() {
		return issueCollectionName;
	}

	public void setIssueCollectionName(String issueCollectionName) {
		this.issueCollectionName = issueCollectionName;
	}

}
