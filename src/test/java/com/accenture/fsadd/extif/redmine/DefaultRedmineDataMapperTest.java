package com.accenture.fsadd.extif.redmine;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.extif.service.ExtIfService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultRedmineDataMapperTest {
	

	@Autowired
	private ExtIfService extIfService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private QueryAnswerService queryAnswerService;

	@Autowired
	private IssueService issueServcie;

	@Autowired
	private BackLogService backLogService;

	@Autowired
	private TaskService taskService;
	
	@Test
	public void testMap() {
		DefaultRedmineDataMapper dataMapper = new DefaultRedmineDataMapper();
		dataMapper.setExtIfService(extIfService);
		dataMapper.setMongoTemplate(mongoTemplate);
		dataMapper.setQueryAnswerService(queryAnswerService);
		dataMapper.setIssueServcie(issueServcie);
		dataMapper.setBackLogService(backLogService);
		dataMapper.setTaskService(taskService);

		LocalDateTime now = LocalDateTime.now();
		for(int i=0; i<7; i++){
			dataMapper.map(now.minusDays(i));
		}
		
	}

}
