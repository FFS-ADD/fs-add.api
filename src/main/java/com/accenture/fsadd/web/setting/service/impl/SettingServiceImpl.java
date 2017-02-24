package com.accenture.fsadd.web.setting.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.repository.SonarDashboardRepository;
import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;
import com.accenture.fsadd.web.setting.form.ProjectInfoForm;
import com.accenture.fsadd.web.setting.form.ThresholdProfileForm;
import com.accenture.fsadd.web.setting.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SonarDashboardRepository sonarDashboardRepository;

    @Override
    public List<ProjectModel> findAllValidProjectsInfo() {
    	List<ProjectModel> result = new ArrayList<>();
    	List<Sonardashboard> allSonardashboard = sonarDashboardRepository.findAll();
    	if (!allSonardashboard.isEmpty()) {
    		allSonardashboard.forEach(aonardashboard -> {
    			ProjectModel model = new ProjectModel();
    			model.setId(new BigInteger(aonardashboard.getId()));
    			model.setProjectName(aonardashboard.getTests());
    			// TODO
    			result.add(model);
    		});
    	}
        return result;
    }

    @Override
    public ProjectModel createProject(ProjectInfoForm form) {
    	// TODO
    	return null;
    }

    @Override
    public ProjectModel updateProject(ProjectInfoForm form) {
    	// TODO
    	return null;
    }

	@Override
	public List<ThresholdModel> findAllValidThresholdsInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThresholdModel createThreshold(ThresholdProfileForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThresholdModel updateThreshold(ThresholdProfileForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteThreshold(ThresholdProfileForm form) {
		
	}
}
