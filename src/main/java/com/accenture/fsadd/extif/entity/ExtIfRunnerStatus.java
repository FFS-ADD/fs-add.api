package com.accenture.fsadd.extif.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

/**
 * 
 * External If Runner Status
 *
 */
public class ExtIfRunnerStatus {
	
	/**
	 * 
	 * Status of external-if
	 *
	 */
	public static enum Status{
		/**
		 * <p>Current status of external</p>
		 * <ul>
		 * <li>STOPPED:Initial value.</li>
		 * <li>PROCESSING: Runner Of external if is running.</li>
		 * <li>COMPLETED: External if is completed.</li>
		 * <li>ERROR: Error occurred.</li>
		 * </ul>
		 *
		 */
		STOPPED, PROCESSING, COMPLETED, ERROR		
	}
	
	/**
	 * Name of external name
	 */
	@Id
	private String extIfName;
	
	/**
	 * Status
	 */
	private Status status;
	
	/**
	 * Last Execute time
	 */
	private LocalDateTime lastExecutedOn;
	
	/**
	 * Start time of the external.
	 */
	private LocalDateTime startedOn;
	
	/**
	 * End time of the external
	 */
	private LocalDateTime endedOn;
	
	/**
	 *  Message
	 */
	private String message;

	public String getExtIfName() {
		return extIfName;
	}

	public void setExtIfName(String extIfName) {
		this.extIfName = extIfName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}

	public LocalDateTime getEndedOn() {
		return endedOn;
	}

	public void setEndedOn(LocalDateTime endedOn) {
		this.endedOn = endedOn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}

	public LocalDateTime getLastExecutedOn() {
		return lastExecutedOn;
	}

	public void setLastExecutedOn(LocalDateTime lastExecutedOn) {
		this.lastExecutedOn = lastExecutedOn;
	}

}
