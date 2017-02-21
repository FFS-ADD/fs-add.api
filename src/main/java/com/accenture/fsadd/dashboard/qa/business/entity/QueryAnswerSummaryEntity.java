package com.accenture.fsadd.dashboard.qa.business.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Query Answer Summary
 *
 */
public class QueryAnswerSummaryEntity {

	@Id
	private final BigInteger id = BigInteger.ONE;

	/**
	 * Total
	 */
	private int totalCount;

	/**
	 * New count
	 */
	private int newCount;

	/**
	 * In progressing count
	 */
	private int inProgressIngCount;

	/**
	 * Overdue count
	 */
	private int overdueCount;

	/**
	 * Closed count
	 */
	private int closedCount;

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

	public int getOverdueCount() {
		return overdueCount;
	}

	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
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

}
