package com.accenture.fsadd.dashboard.qa.business.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * QA Entity
 *
 */
public class QueryAnswerEntity {

	public static enum Status {
		NEW, INPROGRESS, CLOSED
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
	 * Status
	 */
	private Status status;

	/**
	 * Expected answer date
	 * 
	 */
	private LocalDate expectedAnswerOn;

	/**
	 * title
	 */
	private String title;

	/**
	 * Owner
	 */
	private String owner;

	/**
	 * questioner
	 */
	private String questioner;

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

	public LocalDate getExpectedAnswerOn() {
		return expectedAnswerOn;
	}

	public void setExpectedAnswerOn(LocalDate expectedAnswerOn) {
		this.expectedAnswerOn = expectedAnswerOn;
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

	public String getQuestioner() {
		return questioner;
	}

	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closedOn == null) ? 0 : closedOn.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((expectedAnswerOn == null) ? 0 : expectedAnswerOn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((questioner == null) ? 0 : questioner.hashCode());
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
		QueryAnswerEntity other = (QueryAnswerEntity) obj;
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
		if (expectedAnswerOn == null) {
			if (other.expectedAnswerOn != null)
				return false;
		} else if (!expectedAnswerOn.equals(other.expectedAnswerOn))
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
		if (questioner == null) {
			if (other.questioner != null)
				return false;
		} else if (!questioner.equals(other.questioner))
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
}
