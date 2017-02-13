package com.accenture.fsadd.extif.sonarqube;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.common.FsaddUtil;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
@ConfigurationProperties
public class SonarqubeDataMapping {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SonarqubeSetting sonarqubeSetting;
	
	@Value("${fsadd.sonarqube.mongodb.collection.sonardashboard}")
	private String sonardashboardCollectionName;
	
	public void mappingDataToSonarDashboard(){
		// get sonarqube date
		String result = mongoTemplate.findOne(
				new Query(Criteria.where(FsaddConstant.SONARQUBE_ISSUES_KEY).is(FsaddConstant.SONARQUBE_TEST_PROJECT_KEY)).with(new Sort(Direction.DESC, FsaddConstant.INSERT_DATA_COL)), String.class, sonarqubeSetting.getIssueCollectionName());
		DBObject dbObject = (DBObject)JSON.parse(result);
		@SuppressWarnings("unchecked")
		List<DBObject> list = (List<DBObject>)dbObject.get(FsaddConstant.SONARQUBE_MEASURES_KEY);
		
		Map<String, String> sonarMap = new HashMap<String, String>();
		for(DBObject object : list){
			if(object.get(FsaddConstant.SONARQUBE_METRIC_KEY) != null){
				if(object.get(FsaddConstant.SONARQUBE_VALUE_KEY) != null){
					sonarMap.put(object.get(FsaddConstant.SONARQUBE_METRIC_KEY).toString(), object.get(FsaddConstant.SONARQUBE_VALUE_KEY).toString());
				}else{
					sonarMap.put(object.get(FsaddConstant.SONARQUBE_METRIC_KEY).toString(),"");					
				}
			}
		}
		
		// mapping sonarqube date to sonardashboard data
		Sonardashboard sonardashboard = createSonardashboardEntity(sonarMap);
		
		// create sonardashboard data
		mongoTemplate.insert(sonardashboard, sonardashboardCollectionName);
	}
	
	private Sonardashboard createSonardashboardEntity(Map<String, String> sonarMap){
		Sonardashboard sonardashboard = new Sonardashboard();
		
		// get quality gate
		sonardashboard.setQualityGateStatus(FsaddUtil.nullOrEmptyToZero(sonarMap.get("alert_status")));
		sonardashboard.setVulneralilities(FsaddUtil.nullOrEmptyToZero(sonarMap.get("vulnerabilities")));
		sonardashboard.setBugs(FsaddUtil.nullOrEmptyToZero(sonarMap.get("bugs")));
		sonardashboard.setCodeSmells(FsaddUtil.nullOrEmptyToZero(sonarMap.get("code_smells")));
		
		// get loc
		sonardashboard.setCodeLines(FsaddUtil.nullOrEmptyToZero(sonarMap.get("ncloc")));
		sonardashboard.setLine(FsaddUtil.nullOrEmptyToZero(sonarMap.get("lines")));
		sonardashboard.setStatement(FsaddUtil.nullOrEmptyToZero(sonarMap.get("statements")));
		sonardashboard.setFile(FsaddUtil.nullOrEmptyToZero(sonarMap.get("files")));
		
		// get coverage
		sonardashboard.setCoverage(FsaddUtil.nullOrEmptyToZero(sonarMap.get("coverage")));
		sonardashboard.setTests(FsaddUtil.nullOrEmptyToZero(sonarMap.get("tests")));
		sonardashboard.setSuccess(FsaddUtil.nullOrEmptyToZero(sonarMap.get("test_success_density")));
		sonardashboard.setFailures(FsaddUtil.nullOrEmptyToZero(sonarMap.get("test_failures")));
		
		// get duplication
		sonardashboard.setDuplication(FsaddUtil.nullOrEmptyToZero(sonarMap.get("duplicated_lines_density")));
		sonardashboard.setdLine(FsaddUtil.nullOrEmptyToZero(sonarMap.get("duplicated_lines")));
		sonardashboard.setBlocks(FsaddUtil.nullOrEmptyToZero(sonarMap.get("duplicated_blocks")));
		sonardashboard.setdFile(FsaddUtil.nullOrEmptyToZero(sonarMap.get("duplicated_files")));
		
		return sonardashboard;
	}

}