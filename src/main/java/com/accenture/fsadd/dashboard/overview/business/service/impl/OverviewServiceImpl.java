package com.accenture.fsadd.dashboard.overview.business.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewEventRepository;
import com.accenture.fsadd.dashboard.overview.business.repository.OverviewRepository;
import com.accenture.fsadd.dashboard.overview.business.service.OverviewService;

@Service
public class OverviewServiceImpl implements OverviewService {

	@Autowired
	private OverviewEventRepository overviewEventRepository;

	@Autowired
	private OverviewRepository overviewRepository;

	@Override
	public OverviewEntity getOverview() {
		return overviewRepository.findOne(BigInteger.ONE);
	}

	@Override
	public List<OverviewEventEntity> getAllEvents() {
		List<OverviewEventEntity> result = overviewEventRepository.findAll(new Sort("eventDate"));
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}

	@Override
	public List<OverviewEventEntity> getEventsBetween(LocalDate fromDay, LocalDate toDay) {
		List<OverviewEventEntity> result = overviewEventRepository.findByEventDateBetween(fromDay, toDay,
				new Sort("eventDate"));
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}

	@Override
	public void aggregateService(LocalDate executeDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertEvent(OverviewEventEntity overviewEventEntity) {
		overviewEventRepository.insert(overviewEventEntity);

	}

	@Override
	public void insertEvent(List<OverviewEventEntity> overviewEventEntityList) {
		overviewEventEntityList.forEach((entity) -> this.insertEvent(entity));

	}

}
