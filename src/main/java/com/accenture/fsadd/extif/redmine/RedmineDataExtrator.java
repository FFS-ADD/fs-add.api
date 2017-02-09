package com.accenture.fsadd.extif.redmine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.core.SystemException;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class RedmineDataExtrator {
	
	@Autowired
	private RedmineSetting redmineSetting;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void extractIssues(){		
		RestTemplate restTemplate = new RestTemplate();
		//TODO 
		ResponseEntity<String> result = restTemplate.exchange(redmineSetting.getIssueURL(), HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		dbObject.put("getAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		mongoTemplate.insert((List)dbObject.get("issues"), redmineSetting.getIssueCollectionName());
		
	}


}
