package com.accenture.fsadd.extif.sonarqube;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.common.FsaddUtil;
import com.accenture.fsadd.core.SystemException;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class SonarqubeDataExtrator{
	
	@Autowired
	private SonarqubeSetting sonarqubeSetting;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void extractIssues(){		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(sonarqubeSetting.getSonarqubeMeasuresUrl(), HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		DBObject componentObject = (DBObject)dbObject.get(FsaddConstant.SONARQUBE_ACCESS_KEY);
		componentObject.put(FsaddConstant.INSERT_DATA_COL, FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now(),FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS));
		mongoTemplate.insert(componentObject, sonarqubeSetting.getIssueCollectionName());

		
	}

}
