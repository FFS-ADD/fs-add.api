package com.accenture.fsadd.dashboard.codequality.business.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.common.FsaddConstant;
import com.accenture.fsadd.common.FsaddUtil;
import com.accenture.fsadd.dashboard.codequality.business.entity.Sonardashboard;
import com.accenture.fsadd.dashboard.codequality.business.repository.SonarDashboardRepository;
import com.accenture.fsadd.dashboard.codequality.business.service.SonarDashboardService;
import com.accenture.fsadd.extif.entity.SonarQubeSetting;
import com.accenture.fsadd.extif.service.ExtIfService;

@Service
public class SonarDashboardServiceImpl implements SonarDashboardService {

	@Autowired
	private ExtIfService extIfService;
	
	@Autowired
	SonarDashboardRepository sonarDashboardRepository;

	@Override
	public Sonardashboard getSonarDashboard() {
		SonarQubeSetting sonarQubeSetting = extIfService.getExtIfSetting().getSonarQubeSetting();
		Sonardashboard entity = sonarDashboardRepository.findOneByProjectKey(sonarQubeSetting.getProjectKey(),
				new Sort(Direction.DESC, "createDate"));
		return entity;
	}

	@Override
	public List<Sonardashboard> getSonarDashboardHist() {
		SonarQubeSetting sonarQubeSetting = extIfService.getExtIfSetting().getSonarQubeSetting();
		String toDate = FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now(),
				FsaddConstant.DATAE_FORMAT_YYYYMMDD);
		String fromDate = FsaddUtil.convertLocaldateTimeToString(
				LocalDateTime.now().minusDays(6).truncatedTo(ChronoUnit.DAYS),
				FsaddConstant.DATAE_FORMAT_YYYYMMDD);
		List<Sonardashboard> hist = sonarDashboardRepository.findByprojectKeyAndCreateDateRange(sonarQubeSetting.getProjectKey(), fromDate,
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
				Sonardashboard empty = new Sonardashboard(); 
				empty.setProjectKey(sonarQubeSetting.getProjectKey());
				empty.setCreateDate(FsaddUtil.convertLocaldateTimeToString(LocalDateTime.now().minusDays(i),
					FsaddConstant.DATAE_FORMAT_YYYYMMDD));
				resultList.add(empty);
			}
		}
		return resultList;
	}
}
