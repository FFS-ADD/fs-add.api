package com.accenture.fsadd.extif.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.extif.ExtIfName;
import com.accenture.fsadd.extif.entity.ExtIfRunnerSetting;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus.Status;
import com.accenture.fsadd.extif.entity.ExtIfSetting;
import com.accenture.fsadd.extif.entity.RedmineSetting;
import com.accenture.fsadd.extif.entity.RedmineSetting.BackLogSetting;
import com.accenture.fsadd.extif.entity.RedmineSetting.IssueSetting;
import com.accenture.fsadd.extif.entity.RedmineSetting.QASetting;
import com.accenture.fsadd.extif.entity.RedmineSetting.TaskSetting;
import com.accenture.fsadd.extif.entity.SonarQubeSetting;
import com.accenture.fsadd.extif.service.ExtIfService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtIfServiceImplTest {

	@Autowired
	ExtIfService extIfService;

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Update redmine external If to processing
	 */
	// @Test
	public void updateRedmineExtIfStatusTest() {
		try {
			mongoTemplate.dropCollection(ExtIfRunnerStatus.class);
			// Processing
			extIfService.updateExtIfStatus(ExtIfName.Redmine, Status.PROCESSING, "");
			List<ExtIfRunnerStatus> extIfStatusList = mongoTemplate.findAll(ExtIfRunnerStatus.class);
			assertThat(extIfStatusList.size() == 1).isTrue();
			assertThat(extIfStatusList.get(0).getStatus() == Status.PROCESSING).isTrue();
			assertThat(extIfStatusList.get(0).getStartedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getEndedOn() == null).isTrue();
			// Completed
			extIfService.updateExtIfStatus(ExtIfName.Redmine, Status.COMPLETED, "");
			extIfStatusList = mongoTemplate.findAll(ExtIfRunnerStatus.class);
			assertThat(extIfStatusList.size() == 1).isTrue();
			assertThat(extIfStatusList.get(0).getStatus() == Status.COMPLETED).isTrue();
			assertThat(extIfStatusList.get(0).getStartedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getLastExecutedOn().equals(extIfStatusList.get(0).getStartedOn()))
					.isTrue();
			assertThat(extIfStatusList.get(0).getEndedOn() != null).isTrue();

			// Stopped
			extIfService.updateExtIfStatus(ExtIfName.Redmine, Status.STOPPED, "Stopped");
			extIfStatusList = mongoTemplate.findAll(ExtIfRunnerStatus.class);
			assertThat(extIfStatusList.size() == 1).isTrue();
			assertThat(extIfStatusList.get(0).getStatus() == Status.STOPPED).isTrue();
			assertThat(extIfStatusList.get(0).getStartedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getEndedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getMessage().equals("Stopped")).isTrue();
			// Error
			extIfService.updateExtIfStatus(ExtIfName.Redmine, Status.ERROR, "Error");
			extIfStatusList = mongoTemplate.findAll(ExtIfRunnerStatus.class);
			assertThat(extIfStatusList.size() == 1).isTrue();
			assertThat(extIfStatusList.get(0).getStatus() == Status.ERROR).isTrue();
			assertThat(extIfStatusList.get(0).getStartedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getEndedOn() != null).isTrue();
			assertThat(extIfStatusList.get(0).getMessage().equals("Error")).isTrue();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception Occurred" + e.toString());
		}
	}

	@Test
	public void testExtIfSettingService() {
		try {
			ExtIfSetting extIfSetting = new ExtIfSetting();
			RedmineSetting redmineSetting = new RedmineSetting();
			// QA Setting
			QASetting qaSetting = new QASetting();
			Set<Integer> tempSet = new HashSet<>();
			tempSet.add(3);
			qaSetting.setTrackerId(tempSet);
			Map<Integer, QueryAnswerEntity.Status> tempMap = new HashMap<>();
			tempMap.put(1, QueryAnswerEntity.Status.NEW);
			tempMap.put(2, QueryAnswerEntity.Status.INPROGRESS);
			tempMap.put(4, QueryAnswerEntity.Status.INPROGRESS);
			tempMap.put(5, QueryAnswerEntity.Status.CLOSED);
			qaSetting.setStatusMappingSetting(tempMap);
			redmineSetting.setQaSetting(qaSetting);

			// Issue Setting
			IssueSetting issueSetting = new IssueSetting();
			tempSet = new HashSet<>();
			tempSet.add(28);
			issueSetting.setTrackerId(tempSet);
			Map<Integer, IssueEntity.Status> issueStatusMap = new HashMap<>();
			issueStatusMap.put(97, IssueEntity.Status.CLOSED);
			issueStatusMap.put(95, IssueEntity.Status.CLOSED);
			issueStatusMap.put(96, IssueEntity.Status.CLOSED);
			issueStatusMap.put(94, IssueEntity.Status.RETESTING);
			issueSetting.setStatusMappingSetting(issueStatusMap);

			Map<Integer, IssueEntity.Priority> issuepriorityMappingSetting = new HashMap<>();
			issuepriorityMappingSetting.put(4, IssueEntity.Priority.CRITICAL);
			issuepriorityMappingSetting.put(3, IssueEntity.Priority.HIGH);
			issuepriorityMappingSetting.put(2, IssueEntity.Priority.MEDIUM);
			issuepriorityMappingSetting.put(1, IssueEntity.Priority.LOW);
			issueSetting.setPriorityMappingSetting(issuepriorityMappingSetting);
			issueSetting.setCustomIdPhase(158);
			Map<String, IssueEntity.Phase> issuePhaseMappingSetting = new HashMap<>();
			issuePhaseMappingSetting.put("01 ITa", IssueEntity.Phase.ITA);
			issuePhaseMappingSetting.put("02 ITb1 - オンラインIF疎通検証", IssueEntity.Phase.ITB);
			issuePhaseMappingSetting.put("02 ITb1 - 拠点間疎通確認", IssueEntity.Phase.ITB);
			issuePhaseMappingSetting.put("03 ITb2 - 移行検証", IssueEntity.Phase.ITB);
			issuePhaseMappingSetting.put("03 ITb2 - 多機種検証", IssueEntity.Phase.ITB);
			issuePhaseMappingSetting.put("06 行員試行", IssueEntity.Phase.UAT);
			issueSetting.setPhaseMappingSetting(issuePhaseMappingSetting);
			issueSetting.setCustomIdDetector(115);
			issueSetting.setCustomIdOccuredDate(314);
			redmineSetting.setIssueSetting(issueSetting);

			// Task Setting
			TaskSetting taskSetting = new TaskSetting();
			Map<Integer, TaskEntity.Type> trackerIdMap = new HashMap<>();
			trackerIdMap.put(43, TaskEntity.Type.DEVELOPMENT);
			trackerIdMap.put(42, TaskEntity.Type.DEVELOPMENT);
			trackerIdMap.put(46, TaskEntity.Type.DEVELOPMENT);
			trackerIdMap.put(47, TaskEntity.Type.DEVELOPMENT);
			trackerIdMap.put(40, TaskEntity.Type.DEPLOYMENT);
			taskSetting.setTrackerId(trackerIdMap);
			Map<Integer, TaskEntity.Priority> taskPriorityMappingSetting = new HashMap<>();
			taskPriorityMappingSetting.put(4, TaskEntity.Priority.CRITICAL);
			taskPriorityMappingSetting.put(3, TaskEntity.Priority.HIGH);
			taskPriorityMappingSetting.put(2, TaskEntity.Priority.MEDIUM);
			taskPriorityMappingSetting.put(1, TaskEntity.Priority.LOW);
			taskSetting.setPriorityMappingSetting(taskPriorityMappingSetting);

			Map<Integer, TaskEntity.Status> taskStatusMap = new HashMap<>();
			taskStatusMap.put(73, TaskEntity.Status.NEW);
			taskStatusMap.put(75, TaskEntity.Status.INPROGRESS);
			taskStatusMap.put(91, TaskEntity.Status.INPROGRESS);
			taskStatusMap.put(96, TaskEntity.Status.PENDING);
			taskStatusMap.put(95, TaskEntity.Status.CLOSED);
			taskStatusMap.put(103, TaskEntity.Status.CLOSED);
			taskSetting.setStatusMappingSetting(taskStatusMap);
			redmineSetting.setTaskSetting(taskSetting);
			// BackLog Setting
			BackLogSetting backLogSetting = new BackLogSetting();
			tempSet = new HashSet<>();
			tempSet.add(42);
			backLogSetting.setTrackerId(tempSet);
			Map<Integer, BackLogEntity.Priority> backLogPriorityMappingSetting = new HashMap<>();
			backLogPriorityMappingSetting.put(4, BackLogEntity.Priority.CRITICAL);
			backLogPriorityMappingSetting.put(3, BackLogEntity.Priority.HIGH);
			backLogPriorityMappingSetting.put(2, BackLogEntity.Priority.MEDIUM);
			backLogPriorityMappingSetting.put(1, BackLogEntity.Priority.LOW);
			backLogSetting.setPriorityMappingSetting(backLogPriorityMappingSetting);

			Map<Integer, BackLogEntity.Status> backLogStatusMap = new HashMap<>();
			backLogStatusMap.put(73, BackLogEntity.Status.NEW);
			backLogStatusMap.put(75, BackLogEntity.Status.INPROGRESS);
			backLogStatusMap.put(91, BackLogEntity.Status.INPROGRESS);
			backLogStatusMap.put(96, BackLogEntity.Status.PENDING);
			backLogStatusMap.put(95, BackLogEntity.Status.CLOSED);
			backLogSetting.setStatusMappingSetting(backLogStatusMap);

			redmineSetting.setBackLogSetting(backLogSetting);

			// tracker.setQaId(34);
			// tracker.setBackLogId(42);
			// tracker.setBugId(28);
			// tracker.setTaskId(new int[]{42, 43, 46, 47, 48});
			redmineSetting.setQaSetting(qaSetting);
			redmineSetting.setRootUrl("");
			redmineSetting.setHttpHeaders("Authorization:Basic");
			redmineSetting.setIssueUrl("issues.json");
			redmineSetting.setFixVersionId("118");
			redmineSetting.setIssueCollectionName("redmine_issues");
			redmineSetting.setLimit(100);
			ExtIfRunnerSetting extIfRunnerSetting = new ExtIfRunnerSetting();
			extIfRunnerSetting.setCronExpression("*/5 * * * * ?");
			extIfSetting.setRedmineSetting(redmineSetting);
			extIfSetting.setExtIfRunnerSetting(extIfRunnerSetting);

			// SonarQub
			SonarQubeSetting sonarQubeSetting = new SonarQubeSetting();
			sonarQubeSetting.setProjectKey("fs-add.api");
			sonarQubeSetting.setDashboardCollectionName("sonardashboard");
			sonarQubeSetting.setIssuesCollectionName("sonarqube_issues");
			sonarQubeSetting.setRootUrl("http://localhost:9000/sonar");
			sonarQubeSetting.setMeasuresUrl(
					"/api/measures/component?metricKeys=alert_status,vulnerabilities,bugs,code_smells,ncloc,lines,statements,files,tests,test_failures,test_success_density,coverage,duplicated_lines,duplicated_blocks,duplicated_files,duplicated_lines_density&componentKey=");
			extIfSetting.setSonarQubeSetting(sonarQubeSetting);
			extIfService.saveExtIfSetting(extIfSetting);
			// ExtIfSetting extIfSettingSaved = extIfService.getExtIfSetting();
			// assertThat(extIfSetting.equals(extIfSettingSaved)).isTrue();

			// ExtIfSetting extIfSetting = new ExtIfSetting();
			// RedmineSetting redmineSetting = new RedmineSetting();
			// redmineSetting.setRootUrl("http://www.redmine.org/projects/redmine/");
			// //redmineSetting.setHttpHeaders("Authorization:Basic
			// eGlhb2RvbmcuZGVuZzppb25pYyMxMTEx");
			// redmineSetting.setIssueUrl("issues.json");
			// redmineSetting.setFixVersionId("92");
			// redmineSetting.setIssueCollectionName("redmine_issues");
			// redmineSetting.setLimit(5);
			// ExtIfRunnerSetting extIfRunnerSetting = new ExtIfRunnerSetting();
			// extIfRunnerSetting.setCronExpression("*/5 * * * * ?");
			// extIfSetting.setRedmineSetting(redmineSetting);
			// extIfSetting.setExtIfRunnerSetting(extIfRunnerSetting);
			// extIfService.saveExtIfSetting(extIfSetting);
			// ExtIfSetting extIfSettingSaved = extIfService.getExtIfSetting();
			// assertThat(extIfSetting.equals(extIfSettingSaved)).isTrue();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception Occurred" + e.toString());
		}

	}
}
