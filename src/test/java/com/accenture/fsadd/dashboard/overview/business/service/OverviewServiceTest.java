package com.accenture.fsadd.dashboard.overview.business.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OverviewServiceTest {
	
	@Autowired
	OverviewService overviewService;

	@Test
	public void testAggregateService() {
		overviewService.aggregateService(LocalDateTime.now());
	}

}
