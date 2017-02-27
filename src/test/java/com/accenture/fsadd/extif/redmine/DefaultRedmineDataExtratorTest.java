package com.accenture.fsadd.extif.redmine;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.extif.service.ExtIfService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultRedmineDataExtratorTest {

	@Autowired
	private ExtIfService extIfService;

	@Autowired
	private MongoTemplate mongoTemplate;

	// @Test
	public void testRedmineDataExtrator1() {
		DefaultRedmineDataExtrator extrator = new DefaultRedmineDataExtrator();
		extrator.setExtIfService(extIfService);
		extrator.setMongoTemplate(mongoTemplate);
		extrator.extrator(null);
	}


		// @Test
	// public void testRedmineDataExtrator2() {
	// RedmineDataExtrator extrator = new RedmineDataExtrator();
	// extrator.setExtIfService(extIfService);
	// extrator.setMongoTemplate(mongoTemplate);
	// extrator.extrator(LocalDateTime.now().minusDays(2));
	// }

	// @Test
	// public void testGetLastUpdateOnInLastExecuted(){
	// RedmineDataExtrator extrator = new RedmineDataExtrator();
	// extrator.setExtIfService(extIfService);
	// extrator.setMongoTemplate(mongoTemplate);
	// String lastedUpdateOnn =
	// extrator.getLastUpdateOnInLastExecuted(extIfService.getExtIfSetting().getRedmineSetting());
	// System.out.println(lastedUpdateOnn);
	//
	// }

}
