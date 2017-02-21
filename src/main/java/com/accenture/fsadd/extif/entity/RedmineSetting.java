package com.accenture.fsadd.extif.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;

public class RedmineSetting {

	/**
	 * QA Setting
	 *
	 */
	public static class QASetting {

		/**
		 * Tracked Id
		 */
		private Set<Integer> trackerId;

		/**
		 * Status ID <-> Status Mapping
		 */
		Map<Integer, QueryAnswerEntity.Status> statusMappingSetting;

		public Set<Integer> getTrackerId() {
			return trackerId;
		}

		public void setTrackerId(Set<Integer> trackerId) {
			this.trackerId = trackerId;
		}

		public Map<Integer, QueryAnswerEntity.Status> getStatusMappingSetting() {
			return statusMappingSetting;
		}

		public void setStatusMappingSetting(Map<Integer, QueryAnswerEntity.Status> statusMappingSetting) {
			this.statusMappingSetting = statusMappingSetting;
		}

	}

	/**
	 * Issue Setting
	 * 
	 *
	 */
	public static class IssueSetting {
		/**
		 * Tracked Id
		 */
		private Set<Integer> trackerId;

		/**
		 * Status setting
		 */
		private Map<Integer, IssueEntity.Status> statusMappingSetting;

		/**
		 * 
		 * Priority Setting
		 *
		 */
		private Map<Integer, IssueEntity.Priority> priorityMappingSetting;

		/**
		 * Custom ID: Phase
		 */
		private Integer customIdPhase;
		/**
		 * Phase Setting
		 *
		 */
		private Map<String, IssueEntity.Phase> phaseMappingSetting;

		/**
		 * Custom ID:Detector
		 */
		private Integer customIdDetector;
		/**
		 * Custom ID:Ocurred Date
		 */
		private Integer customIdOccuredDate;

		public Set<Integer> getTrackerId() {
			return trackerId;
		}

		public void setTrackerId(Set<Integer> trackerId) {
			this.trackerId = trackerId;
		}

		public Map<Integer, IssueEntity.Status> getStatusMappingSetting() {
			return statusMappingSetting;
		}

		public void setStatusMappingSetting(Map<Integer, IssueEntity.Status> statusMappingSetting) {
			this.statusMappingSetting = statusMappingSetting;
		}

		public Map<Integer, IssueEntity.Priority> getPriorityMappingSetting() {
			return priorityMappingSetting;
		}

		public void setPriorityMappingSetting(Map<Integer, IssueEntity.Priority> priorityMappingSetting) {
			this.priorityMappingSetting = priorityMappingSetting;
		}

		public Integer getCustomIdPhase() {
			return customIdPhase;
		}

		public void setCustomIdPhase(Integer customIdPhase) {
			this.customIdPhase = customIdPhase;
		}

		public Map<String, IssueEntity.Phase> getPhaseMappingSetting() {
			return phaseMappingSetting;
		}

		public void setPhaseMappingSetting(Map<String, IssueEntity.Phase> phaseMappingSetting) {
			this.phaseMappingSetting = phaseMappingSetting;
		}

		public Integer getCustomIdDetector() {
			return customIdDetector;
		}

		public void setCustomIdDetector(Integer customIdDetector) {
			this.customIdDetector = customIdDetector;
		}

		public Integer getCustomIdOccuredDate() {
			return customIdOccuredDate;
		}

		public void setCustomIdOccuredDate(Integer customIdOccuredDate) {
			this.customIdOccuredDate = customIdOccuredDate;
		}

	}

	public static class TaskSetting {

		/**
		 * Tracker ID Setting
		 */
		private Map<Integer, TaskEntity.Type> trackerId;

		/**
		 * Status setting
		 */
		private Map<Integer, TaskEntity.Status> statusMappingSetting;

		/**
		 * 
		 * Priority Setting
		 *
		 */
		private Map<Integer, TaskEntity.Priority> priorityMappingSetting;

		/**
		 * Custom ID:Actual Start Date
		 */
		private Integer customIdActualStartDate;

		/**
		 * Custom ID:Actual End Date
		 */
		private Integer customIdActualEndDate;

		/**
		 * Custom ID:Estimated Hours
		 */
		private Integer customIdEstimatedHours;

		/**
		 * Custom ID:Actual Hours
		 */
		private Integer customIdActualHours;

		public Map<Integer, TaskEntity.Type> getTrackerId() {
			return trackerId;
		}

		public void setTrackerId(Map<Integer, TaskEntity.Type> trackerId) {
			this.trackerId = trackerId;
		}

		public Map<Integer, TaskEntity.Status> getStatusMappingSetting() {
			return statusMappingSetting;
		}

		public void setStatusMappingSetting(Map<Integer, TaskEntity.Status> statusMappingSetting) {
			this.statusMappingSetting = statusMappingSetting;
		}

		public Map<Integer, TaskEntity.Priority> getPriorityMappingSetting() {
			return priorityMappingSetting;
		}

		public void setPriorityMappingSetting(Map<Integer, TaskEntity.Priority> priorityMappingSetting) {
			this.priorityMappingSetting = priorityMappingSetting;
		}

		public Integer getCustomIdActualStartDate() {
			return customIdActualStartDate;
		}

		public void setCustomIdActualStartDate(Integer customIdActualStartDate) {
			this.customIdActualStartDate = customIdActualStartDate;
		}

		public Integer getCustomIdActualEndDate() {
			return customIdActualEndDate;
		}

		public void setCustomIdActualEndDate(Integer customIdActualEndDate) {
			this.customIdActualEndDate = customIdActualEndDate;
		}

		public Integer getCustomIdEstimatedHours() {
			return customIdEstimatedHours;
		}

