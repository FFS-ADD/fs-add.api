package com.accenture.fsadd.extif.redmine;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StringUtils;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.backlog.business.service.BackLogService;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.issues.business.service.IssueService;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.qa.business.service.QueryAnswerService;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;
import com.accenture.fsadd.dashboard.task.business.service.TaskService;
import com.accenture.fsadd.extif.ExtIfDataMapper;
import com.accenture.fsadd.extif.entity.RedmineSetting;
import com.accenture.fsadd.extif.service.ExtIfService;
import com.mongodb.BasicDBObject;

public class DefaultRedmineDataMapper implements ExtIfDataMapper {

	@Autowired
	private ExtIfService extIfService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private QueryAnswerService queryAnswerService;

	@Autowired
	private IssueService issueServcie;

	@Autowired
	private BackLogService backLogService;

	@Autowired
	private TaskService taskService;

	@Override
	public void map(LocalDateTime lastExecutedTime) {

		// Get Redmine Setting. It should never be null
		RedmineSetting redmineSetting = extIfService.getExtIfSetting().getRedmineSetting();
		List<BasicDBObject> issueList = mongoTemplate.findAll(BasicDBObject.class,
				redmineSetting.getIssueCollectionName());
		Set<BigInteger> backLogIds = new HashSet<>();
		Map<BigInteger, List<BigInteger>> parentChildTaskIds = new HashMap<>();
		Map<BigInteger, BigInteger> childParentIds = new HashMap<>();
		for (BasicDBObject dbObject : issueList) {

			BasicDBObject trackerDBObjct = (BasicDBObject) dbObject.get("tracker");
			Integer trackerId = trackerDBObjct.getInt("id");
			// QA
			if (redmineSetting.getQaSetting().getTrackerId().contains(trackerId)) {
				mapToQA(lastExecutedTime, redmineSetting, dbObject);
			}
			// Issue
			if (redmineSetting.getIssueSetting().getTrackerId().contains(trackerId)) {
				mapToIssue(lastExecutedTime, redmineSetting, dbObject);
			}
			// BackLog
			if (redmineSetting.getBackLogSetting().getTrackerId().contains(trackerId)) {
				mapToBackLog(lastExecutedTime, redmineSetting, dbObject, backLogIds);
			}
			// Task
			if (redmineSetting.getTaskSetting().getTrackerId().containsKey(trackerId)) {
				mapToTask(trackerId, lastExecutedTime, redmineSetting, dbObject, parentChildTaskIds, childParentIds);
			}

		}

		// Collections
		for (BigInteger parentKey : parentChildTaskIds.keySet()) {
			// Parent is not back log
			if (!backLogIds.contains(parentKey)) {
				replaceAndRemoveMediumParentId(parentKey, parentChildTaskIds, childParentIds, backLogIds);
			}else if(parentChildTaskIds.get(parentKey).size() > 1){ 
				// Backlog self should be delete from taskEntity
				taskService.delete(parentKey);				
			}
		}
		queryAnswerService.aggregateSummaryService(lastExecutedTime.toLocalDate());
		issueServcie.aggregateSummaryService(lastExecutedTime.toLocalDate());
		taskService.aggregateSummaryService(lastExecutedTime.toLocalDate());
		backLogService.aggregateSummaryService(lastExecutedTime.toLocalDate());
	}

