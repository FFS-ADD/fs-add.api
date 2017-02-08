package com.accenture.fsadd.sonar.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.repository.SonarDashboardRepository;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;

@Service
public class SonarDashboardServiceImpl implements SonarDashboardService {

	@Autowired
	SonarDashboardRepository sonarDashboardRepository;
	
	@Override
	public Sonardashboard getSonarDashboard(String projectKey) {
		Sonardashboard entity = sonarDashboardRepository.findOneByProjectKey(projectKey,new Sort(Direction.DESC,"createDate"));
		return entity;
	}

}
