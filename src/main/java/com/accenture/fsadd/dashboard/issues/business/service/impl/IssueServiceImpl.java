package com.accenture.fsadd.dashboard.issues.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.issues.business.entity.IssueDailySummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity.Status;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;
import com.accenture.fsadd.dashboard.issues.business.repository.IssueDailySummaryRepository;
import com.accenture.fsadd.dashboard.issues.business.repository.IssueRepository;
import com.accenture.fsadd.dashboard.issues.business.repository.IssueSummaryRepository;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueDailySummaryRepository issueDailySummaryRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private IssueSummaryRepository issueSummaryRepository;

	@Override
	public IssueSummaryEntity getSummaryService() {
		return issueSummaryRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<IssueEntity> getAllOpenedIssue() {
		List<IssueEntity> openedIssues = issueRepository.findByNotInStatus(Status.CLOSED);
		if (openedIssues == null) {
			openedIssues = new ArrayList<>();
		}
		openedIssues.sort((opendIssue1, opendIssue2) -> {
			return opendIssue2.getPriority().compareTo(opendIssue1.getPriority());
		});
		return openedIssues;
	}

	@Override
	public List<IssueDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay) {
		List<IssueDailySummaryEntity> dailySummaryList = issueDailySummaryRepository
				.findByDayBetween(fromDay.minusDays(1), toDay.plusDays(1));
		if (dailySummaryList == null) {
			dailySummaryList = new ArrayList<>();
		}
		dailySummaryList.sort((dailySummary1, dailySummary2) -> {
			return dailySummary1.getDay().compareTo(dailySummary2.getDay());
		});
		return dailySummaryList;
	}

	@Override
	public void aggregateSummaryService(LocalDate executeDate) {
		List<IssueEntity> issueList = issueRepository.findAll();
		IssueSummaryEntity summary = new IssueSummaryEntity();
		IssueDailySummaryEntity daily = new IssueDailySummaryEntity();
		daily.setSummaryEntity(summary);
		daily.setDay(executeDate);
		for (IssueEntity issue : issueList) {

			// Setting the count in according to status
			if (issue.getStatus() == Status.NEW) {
				summary.setNewCount(summary.getNewCount() + 1);
			}
			if (issue.getStatus() == Status.INPROGRESS) {
				summary.setInProgressIngCount(summary.getInProgressIngCount() + 1);
			}
			if (issue.getStatus() == Status.FIXED) {
				summary.setFixedCount(summary.getFixedCount() + 1);
			}
			if (issue.getStatus() == Status.RETESTING) {
				summary.setRetestingCount(summary.getRetestingCount() + 1);
			}
			if (issue.getStatus() == Status.CLOSED) {
				summary.setClosedCount(summary.getClosedCount() + 1);
			}

			summary.setTotalCount(summary.getNewCount() + summary.getFixedCount() + summary.getRetestingCount()
					+ summary.getClosedCount() + summary.getInProgressIngCount());
		}
		// save the data
		issueSummaryRepository.save(summary);
		issueDailySummaryRepository.save(daily);

	}

	@Override
	public void save(IssueEntity issueEntity) {
		issueRepository.save(issueEntity);

	}

	@Override
	public void save(List<IssueEntity> issueEnityList) {
		issueEnityList.forEach((entity) -> this.save(entity));

	}

}
