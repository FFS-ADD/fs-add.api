package com.accenture.fsadd.dashboard.qa.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerDailySummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity.Status;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.repository.QueryAnswerDailySummaryRepository;
import com.accenture.fsadd.dashboard.qa.business.repository.QueryAnswerRepository;
import com.accenture.fsadd.dashboard.qa.business.repository.QueryAnswerSummaryRepository;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;

@Service
public class QueryAnswerServiceImpl implements QueryAnswerService {

	@Autowired
	private QueryAnswerDailySummaryRepository queryAnswerDailySummaryRepository;

	@Autowired
	private QueryAnswerRepository queryAnswerRepository;

	@Autowired
	private QueryAnswerSummaryRepository queryAnswerSummaryRepository;

	@Override
	public QueryAnswerSummaryEntity getSummaryService() {
		return queryAnswerSummaryRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<QueryAnswerEntity> getAllOverDueQAService() {
		List<QueryAnswerEntity> overdueList = queryAnswerRepository.findByGreaterThanExpectedAnswerOn(LocalDate.now());
		if (overdueList == null) {
			overdueList = new ArrayList<>();
		}
		overdueList.sort((overdue1, overdue2) -> {
			return overdue1.getExpectedAnswerOn().compareTo(overdue2.getExpectedAnswerOn());
		});
		return overdueList;
	}

	@Override
	public List<QueryAnswerDailySummaryEntity> getDailySummaryService(LocalDate fromDay, LocalDate toDay) {
		List<QueryAnswerDailySummaryEntity> dailySummaryList = queryAnswerDailySummaryRepository
				.findByDayBetween(fromDay, toDay);
		if (dailySummaryList == null) {
			dailySummaryList = new ArrayList<>();
		}
		dailySummaryList.sort((dailySummary1, dailySummary2) -> {
			return dailySummary1.getDay().compareTo(dailySummary2.getDay());
		});
		return dailySummaryList;
	}

	@Override
	public void aggregateSummaryService(LocalDate executedDate) {
		List<QueryAnswerEntity> qaList = queryAnswerRepository.findAll();
		QueryAnswerSummaryEntity summary = new QueryAnswerSummaryEntity();
		QueryAnswerDailySummaryEntity daily = new QueryAnswerDailySummaryEntity();
		daily.setSummaryEntity(summary);
		daily.setDay(executedDate);
		for (QueryAnswerEntity qa : qaList) {
			// Setting the count in according to status
			if (qa.getStatus() == Status.NEW) {
				summary.setNewCount(summary.getNewCount() + 1);
			}
			if (qa.getStatus() == Status.INPROGRESS) {
				summary.setInProgressIngCount(summary.getInProgressIngCount() + 1);
			}
			if (qa.getStatus() == Status.CLOSED) {
				summary.setClosedCount(summary.getClosedCount() + 1);
			}
			if (qa.getStatus() != Status.CLOSED && qa.getExpectedAnswerOn().isBefore(executedDate)) {
				summary.setOverdueCount(summary.getOverdueCount() + 1);
			}
			summary.setTotalCount(summary.getNewCount() + summary.getClosedCount() + summary.getInProgressIngCount());

		}
		// save the data
		queryAnswerSummaryRepository.save(summary);
		queryAnswerDailySummaryRepository.save(daily);

	}

	@Override
	public void save(QueryAnswerEntity queryAnswerEntity) {
		queryAnswerRepository.save(queryAnswerEntity);

	}

	@Override
	public void save(List<QueryAnswerEntity> queryAnswerEntityList) {
		queryAnswerEntityList.forEach((entity) -> this.save(entity));
		// BulkOperations ops = mongoTemplate.bulkOps(BulkMode.ORDERED,
		// QueryAnswerEntity.class);
		// for (QueryAnswerEntity queryAnswerEntity : queryAnswerEntityList) {
		// DBObject dbObject = new BasicDBObject();
		// mongoTemplate.getConverter().write(queryAnswerEntity, dbObject);
		// ops.upsert(Query.query(Criteria.where("id").is(queryAnswerEntity.getId().toString())),
		// Update.fromDBObject(dbObject, "_class"));
		// }
		// ops.execute();

	}

}
