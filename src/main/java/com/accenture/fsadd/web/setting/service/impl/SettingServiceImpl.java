package com.accenture.fsadd.web.setting.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.web.setting.controller.model.ProjectModel;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;
import com.accenture.fsadd.web.setting.form.ProjectInfoForm;
import com.accenture.fsadd.web.setting.form.ThresholdProfileForm;
import com.accenture.fsadd.web.setting.repository.ProjectsRepository;
import com.accenture.fsadd.web.setting.repository.ThresholdsRepository;
import com.accenture.fsadd.web.setting.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private ProjectsRepository projectsRepository;

	@Autowired
	private ThresholdsRepository thresholdsRepository;

    @Override
    public List<ProjectModel> findAllValidProjectsInfo() {
        return projectsRepository.findAll();
    }

    @Override
    public ProjectModel createProject(ProjectInfoForm form) {
        ProjectModel project = new ProjectModel();
        BeanUtils.copyProperties(form, project);
    	return projectsRepository.insert(project);
    }

    @Override
    public ProjectModel updateProject(ProjectInfoForm form) {
        ProjectModel project = new ProjectModel();
        BeanUtils.copyProperties(form, project);
    	return projectsRepository.save(project);
    }

	@Override
	public List<ThresholdModel> findAllValidThresholdsInfo() {
		return thresholdsRepository.findAll();
	}

	@Override
	public ThresholdModel createThreshold(ThresholdProfileForm form) {
		int size = thresholdsRepository.findAll().size();
		form.setId(String.valueOf(size+1));
	    ThresholdModel threshold = new ThresholdModel();
        BeanUtils.copyProperties(form, threshold);
        return thresholdsRepository.insert(threshold);
	}

	@Override
	public ThresholdModel updateThreshold(ThresholdProfileForm form) {
	    ThresholdModel threshold = new ThresholdModel();
        BeanUtils.copyProperties(form, threshold);
        return thresholdsRepository.save(threshold);
	}

	@Override
	public void deleteThreshold(ThresholdProfileForm form) {
	    ThresholdModel threshold = new ThresholdModel();
        BeanUtils.copyProperties(form, threshold);
        thresholdsRepository.delete(threshold.getId());
	}
}
