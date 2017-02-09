package com.accenture.fsadd.extif.redmine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class RedmineSetting {
	
	/**
	 * Redmine Issue URL
	 */
	private final static String ISSUES_URL = "issues.json";
	
	/**
	 * Redmine  URL
	 */
	@Value("${fsadd.redmine.url}")
	private String redmineRootURL;
	
	/**
	 * Redmine issues collection name
	 */
	@Value("${fsadd.redmine.mongodb.collection.issues}")
	private String issueCollectionName;

	public String getIssueCollectionName() {
		return issueCollectionName;
	}

	public void setIssueCollectionName(String issueCollectionName) {
		this.issueCollectionName = issueCollectionName;
	}

	public String getRedmineRootURL() {
		return redmineRootURL;
	}

	public void setRedmineRootURL(String redmineURL) {
		this.redmineRootURL = redmineURL;
	}
	
	public String getIssueURL(){
		return this.redmineRootURL + "/" + ISSUES_URL;
	}
}
