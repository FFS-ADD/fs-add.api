package com.accenture.fsadd.dashboard.overview.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * Project Overview Event Entity
 *
 */
public class OverviewEventEntity {

	public static enum EventType {
		INFO, WARNING
	}

	/**
	 * ID
	 */
	@Id
	private String id;

	/**
	 * Event Date
	 */
	private LocalDate eventDate;

	/**
	 * Event
	 */
	private String event;

	/**
	 * Event Type
	 */
	private EventType eventTye;

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public EventType getEventTye() {
		return eventTye;
	}

	public void setEventTye(EventType eventTye) {
		this.eventTye = eventTye;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
