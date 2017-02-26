package com.accenture.fsadd.dashboard.codequality.controller.model;

import com.accenture.fsadd.common.mvc.model.CommonModel;

public class CoverageModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String coverage;
	
	private String tests;
	
	private String success;
	
	private String failures;

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getFailures() {
		return failures;
	}

	public void setFailures(String failures) {
		this.failures = failures;
	}
	
}
