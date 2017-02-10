package com.accenture.fsadd.sonar.business.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.common.FsaddUtil;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.repository.SonarDashboardRepository;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;

@Service
public class SonarDashboardServiceImpl implements SonarDashboardService {

	@Autowired
	SonarDashboardRepository sonarDashboardRepository;

	@Override
	public Sonardashboard getSonarDashboard(String projectKey) {
		Sonardashboard entity = sonarDashboardRepository.findOneByProjectKey(projectKey,
				new Sort(Direction.DESC, "createDate"));
		return entity;
	}

	@Override
	public List<Sonardashboard> getSonarDashboardHist(String projectKey) {

		String fromDate = FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS),
				FsaddConstant.DATE_FORMAT_YY_MM_DD_HH_MM_SS);
		String toDate = FsaddUtil.convertLocaldateTimeToString(
				LocalDateTime.now().minusDays(7).truncatedTo(ChronoUnit.HOURS),
				FsaddConstant.DATE_FORMAT_YY_MM_DD_HH_MM_SS);
		List<Sonardashboard> hist = sonarDashboardRepository.findByprojectKeyAndCreateDateRange(projectKey, fromDate,
				toDate, new Sort(Direction.DESC, "createDate"));
//		List<Sonardashboard> hist = sonarDashboardRepository.findByprojectKeyAndCreateDateRange(projectKey, "2017/2/2",
//				"2017/2/9", new Sort(Direction.DESC, "createDate"));
		return hist;
	}
}
