package com.accenture.fsadd.extif.extrator.redmine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.core.SystemException;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class RedmineService {
	
	public static void main(String[] args){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange("http://www.redmine.org/projects/redmine/issues.json", HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		dbObject.put("getAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

		
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "redmine"));
		mongoOps.insert((List)dbObject.get("issues"), "issues1");
		//com.mongodb.BasicDBList 
		
		
		System.out.println(result.getBody());
		System.out.println(dbObject);
		
	}

}
