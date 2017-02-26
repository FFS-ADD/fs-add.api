package com.accenture.fsadd.dashboard.task.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * Task Entity
 *
 */
public class TaskEntity {

	public static enum Status {
		NEW, INPROGRESS, CLOSED, PENDING
	}

	public static enum Priority {
		LOW, MEDIUM, HIGH, CRITICAL
	}

	public static enum Type {
		ANALYSIS, DESGIN, BUILD, DEVELOPMENT, TEST, DEPLOYMENT
	}

	/**
	 * ID
	 */
	@Id
	private BigInteger id;

	/**
	 * Task Type
	 */
	private Type type;

	/**
	 * Back Log ID
	 */
	private BigInteger backLogId;

	/**
	 * Created On
	 */
	private LocalDate createdOn;

	/**
	 * Updated On
	 */
	private LocalDate updatedOn;

	/**
	 * Closed On
	 */
	private LocalDate closedOn;

	/**
	 * Status
	 */
	private Status status;

	/**
	 * Priority
	 */
	private Priority priority;
	/**
	 * Planned Start Date
	 */
	private LocalDate planedStartDate;

	/**
	 * Actual Start Date
	 */
	private LocalDate actualStartDate;

	/**
	 * Planned End Date
	 */
	private LocalDate planedEndDate;

	/**
	 * Actual End Date
	 */
	private LocalDate actualEndDate;

	/**
	 * Estimate Hours
	 */
	private int estimatedHours;

	/**
	 * Actual Hours
	 */
	private int actualHours;

	/**
	 * Done Ratio
	 */
	private int doneRatio;

	/**
	 * title
	 */
	private String title;

	/**
	 * owner
	 */
	private String owner;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BigInteger getBackLogId() {
		return backLogId;
	}

	public void setBackLogId(BigInteger backLogId) {
		this.backLogId = backLogId;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public LocalDate getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(LocalDate closedOn) {
		this.closedOn = closedOn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public LocalDate getPlanedStartDate() {
		return planedStartDate;
	}

	public void setPlanedStartDate(LocalDate planedStartDate) {
		this.planedStartDate = planedStartDate;
	}

	public LocalDate getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(LocalDate actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public LocalDate getPlanedEndDate() {
		return planedEndDate;
	}

	public void setPlanedEndDate(LocalDate planedEndDate) {
		this.planedEndDate = planedEndDate;
	}

	public LocalDate getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(LocalDate actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public int getActualHours() {
		return actualHours;
	}

	public void setActualHours(int actualHours) {
		this.actualHours = actualHours;
	}

	public int getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(int doneRatio) {
		this.doneRatio = doneRatio;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualEndDate == null) ? 0 : actualEndDate.hashCode());
		result = prime * result + actualHours;
		result = prime * result + ((actualStartDate == null) ? 0 : actualStartDate.hashCode());
		result = prime * result + ((backLogId == null) ? 0 : backLogId.hashCode());
		result = prime * result + ((closedOn == null) ? 0 : closedOn.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + doneRatio;
		result = prime * result + estimatedHours;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((planedEndDate == null) ? 0 : planedEndDate.hashCode());
		result = prime * result + ((planedStartDate == null) ? 0 : planedStartDate.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updatedOn == null) ? 0 : updatedOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskEntity other = (TaskEntity) obj;
		if (actualEndDate == null) {
			if (other.actualEndDate != null)
				return false;
		} else if (!actualEndDate.equals(other.actualEndDate))
			return false;
		if (actualHours != other.actualHours)
			return false;
		if (actualStartDate == null) {
			if (other.actualStartDate != null)
				return false;
		} else if (!actualStartDate.equals(other.actualStartDate))
			return false;
		if (backLogId == null) {
			if (other.backLogId != null)
				return false;
		} else if (!backLogId.equals(other.backLogId))
			return false;
		if (closedOn == null) {
			if (other.closedOn != null)
				return false;
		} else if (!closedOn.equals(other.closedOn))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (doneRatio != other.doneRatio)
			return false;
		if (estimatedHours != other.estimatedHours)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (planedEndDate == null) {
			if (other.planedEndDate != null)
				return false;
		} else if (!planedEndDate.equals(other.planedEndDate))
			return false;
		if (planedStartDate == null) {
			if (other.planedStartDate != null)
				return false;
		} else if (!planedStartDate.equals(other.planedStartDate))
			return false;
		if (priority != other.priority)
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (updatedOn == null) {
			if (other.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(other.updatedOn))
			return false;
		return true;
	}

}
