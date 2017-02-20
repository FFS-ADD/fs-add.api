package com.accenture.fsadd.dashboard.issues.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * Issue Entity
 *
 */
public class IssueEntity {

	public static enum Status {
		NEW, INPROGRESS, FIXED, RETESTING, CLOSED
	}

	public static enum Phase {
		ITA, ITB, ST, UAT
	}

	public static enum Priority {
		LOW, MEDIUM, HIGH, CRITICAL
	}

	/**
	 * ID
	 */
	@Id
	private BigInteger id;

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
	 * Occured Date
	 */
	private LocalDate occuredDate;
	/**
	 * Status
	 */
	private Status status;

	/**
	 * Phase
	 */
	private Phase phase;

	/**
	 * Priority
	 */
	private Priority priority;

	/**
	 * Due Date
	 * 
	 */
	private LocalDate dueDate;

	/**
	 * title
	 */
	private String title;

	/**
	 * owner
	 */
	private String owner;

	/**
	 * detector
	 */
	private String detector;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getDetector() {
		return detector;
	}

	public void setDetector(String detector) {
		this.detector = detector;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closedOn == null) ? 0 : closedOn.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((detector == null) ? 0 : detector.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((occuredDate == null) ? 0 : occuredDate.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((phase == null) ? 0 : phase.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		IssueEntity other = (IssueEntity) obj;
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
		if (detector == null) {
			if (other.detector != null)
				return false;
		} else if (!detector.equals(other.detector))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (occuredDate == null) {
			if (other.occuredDate != null)
				return false;
		} else if (!occuredDate.equals(other.occuredDate))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (phase != other.phase)
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
		if (updatedOn == null) {
			if (other.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(other.updatedOn))
			return false;
		return true;
	}

	public LocalDate getOccuredDate() {
		return occuredDate;
	}

	public void setOccuredDate(LocalDate occuredDate) {
		this.occuredDate = occuredDate;
	}

}
