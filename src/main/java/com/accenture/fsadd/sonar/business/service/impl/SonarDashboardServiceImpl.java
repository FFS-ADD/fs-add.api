package com.accenture.fsadd.sonar.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.sonar.business.entity.SonarDashboardEntity;
import com.accenture.fsadd.sonar.business.repository.SonarDashboardRepository;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;

@Service
public class SonarDashboardServiceImpl implements SonarDashboardService {

	@Autowired
	SonarDashboardRepository sonarDashboardRepository;
	
	@Override
	public SonarDashboardEntity getSonarDashboard(String projectKey) {
		SonarDashboardEntity entity = sonarDashboardRepository.findByProjectKey(projectKey);
		return entity;
	}

}
