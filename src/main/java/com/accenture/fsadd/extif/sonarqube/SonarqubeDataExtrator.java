package com.accenture.fsadd.extif.sonarqube;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.core.SystemException;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class SonarqubeDataExtrator {
	
	@Autowired
	private SonarqubeSetting sonarqubeSetting;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void extractIssues(){		
		RestTemplate restTemplate = new RestTemplate();
		sonarqubeSetting.setSonarqubeServeUrl("http://localhost:9000/sonar");
		sonarqubeSetting.setProjectKey(FsaddConstant.SONARQUBE_TEST_PROJECT_KEY);
		ResponseEntity<String> result = restTemplate.exchange(sonarqubeSetting.getSonarqubeIssuesUrl(), HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		dbObject.put("getAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern(FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS)));
		mongoTemplate.insert(dbObject.get(FsaddConstant.SONARQUBE_ACCESS_KEY), sonarqubeSetting.getIssueCollectionName());
		
	}


}
