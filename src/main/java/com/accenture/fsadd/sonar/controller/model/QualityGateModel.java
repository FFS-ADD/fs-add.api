package com.accenture.fsadd.sonar.controller.model;

import com.accenture.fsadd.common.mvc.model.CommonModel;

public class QualityGateModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String qualityGateStatus;
	
	private String vulneralilities;
	
	private String bugs;
	
	private String codeSmells;

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
	

}
