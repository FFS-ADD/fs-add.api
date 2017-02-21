package com.accenture.fsadd.dashboard.task.controller.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskEntryModel implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2856888979821810011L;
	/**
	 * ID
	 */
	@JsonProperty("id")
	private BigInteger id;
	/**
	 * Planned Start Date
	 */
	private LocalDate planedStartDate;
	
	/**
	 * Planned Start Date
	 */
	@JsonProperty("planedEndDate")
	private String planedStartDateAsString;

	/**
	 * title
	 */
	@JsonProperty("ticket")
	private String title;

	/**
	 * Owner
	 */
	@JsonProperty("owner")
	private String owner;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDate getPlanedStartDate() {
		return planedStartDate;
	}

	public void setPlanedStartDate(LocalDate planedStartDate) {
		this.planedStartDate = planedStartDate;
	}

	public String getPlanedStartDateAsString() {
		return planedStartDateAsString;
	}

	public void setPlanedStartDateAsString(String planedStartDateAsString) {
		this.planedStartDateAsString = planedStartDateAsString;
	}


}
