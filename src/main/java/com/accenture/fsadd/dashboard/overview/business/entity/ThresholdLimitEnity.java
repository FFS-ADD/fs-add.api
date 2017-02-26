package com.accenture.fsadd.dashboard.overview.business.entity;

/**
 * Threshold Limit Entity
 *
 */
public class ThresholdLimitEnity {
	
	public enum ThreshLimitType{
		BACKLOG, TASK, BUG, QA, SOURCE_HEALTH
	}

	private ThreshLimitType type;
	
	private double upperLimit;
	
	private double lowerLimit;
	
	private String upperLimitString;
	
	private String lowerLimitString;
		
	private String warningMessage;
	
	private String criticalMessage;

	public ThreshLimitType getType() {
		return type;
	}

	public void setType(ThreshLimitType type) {
		this.type = type;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getUpperLimitString() {
		return upperLimitString;
	}

	public void setUpperLimitString(String upperLimitString) {
		this.upperLimitString = upperLimitString;
	}

	public String getLowerLimitString() {
		return lowerLimitString;
	}

	public void setLowerLimitString(String lowerLimitString) {
		this.lowerLimitString = lowerLimitString;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getCriticalMessage() {
		return criticalMessage;
	}

	public void setCriticalMessage(String criticalMessage) {
		this.criticalMessage = criticalMessage;
	}


}
