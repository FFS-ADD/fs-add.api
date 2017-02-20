package com.accenture.fsadd.dashboard.overview.business.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEventEntity;

@Repository
public interface OverviewEventRepository extends CrudBaseRepository<OverviewEventEntity, BigInteger> {
	
	List<OverviewEventEntity> findByEventDateBetween(LocalDate from, LocalDate to, Sort sort);

}
