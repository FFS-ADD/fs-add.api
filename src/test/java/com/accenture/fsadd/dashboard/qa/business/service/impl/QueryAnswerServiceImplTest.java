package com.accenture.fsadd.dashboard.qa.business.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerDailySummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity.Status;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryAnswerServiceImplTest {

	@Autowired
	QueryAnswerService queryAnswerService;

	@Autowired
	MongoTemplate mongoTemplate;

	Random random = new Random();

	QueryAnswerEntity getRandomEnity(Status status) {
		QueryAnswerEntity queryAnswerEntity = new QueryAnswerEntity();
		LocalDate openDate = LocalDate.now().minusDays(random.nextInt(365));
		queryAnswerEntity.setId(BigInteger.valueOf(Math.abs(random.nextInt())));
		queryAnswerEntity.setCreatedOn(openDate);
		queryAnswerEntity.setExpectedAnswerOn(openDate.plusDays(random.nextInt(365)));
		queryAnswerEntity.setOwner(RandomStringUtils.randomAscii(20));
		queryAnswerEntity.setQuestioner(RandomStringUtils.randomAscii(20));
		queryAnswerEntity.setStatus(status);
		if (status == Status.CLOSED) {
			queryAnswerEntity.setClosedOn(openDate.plusDays(random.nextInt(365)));
		}
		queryAnswerEntity.setTitle(RandomStringUtils.randomAscii(100));
		queryAnswerEntity.setUpdatedOn(LocalDate.now().minusDays(random.nextInt(365)));
		return queryAnswerEntity;
	}

	// @Test
	public void testSave() {

		mongoTemplate.dropCollection(QueryAnswerEntity.class);
		QueryAnswerEntity one = getRandomEnity(Status.NEW);
		// Insert one
		queryAnswerService.save(one);
		List<QueryAnswerEntity> expectedList = mongoTemplate.findAll(QueryAnswerEntity.class);
		assertThat(expectedList.size() == 1).isTrue();
		assertThat(expectedList.get(0)).isEqualTo(one);
		// update One
		QueryAnswerEntity two = getRandomEnity(Status.INPROGRESS);
		two.setId(one.getId());
		queryAnswerService.save(two);
		expectedList = mongoTemplate.findAll(QueryAnswerEntity.class);
		assertThat(expectedList.size() == 1).isTrue();
		assertThat(expectedList.get(0)).isEqualTo(two);
		// Bulk insert update
		QueryAnswerEntity o1 = getRandomEnity(Status.CLOSED);
		o1.setId(one.getId());
		QueryAnswerEntity o2 = getRandomEnity(Status.NEW);
		o2.setId(two.getId());
		QueryAnswerEntity n1 = getRandomEnity(Status.NEW);
		QueryAnswerEntity n2 = getRandomEnity(Status.INPROGRESS);
		List<QueryAnswerEntity> list = new ArrayList<>();
		list.add(o1);
		list.add(o2);
		list.add(n1);
		list.add(n2);
		queryAnswerService.save(list);
		expectedList = mongoTemplate.findAll(QueryAnswerEntity.class);
		assertThat(expectedList.size() == 3).isTrue();
		assertThat(expectedList.get(0)).isEqualTo(o2);
		assertThat(expectedList.get(1)).isEqualTo(n1);
		assertThat(expectedList.get(2)).isEqualTo(n2);
	}

	//@Test
	public void testGetOverDueQA() {
		mongoTemplate.dropCollection(QueryAnswerEntity.class);
		// Empty Collections
		List<QueryAnswerEntity> result = queryAnswerService.getAllOverDueQAService();
		assertThat(result.size() == 0).isTrue();
		List<QueryAnswerEntity> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			QueryAnswerEntity temp = getRandomEnity(Status.INPROGRESS);
			temp.setExpectedAnswerOn(LocalDate.now().plusDays(1));
			list.add(temp);
		}
		list.get(2).setExpectedAnswerOn(LocalDate.now().minusDays(1));
		list.get(2).setStatus(Status.INPROGRESS);
		list.get(3).setExpectedAnswerOn(LocalDate.now().minusDays(2));
		list.get(3).setStatus(Status.NEW);
		list.get(5).setExpectedAnswerOn(LocalDate.now().minusDays(1));
		list.get(5).setStatus(Status.CLOSED);
		queryAnswerService.save(list);
		result = queryAnswerService.getAllOverDueQAService();
		assertThat(result.size() == 2).isTrue();
		assertThat(list.get(3)).isEqualTo(result.get(0));
		assertThat(list.get(2)).isEqualTo(result.get(1));
	}

	@Test
	public void testAggregateSummary() {
		mongoTemplate.dropCollection(QueryAnswerEntity.class);
		mongoTemplate.dropCollection(QueryAnswerDailySummaryEntity.class);
		mongoTemplate.dropCollection(QueryAnswerSummaryEntity.class);
		queryAnswerService.aggregateSummaryService(LocalDate.now());
		QueryAnswerSummaryEntity summary = queryAnswerService.getSummaryService();
		assertThat(summary !=null ).isTrue();
		assertThat(summary.getTotalCount()).isEqualTo(0);
		assertThat(summary.getClosedCount()).isEqualTo(0);
		assertThat(summary.getInProgressIngCount()).isEqualTo(0);
		assertThat(summary.getNewCount()).isEqualTo(0);
		assertThat(summary.getOverdueCount()).isEqualTo(0);
		// One
		QueryAnswerEntity two = getRandomEnity(Status.INPROGRESS);
		two.setCreatedOn(LocalDate.now().minusDays(2));
		two.setExpectedAnswerOn(LocalDate.now().minusDays(1));
		queryAnswerService.save(two);
		queryAnswerService.aggregateSummaryService(LocalDate.now());		
		 summary = queryAnswerService.getSummaryService();
		assertThat(summary !=null ).isTrue();
		assertThat(summary.getTotalCount()).isEqualTo(1);
		assertThat(summary.getClosedCount()).isEqualTo(0);
		assertThat(summary.getInProgressIngCount()).isEqualTo(1);
		assertThat(summary.getNewCount()).isEqualTo(0);
		assertThat(summary.getOverdueCount()).isEqualTo(1);
		List<QueryAnswerDailySummaryEntity> dailySummaryList = 
				queryAnswerService.getDailySummaryService(LocalDate.now().minusYears(1), LocalDate.now().plusYears(1));
		assertThat(dailySummaryList.size() == 1 ).isTrue();
		QueryAnswerDailySummaryEntity daily = dailySummaryList.get(0);
		QueryAnswerSummaryEntity dailySummary = daily.getSummaryEntity();
		assertThat(daily.getDay()).isEqualTo(LocalDate.now().minusDays(2));
		assertThat(dailySummary.getTotalCount()).isEqualTo(1);
		assertThat(dailySummary.getClosedCount()).isEqualTo(0);
		assertThat(dailySummary.getInProgressIngCount()).isEqualTo(1);
		assertThat(dailySummary.getNewCount()).isEqualTo(0);
		assertThat(dailySummary.getOverdueCount()).isEqualTo(1);
		
		mongoTemplate.dropCollection(QueryAnswerEntity.class);
		mongoTemplate.dropCollection(QueryAnswerDailySummaryEntity.class);
		mongoTemplate.dropCollection(QueryAnswerSummaryEntity.class);
		// multiply qa
		// Inprogress 2 delay2
		QueryAnswerEntity oy1 = getRandomEnity(Status.INPROGRESS);
		oy1.setCreatedOn(LocalDate.now().minusDays(2));
		oy1.setExpectedAnswerOn(LocalDate.now().plusDays(2));
		QueryAnswerEntity oy2 = getRandomEnity(Status.INPROGRESS);
		oy2.setCreatedOn(LocalDate.now().minusDays(3));
		oy2.setExpectedAnswerOn(LocalDate.now().minusDays(4));
		// new 3 delay2
		QueryAnswerEntity oy3 = getRandomEnity(Status.NEW);
		oy3.setCreatedOn(LocalDate.now().minusDays(2));
		oy3.setExpectedAnswerOn(LocalDate.now().minusDays(1));
		QueryAnswerEntity oy4 = getRandomEnity(Status.NEW);
		oy4.setCreatedOn(LocalDate.now().minusDays(3));
		oy4.setExpectedAnswerOn(LocalDate.now().plusDays(4));
		QueryAnswerEntity oy41 = getRandomEnity(Status.NEW);
		oy41.setCreatedOn(LocalDate.now().minusDays(4));
		oy41.setExpectedAnswerOn(LocalDate.now().minusDays(3));
		// closed 4
		QueryAnswerEntity oy5 = getRandomEnity(Status.CLOSED);
		oy5.setCreatedOn(LocalDate.now().minusDays(2));
		oy5.setExpectedAnswerOn(LocalDate.now().plusDays(2));
		QueryAnswerEntity oy6 = getRandomEnity(Status.CLOSED);
		oy6.setCreatedOn(LocalDate.now().minusDays(3));
		oy6.setExpectedAnswerOn(LocalDate.now().plusDays(3));
		QueryAnswerEntity oy7 = getRandomEnity(Status.CLOSED);
		oy7.setCreatedOn(LocalDate.now().minusDays(4));
		oy7.setExpectedAnswerOn(LocalDate.now().minusDays(4));
		QueryAnswerEntity oy8 = getRandomEnity(Status.CLOSED);
		oy8.setCreatedOn(LocalDate.now().minusDays(5));
		oy8.setExpectedAnswerOn(LocalDate.now().minusDays(5));
		List<QueryAnswerEntity> list = new ArrayList<>();
		list.add(oy1);
		list.add(oy2);
		list.add(oy3);
		list.add(oy4);
		list.add(oy41);
		list.add(oy5);
		list.add(oy6);
		list.add(oy7);
		list.add(oy8);
		
		queryAnswerService.save(list);
		queryAnswerService.aggregateSummaryService(LocalDate.now());		
		 summary = queryAnswerService.getSummaryService();
		assertThat(summary !=null ).isTrue();
		assertThat(summary.getTotalCount()).isEqualTo(9);
		assertThat(summary.getClosedCount()).isEqualTo(4);
		assertThat(summary.getInProgressIngCount()).isEqualTo(2);
		assertThat(summary.getNewCount()).isEqualTo(3);
		assertThat(summary.getOverdueCount()).isEqualTo(3);
		dailySummaryList = 
				queryAnswerService.getDailySummaryService(LocalDate.now().minusYears(1), LocalDate.now().plusYears(1));
		assertThat(dailySummaryList.size() == 4 ).isTrue();
		daily = dailySummaryList.get(0);
		dailySummary = daily.getSummaryEntity();
		assertThat(daily.getDay()).isEqualTo(LocalDate.now().minusDays(5));
		assertThat(dailySummary.getTotalCount()).isEqualTo(1);
		assertThat(dailySummary.getClosedCount()).isEqualTo(1);
		assertThat(dailySummary.getInProgressIngCount()).isEqualTo(0);
		assertThat(dailySummary.getNewCount()).isEqualTo(0);
		assertThat(dailySummary.getOverdueCount()).isEqualTo(0);
		daily = dailySummaryList.get(2);
		dailySummary = daily.getSummaryEntity();
		assertThat(daily.getDay()).isEqualTo(LocalDate.now().minusDays(3));
		assertThat(dailySummary.getTotalCount()).isEqualTo(3);
		assertThat(dailySummary.getClosedCount()).isEqualTo(1);
		assertThat(dailySummary.getInProgressIngCount()).isEqualTo(1);
		assertThat(dailySummary.getNewCount()).isEqualTo(1);
		assertThat(dailySummary.getOverdueCount()).isEqualTo(1);
	}

}
