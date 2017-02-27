package com.accenture.fsadd.extif.sonarqube;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.common.FsaddUtil;
import com.accenture.fsadd.core.SystemException;
import com.accenture.fsadd.extif.ExtIfDataExtrator;
import com.accenture.fsadd.extif.entity.SonarQubeSetting;
import com.accenture.fsadd.extif.service.ExtIfService;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class DefaultSonarQubeDataExtrator  implements ExtIfDataExtrator{
	
	@Autowired
	private ExtIfService extIfService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void extrator(LocalDateTime lastExecutedTime){
		SonarQubeSetting sonarQubeSetting = extIfService.getExtIfSetting().getSonarQubeSetting();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(sonarQubeSetting.getSonarqubeUrl(), HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		if(!(parseResult instanceof DBObject)){
			throw new SystemException("cannot get the data by api");
		}
		DBObject dbObject = (DBObject)parseResult;
		DBObject componentObject = (DBObject)dbObject.get(FsaddConstant.SONARQUBE_ACCESS_KEY);
		componentObject.put(FsaddConstant.INSERT_DATA_COL, FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now(),FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS));
		mongoTemplate.insert(componentObject, sonarQubeSetting.getIssuesCollectionName());
	}
}
