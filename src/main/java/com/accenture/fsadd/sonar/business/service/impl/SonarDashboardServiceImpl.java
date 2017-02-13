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

		String toDate = FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now(),
				FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS);
		String fromDate = FsaddUtil.convertLocaldateTimeToString(
				LocalDateTime.now().minusDays(6).truncatedTo(ChronoUnit.DAYS),
				FsaddConstant.DATAE_FORMAT_YYYYMMDDHHMMSS);
		List<Sonardashboard> hist = sonarDashboardRepository.findByprojectKeyAndCreateDateRange(projectKey, fromDate,
				toDate, new Sort(Direction.DESC, "createDate"));
		List<Sonardashboard> resultList = new ArrayList<Sonardashboard>();
		for(int i=0;i<7;i++){
			String compareDate = FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now().minusDays(i),
					FsaddConstant.DATAE_FORMAT_YYYYMMDD);
			for(Sonardashboard sonardashboard:hist){
				if(sonardashboard.getCreateDate().startsWith(compareDate)){
					resultList.add(sonardashboard);
					break;
				}
			}
			if(resultList.size() < i+1){
				resultList.add(new Sonardashboard());
			}
		}
		return hist;
	}
}
