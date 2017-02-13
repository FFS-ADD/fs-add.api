package com.accenture.fsadd.extif.sonarqube;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.accenture.fsadd.common.FsaddConstant;

@Component
@ConfigurationProperties
public class SonarqubeSetting {
	
	/**
	 * Sonarqube issues URL
	 */
	@Value("${fsadd.sonarqube.url}")
	private String sonarqubeUrl;
	
	private String projectKey;

	/**
	 * Sonarqube issues collection name
	 */
	@Value("${fsadd.sonarqube.mongodb.collection.issues}")
	private String issueCollectionName;

	public String getSonarqubeUrl() {
		return sonarqubeUrl;
	}

	public void setSonarqubeUrl(String sonarqubeUrl) {
		this.sonarqubeUrl = sonarqubeUrl;
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

	public String getSonarqubeMeasuresUrl() {
		return this.sonarqubeUrl + FsaddConstant.SONARQUBE_MEASURES_URL;
	}

}
