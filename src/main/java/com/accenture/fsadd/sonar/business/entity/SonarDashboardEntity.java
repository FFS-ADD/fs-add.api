package com.accenture.fsadd.sonar.business.entity;

public class SonarDashboardEntity {
	private String qualityGateStatus;
	
	private String vulneralilities;
	
	private String bugs;
	
	private String codeSmells;
	
	private String CodeLines;
	
	private String line;
	
	private String statement;
	
	private String file;
	
	private String coverage;
	
	private String tests;
	
	public String getQualityGateStatus() {
		return qualityGateStatus;
	}

	public void setQualityGateStatus(String qualityGateStatus) {
		this.qualityGateStatus = qualityGateStatus;
	}

	public String getVulneralilities() {
		return vulneralilities;
	}

	public void setVulneralilities(String vulneralilities) {
		this.vulneralilities = vulneralilities;
	}

	public String getBugs() {
		return bugs;
	}

	public void setBugs(String bugs) {
		this.bugs = bugs;
	}

	public String getCodeSmells() {
		return codeSmells;
	}

	public void setCodeSmells(String codeSmells) {
		this.codeSmells = codeSmells;
	}

	public String getCodeLines() {
		return CodeLines;
	}

	public void setCodeLines(String codeLines) {
		CodeLines = codeLines;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

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

	private String success;
	
	private String Failures;
	
}