	/**
	 * Map external if data to query and answer entity
	 * 
	 * @param lastExecutedTime
	 *            time that last extif was executed.
	 * @param redmineSetting
	 *            Redmine Setting
	 * @param dbObject
	 *            Db Object
	 */
	protected void mapToQA(LocalDateTime lastExecutedTime, RedmineSetting redmineSetting, BasicDBObject dbObject) {

		QueryAnswerEntity queryAnswerEntity = new QueryAnswerEntity();
		queryAnswerEntity.setId(BigInteger.valueOf(dbObject.getInt("id")));
		queryAnswerEntity.setCreatedOn(converIOSDateTime(dbObject.getString("created_on")));
		queryAnswerEntity.setUpdatedOn(converIOSDateTime(dbObject.getString("updated_on")));
		queryAnswerEntity.setClosedOn(converIOSDateTime(dbObject.getString("closed_on")));
		queryAnswerEntity.setExpectedAnswerOn(converIOSDate(dbObject.getString("due_date")));
		queryAnswerEntity.setOwner(getNestedDbObjectString((BasicDBObject) dbObject.get("assigned_to"), "name"));
		queryAnswerEntity.setQuestioner(getNestedDbObjectString((BasicDBObject) dbObject.get("author"), "name"));
		queryAnswerEntity.setTitle(dbObject.getString("subject"));
		Integer statusId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("status"), "id");
		QueryAnswerEntity.Status status = null;
		if (statusId != null) {
			status = redmineSetting.getQaSetting().getStatusMappingSetting().get(statusId);
		}
		if (status == null) {
			status = QueryAnswerEntity.Status.INPROGRESS;
		}
		queryAnswerEntity.setStatus(status);
		if (queryAnswerEntity.getStatus() == QueryAnswerEntity.Status.CLOSED
				&& queryAnswerEntity.getClosedOn() == null) {
			queryAnswerEntity.setClosedOn(queryAnswerEntity.getUpdatedOn());

		}
		queryAnswerService.save(queryAnswerEntity);
	}

	/**
	 * Map external if data to Issue Entity
	 * 
	 * @param lastExecutedTime
	 *            time that last extif was executed.
	 * @param redmineSetting
	 *            Redmine Setting
	 * @param dbObject
	 *            Db Object
	 */
	@SuppressWarnings("unchecked")
	protected void mapToIssue(LocalDateTime lastExecutedTime, RedmineSetting redmineSetting, BasicDBObject dbObject) {

		IssueEntity issueEntity = new IssueEntity();
		issueEntity.setId(BigInteger.valueOf(dbObject.getInt("id")));
		issueEntity.setCreatedOn(converIOSDateTime(dbObject.getString("created_on")));
		issueEntity.setUpdatedOn(converIOSDateTime(dbObject.getString("updated_on")));
		issueEntity.setClosedOn(converIOSDateTime(dbObject.getString("closed_on")));
		issueEntity.setDueDate(converIOSDate(dbObject.getString("due_date")));
		issueEntity.setOwner(getNestedDbObjectString((BasicDBObject) dbObject.get("assigned_to"), "name"));
		issueEntity.setTitle(dbObject.getString("subject"));
		Integer statusId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("status"), "id");
		IssueEntity.Status status = null;
		if (statusId != null) {
			status = redmineSetting.getIssueSetting().getStatusMappingSetting().get(statusId);
		}
		if (status == null) {
			status = IssueEntity.Status.INPROGRESS;
		}
		issueEntity.setStatus(status);
		if (issueEntity.getStatus() == IssueEntity.Status.CLOSED && issueEntity.getClosedOn() == null) {
			issueEntity.setClosedOn(issueEntity.getUpdatedOn());

		}
		Integer priorityId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("priority"), "id");
		IssueEntity.Priority priority = null;
		if (priorityId != null) {
			priority = redmineSetting.getIssueSetting().getPriorityMappingSetting().get(priorityId);
		}
		if (priority == null) {
			priority = IssueEntity.Priority.MEDIUM;
		}
		issueEntity.setPriority(priority);

		List<BasicDBObject> customFields = (List<BasicDBObject>) dbObject.get("custom_fields");
		for (BasicDBObject customField : customFields) {
			Integer id = getNestedDbObjectInteger(customField, "id");
			if (id == null) {
				continue;
			}
			if (id.equals(redmineSetting.getIssueSetting().getCustomIdDetector())) {
				issueEntity.setDetector(customField.getString("value"));
			}
			if (id.equals(redmineSetting.getIssueSetting().getCustomIdOccuredDate())) {
				issueEntity.setOccuredDate(converIOSDate(customField.getString("value")));
			}
			if (id.equals(redmineSetting.getIssueSetting().getCustomIdPhase())) {
				issueEntity.setPhase(
						redmineSetting.getIssueSetting().getPhaseMappingSetting().get(customField.getString("value")));
			}
		}
		if (issueEntity.getDetector() == null) {
			issueEntity.setDetector(getNestedDbObjectString((BasicDBObject) dbObject.get("author"), "name"));
		}
		if (issueEntity.getOccuredDate() == null) {
			issueEntity.setOccuredDate(issueEntity.getCreatedOn());
		}
		issueServcie.save(issueEntity);
	}

	/**
	 * Map external if data to BackLog Entity
	 * 
	 * @param lastExecutedTime
	 *            time that last extif was executed.
	 * @param redmineSetting
	 *            Redmine Setting
	 * @param dbObject
	 *            Db Object
	 * @param backLogIds
	 *            BackLog Id set
	 */
	@SuppressWarnings("unchecked")
	protected void mapToBackLog(LocalDateTime lastExecutedTime, RedmineSetting redmineSetting, BasicDBObject dbObject,
			Set<BigInteger> backLogIds) {
		if (dbObject.get("parent") != null) {
			return;
		}

		BackLogEntity backLogEntity = new BackLogEntity();
		backLogEntity.setId(BigInteger.valueOf(dbObject.getInt("id")));
		backLogEntity.setCreatedOn(converIOSDateTime(dbObject.getString("created_on")));
		backLogEntity.setUpdatedOn(converIOSDateTime(dbObject.getString("updated_on")));
		backLogEntity.setClosedOn(converIOSDateTime(dbObject.getString("closed_on")));
		backLogEntity.setPlanedStartDate(converIOSDate(dbObject.getString("start_date")));
		backLogEntity.setPlanedEndDate(converIOSDate(dbObject.getString("due_date")));
		backLogEntity.setDoneRatio(dbObject.getInt("done_ratio"));
		backLogEntity.setOwner(getNestedDbObjectString((BasicDBObject) dbObject.get("assigned_to"), "name"));
		backLogEntity.setTitle(dbObject.getString("subject"));
		Integer statusId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("status"), "id");
		BackLogEntity.Status status = null;
		if (statusId != null) {
			status = redmineSetting.getBackLogSetting().getStatusMappingSetting().get(statusId);
		}
		if (status == null) {
			status = BackLogEntity.Status.INPROGRESS;
		}
		backLogEntity.setStatus(status);
		if (backLogEntity.getStatus() == BackLogEntity.Status.CLOSED && backLogEntity.getClosedOn() == null) {
			backLogEntity.setClosedOn(backLogEntity.getUpdatedOn());

		}
		Integer priorityId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("priority"), "id");
		BackLogEntity.Priority priority = null;
		if (priorityId != null) {
			priority = redmineSetting.getBackLogSetting().getPriorityMappingSetting().get(priorityId);
		}
		if (priority == null) {
			priority = BackLogEntity.Priority.MEDIUM;
		}
		backLogEntity.setPriority(priority);

		List<BasicDBObject> customFields = (List<BasicDBObject>) dbObject.get("custom_fields");
		for (BasicDBObject customField : customFields) {
			Integer id = getNestedDbObjectInteger(customField, "id");
			if (id == null) {
				continue;
			}
			if (id.equals(redmineSetting.getBackLogSetting().getCustomIdEstimatedHours())) {
				backLogEntity.setEstimatedHours(dbObject.getInt("value"));
			}
			if (id.equals(redmineSetting.getBackLogSetting().getCustomIdActualHours())) {
				backLogEntity.setActualHours(dbObject.getInt("value"));
			}
			if (id.equals(redmineSetting.getBackLogSetting().getCustomIdActualStartDate())) {
				backLogEntity.setActualStartDate(this.getNestedDbObjectLocalDate(customField, "value"));
			}
			if (id.equals(redmineSetting.getBackLogSetting().getCustomIdActualEndDate())) {
				backLogEntity.setActualEndDate(this.getNestedDbObjectLocalDate(customField, "value"));
			}

		}
		backLogIds.add(backLogEntity.getId());
		backLogService.save(backLogEntity);
	}

	/**
	 * Map external if data to task Entity
	 * 
	 * @param trackerId
	 *            Tacker ID
	 * @param lastExecutedTime
	 *            time that last extif was executed.
	 * @param redmineSetting
	 *            Redmine Setting
	 * @param dbObject
	 *            Db Object
	 * @param parentChildTaskIds
	 *            Parent -> Child Task Id Map;
	 * @param childParentIds
	 *            Child -> Parent Task Id Map;
	 */
	@SuppressWarnings("unchecked")
	protected void mapToTask(Integer trackerId, LocalDateTime lastExecutedTime, RedmineSetting redmineSetting,
			BasicDBObject dbObject, Map<BigInteger, List<BigInteger>> parentChildTaskIds,
			Map<BigInteger, BigInteger> childParentIds) {

		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(BigInteger.valueOf(dbObject.getInt("id")));
		taskEntity.setType(redmineSetting.getTaskSetting().getTrackerId().get(trackerId));
		if (taskEntity.getType() == null) {
			taskEntity.setType(TaskEntity.Type.BUILD);
		}
		taskEntity.setCreatedOn(converIOSDateTime(dbObject.getString("created_on")));
		taskEntity.setUpdatedOn(converIOSDateTime(dbObject.getString("updated_on")));
		taskEntity.setClosedOn(converIOSDateTime(dbObject.getString("closed_on")));
		taskEntity.setPlanedStartDate(converIOSDate(dbObject.getString("start_date")));
		taskEntity.setPlanedEndDate(converIOSDate(dbObject.getString("due_date")));
		dbObject.getString("due_date")estimated_hours
		taskEntity.setEstimatedHours(estimatedHours);
		taskEntity.setDoneRatio(dbObject.getInt("done_ratio"));
		taskEntity.setOwner(getNestedDbObjectString((BasicDBObject) dbObject.get("assigned_to"), "name"));
		taskEntity.setTitle(dbObject.getString("subject"));
		Integer statusId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("status"), "id");
		TaskEntity.Status status = null;
		if (statusId != null) {
			status = redmineSetting.getTaskSetting().getStatusMappingSetting().get(statusId);
		}
		if (status == null) {
			status = TaskEntity.Status.INPROGRESS;
		}
		taskEntity.setStatus(status);
		if (taskEntity.getStatus() == TaskEntity.Status.CLOSED && taskEntity.getClosedOn() == null) {
			taskEntity.setClosedOn(taskEntity.getUpdatedOn());

		}
		Integer priorityId = getNestedDbObjectInteger((BasicDBObject) dbObject.get("priority"), "id");
		TaskEntity.Priority priority = null;
		if (priorityId != null) {
			priority = redmineSetting.getTaskSetting().getPriorityMappingSetting().get(priorityId);
		}
		if (priority == null) {
			priority = TaskEntity.Priority.MEDIUM;
		}
		taskEntity.setPriority(priority);

		List<BasicDBObject> customFields = (List<BasicDBObject>) dbObject.get("custom_fields");
		for (BasicDBObject customField : customFields) {
			Integer id = getNestedDbObjectInteger(customField, "id");
			if (id == null) {
				continue;
			}
			if (id.equals(redmineSetting.getTaskSetting().getCustomIdEstimatedHours())) {
				taskEntity.setEstimatedHours(dbObject.getInt("value"));
			}
			if (id.equals(redmineSetting.getTaskSetting().getCustomIdActualHours())) {
				taskEntity.setActualHours(dbObject.getInt("value"));
			}
			if (id.equals(redmineSetting.getTaskSetting().getCustomIdActualStartDate())) {
				taskEntity.setActualStartDate(this.getNestedDbObjectLocalDate(customField, "value"));
			}
			if (id.equals(redmineSetting.getTaskSetting().getCustomIdActualEndDate())) {
				taskEntity.setActualEndDate(this.getNestedDbObjectLocalDate(customField, "value"));
			}

		}
		Integer parentId = this.getNestedDbObjectInteger((BasicDBObject) dbObject.get("parent"), "id");
		if (parentId == null) {
			taskEntity.setBackLogId(taskEntity.getId());
		} else {
			taskEntity.setBackLogId(BigInteger.valueOf(parentId));
		}

		parentChildTaskIds.putIfAbsent(taskEntity.getBackLogId(), new ArrayList<>());
		parentChildTaskIds.get(taskEntity.getBackLogId()).add(taskEntity.getId());
		childParentIds.put(taskEntity.getId(), taskEntity.getBackLogId());
		taskService.save(taskEntity);
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

	public QueryAnswerService getQueryAnswerService() {
		return queryAnswerService;
	}

	public void setQueryAnswerService(QueryAnswerService queryAnswerService) {
		this.queryAnswerService = queryAnswerService;
	}

	protected LocalDate converIOSDateTime(String dateString) {
		if (StringUtils.isEmpty(dateString) || dateString.length() < 10) {
			return null;
		}
		return LocalDate.parse(dateString.substring(0, 10), DateTimeFormatter.ISO_DATE);
	}

	protected LocalDate converIOSDate(String dateString) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	}

	protected String getNestedDbObjectString(BasicDBObject nestedDbObject, String propertyName) {
		if (nestedDbObject == null) {
			return null;
		}
		return nestedDbObject.getString(propertyName);
	}

	protected LocalDate getNestedDbObjectLocalDate(BasicDBObject nestedDbObject, String propertyName) {
		String nestedValue = getNestedDbObjectString(nestedDbObject, propertyName);
		if (nestedValue == null) {
			return null;
		}
		if (nestedValue.length() > 10) {
			return this.converIOSDateTime(nestedValue);
		} else {
			return this.converIOSDate(nestedValue);
		}
	}

	protected Integer getNestedDbObjectInteger(BasicDBObject nestedDbObject, String propertyName) {
		String nestedValue = getNestedDbObjectString(nestedDbObject, propertyName);
		if (nestedValue == null) {
			return null;
		}
		try {
			return Integer.valueOf(nestedValue);
		} catch (Exception e) {
			return null;
		}
	}

	protected void replaceAndRemoveMediumParentId(BigInteger parentId,
			Map<BigInteger, List<BigInteger>> parentChildTaskIds, Map<BigInteger, BigInteger> childParentIds,
			Set<BigInteger> backLogIds) {
		for (BigInteger childId : parentChildTaskIds.get(parentId)) {
			// Skip if the child is the parent of other tasks
			if (parentChildTaskIds.containsKey(childId)) {
				continue;
			}

			// Find the root parent task
			BigInteger nextChildId = parentId;
			BigInteger nextParentId = childParentIds.get(nextChildId);

			while (nextParentId != null && !nextParentId.equals(nextChildId)) {
				nextChildId = nextParentId;
				nextParentId = childParentIds.get(nextChildId);
			}
			// cause cannot find the backLog, it should be delete from the task
			// list
			if (!backLogIds.contains(nextChildId)) {
				taskService.delete(childId);
			} else {
				taskService.updateBackLogId(childId, nextChildId);
			}

		}
		taskService.delete(parentId);

	}

	public IssueService getIssueServcie() {
		return issueServcie;
	}

	public void setIssueServcie(IssueService issueServcie) {
		this.issueServcie = issueServcie;
	}

	public BackLogService getBackLogService() {
		return backLogService;
	}

	public void setBackLogService(BackLogService backLogService) {
		this.backLogService = backLogService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
