package com.accenture.fsadd.web.setting.service;

import java.util.List;

import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;
import com.accenture.fsadd.web.setting.form.ProjectInfoForm;
import com.accenture.fsadd.web.setting.form.ThresholdProfileForm;

public interface SettingService {
	
	List<ProjectModel> findAllValidProjectsInfo();

	ProjectModel createProject(ProjectInfoForm form);
	
	ProjectModel updateProject(ProjectInfoForm form);

	List<ThresholdModel> findAllValidThresholdsInfo();

	ThresholdModel createThreshold(ThresholdProfileForm form);
	
	ThresholdModel updateThreshold(ThresholdProfileForm form);

	void deleteThreshold(ThresholdProfileForm form);
}
