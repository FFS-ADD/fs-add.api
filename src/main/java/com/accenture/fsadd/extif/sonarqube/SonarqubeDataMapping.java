package com.accenture.fsadd.extif.sonarqube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.sonar.business.repository.SonarDashboardRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class SonarqubeDataMapping {
	
	@Autowired
	private SonarDashboardRepository sonarDashboardRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SonarqubeSetting sonarqubeSetting;
	
	public void MappingDataToSonarDashboard(){
		String result = mongoTemplate.findOne(
				new Query(Criteria.where(FsaddConstant.SONARQUBE_ISSUES_KEY).is(FsaddConstant.SONARQUBE_TEST_PROJECT_KEY)).with(new Sort(Direction.DESC, "createDate")), String.class, sonarqubeSetting.getIssueCollectionName());
		DBObject dbObject = (DBObject)JSON.parse(result);
		dbObject.get("measures");
	}
}