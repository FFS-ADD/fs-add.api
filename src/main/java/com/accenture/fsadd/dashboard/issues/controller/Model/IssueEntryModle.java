package com.accenture.fsadd.dashboard.issues.controller.Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity.Priority;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueEntryModle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3498559945827963858L;
	
	/**
	 * ID
	 */
	@JsonProperty("no")
	private BigInteger id;
	

	/**
	 * Priority
	 */
	@JsonProperty("priorityRaw")
	private Priority priority;
	
	/**
	 * Priority As String
	 * 
	 */
	@JsonProperty("priority")
	private String priorityAsString;
	
	/**
	 * title
	 */
	@JsonProperty("title")
	private String title;

	/**
	 * owner
	 */
	@JsonProperty("owner")
	private String owner;
	
	/**
	 * Due Date
	 * 
	 */
	@JsonProperty("dueDateRaw")
	private LocalDate dueDate;
	
	/**
	 * Due Date As String
	 * 
	 */
	@JsonProperty("dueDate")
	private String dueDateAsString;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getPriorityAsString() {
		return priorityAsString;
	}

	public void setPriorityAsString(String priorityAsString) {
		this.priorityAsString = priorityAsString;
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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getDueDateAsString() {
		return dueDateAsString;
	}

	public void setDueDateAsString(String dueDateAsString) {
		this.dueDateAsString = dueDateAsString;
	}

}
