package com.accenture.fsadd.sonar.controller.model;

import com.accenture.fsadd.common.mvc.model.CommonModel;

public class CoverageModel extends CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String coverage;
	
	private String tests;
	
	private String success;
	
	private String Failures;

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
		return Failures;
	}

	public void setFailures(String failures) {
		Failures = failures;
	}
	
}
