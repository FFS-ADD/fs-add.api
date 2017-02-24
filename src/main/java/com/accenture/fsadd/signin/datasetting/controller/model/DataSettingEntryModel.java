package com.accenture.fsadd.signin.datasetting.controller.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataSettingEntryModel implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2856888979821810011L;
	/**
	 * email
	 */
	@JsonProperty("email")
	private String email;
	
	/**
	 * backlog
	 */
	@JsonProperty("backlog")
	private boolean backlog;
	
	/**
	 * task
	 */
	@JsonProperty("task")
	private boolean task;
	
	/**
	 * bug
	 */
	@JsonProperty("bug")
	private boolean bug;
	
	/**
	 * qualityGate
	 */
	@JsonProperty("qualitygate")
	private boolean qualitygate;
	
	/**
	 * loc
	 */
	@JsonProperty("loc")
	private boolean loc;
	
	/**
	 * duplication
	 */
	@JsonProperty("duplication")
	private boolean duplication;
	
	/**
	 * coverage
	 */
	@JsonProperty("coverage")
	private boolean coverage;
	
	
	public boolean isBacklog() {
		return backlog;
	}
	public void setBacklog(boolean backlog) {
		this.backlog = backlog;
	}
	public boolean isTask() {
		return task;
	}
	public void setTask(boolean task) {
		this.task = task;
	}
	public boolean isBug() {
		return bug;
	}
	public void setBug(boolean bug) {
		this.bug = bug;
	}
	public boolean isQualitygate() {
		return qualitygate;
	}
	public void setQualitygate(boolean qualitygate) {
		this.qualitygate = qualitygate;
	}
	public boolean isLoc() {
		return loc;
	}
	public void setLoc(boolean loc) {
		this.loc = loc;
	}
	public boolean isDuplication() {
		return duplication;
	}
	public void setDuplication(boolean duplication) {
		this.duplication = duplication;
	}
	public boolean isCoverage() {
		return coverage;
	}
	public void setCoverage(boolean coverage) {
		this.coverage = coverage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
