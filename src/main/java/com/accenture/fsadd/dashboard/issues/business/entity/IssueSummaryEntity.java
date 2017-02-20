package com.accenture.fsadd.dashboard.issues.business.entity;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

/**
 *Issue Summary
 *
 */
public class IssueSummaryEntity {

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
	 * Fixed count
	 */
	private int fixedCount;
	/**
	 * Fixed count
	 */
	private int retestingCount;
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


	public int getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}

	public BigInteger getId() {
		return id;
	}

	public int getFixedCount() {
		return fixedCount;
	}

	public void setFixedCount(int fixedCount) {
		this.fixedCount = fixedCount;
	}

	public int getRetestingCount() {
		return retestingCount;
	}

	public void setRetestingCount(int retestingCount) {
		this.retestingCount = retestingCount;
	}

}
