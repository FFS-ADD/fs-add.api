package com.accenture.fsadd.sonar.business.service.impl;

import java.util.Map;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.sonar.business.entity.QualitygatesProjectStatusEntity;
import com.accenture.fsadd.sonar.business.service.SonarService;

public class SonarServiceImpl implements SonarService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestTemplate rTemplate = new RestTemplate();
		rTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		String uri = "http://localhost:9000/sonar/api/qualitygates/project_status?projectKey=inventory-aid";
		String entity = rTemplate.getForObject(uri, String.class);
		//Map<String, > entity2 = JsonUtils.readValue(entity, Map.class);
		//System.out.println(entity2.get("projectStatus"));
	}

}
