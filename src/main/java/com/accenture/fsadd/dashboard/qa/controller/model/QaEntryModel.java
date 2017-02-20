package com.accenture.fsadd.dashboard.qa.controller.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QaEntryModel implements Serializable{
	
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
	 * Expected answer date
	 * 
	 */
	private LocalDate expectedAnswerOn;
	
	/**
	 * Expected answer date as String
	 */
	@JsonProperty("exceptedAnswerDate")
	private String expectedAnswerOnAsString;

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

	public LocalDate getExpectedAnswerOn() {
		return expectedAnswerOn;
	}

	public void setExpectedAnswerOn(LocalDate expectedAnswerOn) {
		this.expectedAnswerOn = expectedAnswerOn;
	}

	public String getExpectedAnswerOnAsString() {
		return expectedAnswerOnAsString;
	}

	public void setExpectedAnswerOnAsString(String expectedAnswerOnAsString) {
		this.expectedAnswerOnAsString = expectedAnswerOnAsString;
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


}
