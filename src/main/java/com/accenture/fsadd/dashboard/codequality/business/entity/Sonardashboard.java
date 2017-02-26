package com.accenture.fsadd.dashboard.codequality.business.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Sonardashboard {
	
	
	@Id
	private ObjectId id;
	
	private String projectKey;
	
	private String createDate;
	
	private String qualityGateStatus;
	
	private String vulneralilities;
	
	private String bugs;
	
	private String codeSmells;
	
	private String codeLines;
	
	private String line;
	
	private String statement;
	
	private String file;
	
	private String coverage;
	
	private String tests;
	
	private String success;
	
	private String failures;
	
	private String duplication;
	
	private String dLine;
	
	private String blocks;
	
	private String dFile;

	public Sonardashboard(){
		vulneralilities="0";
		bugs = "0";
		codeSmells="0";
		codeLines="0";
		line="0";
		statement="0";
		file="0";
		coverage="0";
		tests="0";
		success="0";
		failures="0";
		duplication="0";
		dLine="0";
		blocks="0";
		dFile="0";
	}
	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

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
		return codeLines;
	}

	public void setCodeLines(String codeLines) {
		this.codeLines = codeLines;
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
		return failures;
	}

	public void setFailures(String failures) {
		this.failures = failures;
	}

	public String getDuplication() {
		return duplication;
	}

	public void setDuplication(String duplication) {
		this.duplication = duplication;
	}

	public String getdLine() {
		return dLine;
	}

	public void setdLine(String dLine) {
		this.dLine = dLine;
	}

	public String getBlocks() {
		return blocks;
	}

	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}

	public String getdFile() {
		return dFile;
	}

	public void setdFile(String dFile) {
		this.dFile = dFile;
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


}
