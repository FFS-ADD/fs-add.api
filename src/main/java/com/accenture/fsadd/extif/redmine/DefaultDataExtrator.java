package com.accenture.fsadd.extif.redmine;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.core.SystemException;
import com.accenture.fsadd.extif.ExtIfDataExtrator;
import com.accenture.fsadd.extif.entity.RedmineSetting;
import com.accenture.fsadd.extif.service.ExtIfService;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class DefaultDataExtrator implements ExtIfDataExtrator {

	@Autowired
	private ExtIfService extIfService;

	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public void extrator(LocalDateTime lastExecutedTime) {
		extractIssues(lastExecutedTime);

	}
	
	/**
	 * Extract the issues from Redmine
	 * 
	 * @param lastExecutedTime
	 *            execute the external if last.
	 */
	private void extractIssues(LocalDateTime lastExecutedTime) {

		// Get Redmine Setting. It should never be null
		RedmineSetting redmineSetting = extIfService.getExtIfSetting().getRedmineSetting();

		// Get the max Updated_on item value if the previous execution was
		// success
		String maxUpdatedOn = getLastUpdateOnInLastExecuted(redmineSetting);

		// Drop the issue collection
		mongoTemplate.dropCollection(redmineSetting.getIssueCollectionName());
		List<?> issuesList = null;
		int offset = 0;
		do {
			RestTemplate restTemplate = new RestTemplate();
			// Set the http request header
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			if (redmineSetting.getHttpHeaders() != null) {
				headers.putAll(redmineSetting.getHttpHeadersMap());
			}

			// Set the http request body
			Map<String, String> requestParam = new LinkedHashMap<String, String>();
			// Get all status of the issues
			requestParam.put("status_id", "*");
			// Get the target version
			requestParam.put("fixed_version_id", redmineSetting.getFixVersionId());
			// Set the limit in one request
			requestParam.put("limit", String.valueOf(redmineSetting.getLimit()));
			// Set the offset
			requestParam.put("offset", String.valueOf(offset));
			// Set the update on time
			if (!StringUtils.isEmpty(maxUpdatedOn)) {
				requestParam.put("updated_on", ">=" + maxUpdatedOn);
			}
			HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
			// TODO
			String url = redmineSetting.getRootUrl() + "/" + redmineSetting.getIssueUrl() + "?";
			for (String key : requestParam.keySet()) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(url);
				stringBuilder.append("&");
				stringBuilder.append(key);
				stringBuilder.append("=");
				stringBuilder.append("{");
				stringBuilder.append(key);
				stringBuilder.append("}");
				url = stringBuilder.toString();
			}

			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class,
					requestParam);
			Object parseResult = JSON.parse(result.getBody());
			if (!(parseResult instanceof DBObject)) {
				throw new SystemException("cannot get the data by api");
			}
			DBObject dbObject = (DBObject) parseResult;
			issuesList = (List<?>) dbObject.get("issues");
			if (issuesList != null && issuesList.size() > 0) {
				mongoTemplate.insert(issuesList, redmineSetting.getIssueCollectionName());
			}
			// set the offset
			offset = offset + issuesList.size();

		} while (issuesList != null && issuesList.size() > 0 && issuesList.size() == redmineSetting.getLimit());

	}

	public ExtIfService getExtIfService() {
		return extIfService;
	}

	public void setExtIfService(ExtIfService extIfService) {
		this.extIfService = extIfService;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Get the last UpdateOn item value in the previously execeuted.
	 * 
	 * @param redmineSetting
	 * @return
	 */
	private String getLastUpdateOnInLastExecuted(RedmineSetting redmineSetting) {

		// Aggregation aggregation =
		// Aggregation.newAggregation(Aggregation.sort(Direction.DESC,
		// "updated_on"),
		// Aggregation.limit(1));
		// AggregationResults<DBObject> result =
		// mongoTemplate.aggregate(aggregation,
		// redmineSetting.getIssueCollectionName(), DBObject.class);
		// List<DBObject> dbObjectList = result.getMappedResults();
		// if (dbObjectList != null && dbObjectList.size() > 0) {
		// return result.getMappedResults().get(0).get("updated_on").toString();
		// }
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().max("updated_on").as("updated_on"));
		AggregationResults<DBObject> result = mongoTemplate.aggregate(aggregation,
				redmineSetting.getIssueCollectionName(), DBObject.class);
		List<DBObject> dbObjectList = result.getMappedResults();
		if (dbObjectList != null && dbObjectList.size() > 0) {
			return result.getMappedResults().get(0).get("updated_on").toString();
		}
		return null;

	}

}
