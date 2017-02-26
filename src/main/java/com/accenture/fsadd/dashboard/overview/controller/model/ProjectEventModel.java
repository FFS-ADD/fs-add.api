package com.accenture.fsadd.dashboard.overview.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectEventModel implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2856888979821810011L;
	/**
	 * Event Level 0: Information, 1:warning
	 */
	private int eventLevel;

	/**
	 * Event Date as String
	 */
	@JsonProperty("eventDate")
	private String eventDateAsString;

	/**
	 * Event Detail
	 */
	@JsonProperty("eventDescription")
	private String event;

	public int getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(int eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getEventDateAsString() {
		return eventDateAsString;
	}

	public void setEventDateAsString(String eventDateAsString) {
		this.eventDateAsString = eventDateAsString;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
