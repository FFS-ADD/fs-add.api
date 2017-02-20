package com.accenture.fsadd.dashboard.overview.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * Project Overview Entity
 *
 */
public class OverviewEntity {

	public static enum Health {
		GOOD, WARNING, NOTGOOD
	}

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
	private Health health;

	/**
	 * Updated On
	 */
	private LocalDate lastUpdateOn;

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

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public LocalDate getLastUpdateOn() {
		return lastUpdateOn;
	}

	public void setLastUpdateOn(LocalDate lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

}
