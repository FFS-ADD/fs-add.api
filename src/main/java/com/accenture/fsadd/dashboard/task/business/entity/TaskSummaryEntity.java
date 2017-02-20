package com.accenture.fsadd.dashboard.task.business.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

/**
 *Task Summary
 *
 */
public class TaskSummaryEntity {

	@Id
	private final BigInteger id = BigInteger.ONE;

	/**
	 * Total
	 */
	private int totalCount;

	/**
	 * On Schedule Count
	 */
	private int onSheduleCount;

	/**
	 * delay count
	 */
	private int delayCount;

	/**
	 * Pending count
	 */
	private int pendingCount;

	/**
	 * Closed count
	 */
	private int closedCount;

	public int getTotalCount() {
		return totalCount;
	}

	public int getOnSheduleCount() {
		return onSheduleCount;
	}

	public void setOnSheduleCount(int onSheduleCount) {
		this.onSheduleCount = onSheduleCount;
	}

	public int getDelayCount() {
		return delayCount;
	}

	public void setDelayCount(int delayCount) {
		this.delayCount = delayCount;
	}

	public int getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}

	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}

	public BigInteger getId() {
		return id;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + closedCount;
		result = prime * result + delayCount;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + onSheduleCount;
		result = prime * result + pendingCount;
		result = prime * result + totalCount;
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
		TaskSummaryEntity other = (TaskSummaryEntity) obj;
		if (closedCount != other.closedCount)
			return false;
		if (delayCount != other.delayCount)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (onSheduleCount != other.onSheduleCount)
			return false;
		if (pendingCount != other.pendingCount)
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}


}
