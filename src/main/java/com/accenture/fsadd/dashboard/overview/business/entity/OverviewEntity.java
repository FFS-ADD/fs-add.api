package com.accenture.fsadd.dashboard.overview.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.accenture.fsadd.dashboard.overview.business.HealthType;

/**
 * Project Overview Entity
 *
 */
public class OverviewEntity {


	public static enum EventType {
		INFO, WARNING
	}

	/**
	 * ID
	 */
	@Id
	private final BigInteger id = BigInteger.ONE;

	/**
	 * Project Name
	 */
	private String projectName;

	/**
	 * Release Name
	 */
	private String releaseName;

	/**
	 * Health
	 */
	private HealthType health;

	/**
	 * Updated On
	 */
	private LocalDateTime lastUpdateTimeOn;

	public BigInteger getId() {
		return id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public HealthType getHealth() {
		return health;
	}

	public void setHealth(HealthType health) {
		this.health = health;
	}

	public LocalDateTime getLastUpdateTimeOn() {
		return lastUpdateTimeOn;
	}

	public void setLastUpdateTimeOn(LocalDateTime lastUpdateTimeOn) {
		this.lastUpdateTimeOn = lastUpdateTimeOn;
	}



}