		public void setCustomIdEstimatedHours(Integer customIdEstimatedHours) {
			this.customIdEstimatedHours = customIdEstimatedHours;
		}

		public Integer getCustomIdActualHours() {
			return customIdActualHours;
		}

		public void setCustomIdActualHours(Integer customIdActualHours) {
			this.customIdActualHours = customIdActualHours;
		}

	}

	public static class BackLogSetting {

		/**
		 * Tracker ID Setting
		 */
		private Set<Integer> trackerId;

		/**
		 * Status setting
		 */
		private Map<Integer, BackLogEntity.Status> statusMappingSetting;

		/**
		 * 
		 * Priority Setting
		 *
		 */
		private Map<Integer, BackLogEntity.Priority> priorityMappingSetting;

		/**
		 * Custom ID:Actual Start Date
		 */
		private Integer customIdActualStartDate;

		/**
		 * Custom ID:Actual End Date
		 */
		private Integer customIdActualEndDate;

		/**
		 * Custom ID:Estimated Hours
		 */
		private Integer customIdEstimatedHours;

		/**
		 * Custom ID:Actual Hours
		 */
		private Integer customIdActualHours;

		public Set<Integer> getTrackerId() {
			return trackerId;
		}

		public void setTrackerId(Set<Integer> trackerId) {
			this.trackerId = trackerId;
		}

		public Map<Integer, BackLogEntity.Status> getStatusMappingSetting() {
			return statusMappingSetting;
		}

		public void setStatusMappingSetting(Map<Integer, BackLogEntity.Status> statusMappingSetting) {
			this.statusMappingSetting = statusMappingSetting;
		}

		public Map<Integer, BackLogEntity.Priority> getPriorityMappingSetting() {
			return priorityMappingSetting;
		}

		public void setPriorityMappingSetting(Map<Integer, BackLogEntity.Priority> priorityMappingSetting) {
			this.priorityMappingSetting = priorityMappingSetting;
		}

		public Integer getCustomIdActualStartDate() {
			return customIdActualStartDate;
		}

		public void setCustomIdActualStartDate(Integer customIdActualStartDate) {
			this.customIdActualStartDate = customIdActualStartDate;
		}

		public Integer getCustomIdActualEndDate() {
			return customIdActualEndDate;
		}

		public void setCustomIdActualEndDate(Integer customIdActualEndDate) {
			this.customIdActualEndDate = customIdActualEndDate;
		}

		public Integer getCustomIdEstimatedHours() {
			return customIdEstimatedHours;
		}

		public void setCustomIdEstimatedHours(Integer customIdEstimatedHours) {
			this.customIdEstimatedHours = customIdEstimatedHours;
		}

		public Integer getCustomIdActualHours() {
			return customIdActualHours;
		}

		public void setCustomIdActualHours(Integer customIdActualHours) {
			this.customIdActualHours = customIdActualHours;
		}

	}

	/**
	 * Redmine Root URL
	 */
	private String rootUrl;

	/**
	 * Issue URL
	 */
	private String issueUrl = "issues.json";

	/**
	 * 
	 * Target version
	 * 
	 */
	private String fixVersionId;

	/**
	 * Limit in one request
	 */
	private int limit;

	/**
	 * Redmine issues collection name
	 */
	private String issueCollectionName;

	/**
	 * Redmine HTTP Headers
	 */
	private String httpHeaders;

	/**
	 * QA Setting
	 */
	private QASetting qaSetting;

	/**
	 * Issue Setting
	 */
	private IssueSetting issueSetting;

	/**
	 * Task Setting
	 */
	private TaskSetting taskSetting;

	/**
	 * BackLog Setting
	 */
	private BackLogSetting backLogSetting;

	/**
	 * Get http headers as Map
	 * 
	 * @return http headers
	 */
	public Map<String, List<String>> getHttpHeadersMap() {
		if (!StringUtils.isEmpty(this.httpHeaders)) {
			Map<String, List<String>> headersMap = new HashMap<>();
			String[] headers = this.httpHeaders.split("\\|");
			for (String header : headers) {
				String[] headSet = header.split(":");
				if (headSet.length < 2) {
					throw new IllegalArgumentException("Wrong Http Header Setting.Http Header:" + header);
				}
				headersMap.put(headSet[0], Arrays.asList(headSet[1]));
			}
			return headersMap;
		}
		return null;

	}

	public void setHttpHeaders(String httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public String getIssueCollectionName() {
		return issueCollectionName;
	}

	public void setIssueCollectionName(String issueCollectionName) {
		this.issueCollectionName = issueCollectionName;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getIssueUrl() {
		return issueUrl;
	}

	public void setIssueUrl(String issueUrl) {
		this.issueUrl = issueUrl;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFixVersionId() {
		return fixVersionId;
	}

	public void setFixVersionId(String fixVersionId) {
		this.fixVersionId = fixVersionId;
	}

	public String getHttpHeaders() {
		return httpHeaders;
	}

	public QASetting getQaSetting() {
		return qaSetting;
	}

	public void setQaSetting(QASetting qaSetting) {
		this.qaSetting = qaSetting;
	}

	public IssueSetting getIssueSetting() {
		return issueSetting;
	}

	public void setIssueSetting(IssueSetting issueSetting) {
		this.issueSetting = issueSetting;
	}

	public TaskSetting getTaskSetting() {
		return taskSetting;
	}

	public void setTaskSetting(TaskSetting taskSetting) {
		this.taskSetting = taskSetting;
	}

	public BackLogSetting getBackLogSetting() {
		return backLogSetting;
	}

	public void setBackLogSetting(BackLogSetting backLogSetting) {
		this.backLogSetting = backLogSetting;
	}

}
