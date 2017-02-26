package com.accenture.fsadd.signin.datasetting.business.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;


/**
 * Data Setting Entity
 *
 */
public class DataSettingEntity {

	/**
	 * email
	 */
	@Id
	private String email;
	
	/**
	 * backlog
	 */
	private boolean backlog;
	
	/**
	 * task
	 */
	private boolean task;
	
	/**
	 * bug
	 */
	private boolean bug;
	
	/**
	 * qa
	 */
	private boolean qa;

	/**
	 * qualityGate
	 */
	private boolean qualitygate;
	
	/**
	 * loc
	 */
	private boolean loc;
	
	/**
	 * duplication
	 */
	private boolean duplication;
	
	/**
	 * coverage
	 */
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
	
	public boolean isQa() {
		return qa;
	}
	public void setQa(boolean qa) {
		this.qa = qa;
	}
}
