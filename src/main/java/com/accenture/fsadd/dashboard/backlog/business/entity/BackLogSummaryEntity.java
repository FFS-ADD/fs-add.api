package com.accenture.fsadd.dashboard.backlog.business.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

/**
 * BackLog Summary
 *
 */
public class BackLogSummaryEntity {

	@Id
	private final BigInteger id = BigInteger.ONE;

	/**
	 * Initial Estimated Hours
	 */
	private int initEstimatedHours;

	/**
	 * Plan Estimated Hours
	 */
	private int PlannedEstimatedHours;

	/**
	 * ReportedHours
	 */
	private int reportedHours;

	/**
	 * Total
	 */
	private int totalCount;

	/**
	 * New count
	 */
	private int newCount;

	/**
	 * Pending Count
	 */
	private int pendingCount;
	
	/**
	 * Delay Count
	 */
	private int delayCount;

	/**
	 * In progressing count
	 */
	private int inProgressIngCount;

	/**
	 * Closed count
	 */
	private int closedCount;

	public int getInitEstimatedHours() {
		return initEstimatedHours;
	}

	public void setInitEstimatedHours(int initEstimatedHours) {
		this.initEstimatedHours = initEstimatedHours;
	}

	public int getPlannedEstimatedHours() {
		return PlannedEstimatedHours;
	}

	public void setPlannedEstimatedHours(int plannedEstimatedHours) {
		PlannedEstimatedHours = plannedEstimatedHours;
	}

	public int getReportedHours() {
		return reportedHours;
	}

	public void setReportedHours(int reportedHours) {
		this.reportedHours = reportedHours;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public int getInProgressIngCount() {
		return inProgressIngCount;
	}

	public void setInProgressIngCount(int inProgressIngCount) {
		this.inProgressIngCount = inProgressIngCount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + PlannedEstimatedHours;
		result = prime * result + closedCount;
		result = prime * result + delayCount;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + inProgressIngCount;
		result = prime * result + initEstimatedHours;
		result = prime * result + newCount;
		result = prime * result + pendingCount;
		result = prime * result + reportedHours;
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
		BackLogSummaryEntity other = (BackLogSummaryEntity) obj;
		if (PlannedEstimatedHours != other.PlannedEstimatedHours)
			return false;
		if (closedCount != other.closedCount)
			return false;
		if (delayCount != other.delayCount)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inProgressIngCount != other.inProgressIngCount)
			return false;
		if (initEstimatedHours != other.initEstimatedHours)
			return false;
		if (newCount != other.newCount)
			return false;
		if (pendingCount != other.pendingCount)
			return false;
		if (reportedHours != other.reportedHours)
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}

	public int getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}

	public int getDelayCount() {
		return delayCount;
	}

	public void setDelayCount(int delayCount) {
		this.delayCount = delayCount;
	}

}
