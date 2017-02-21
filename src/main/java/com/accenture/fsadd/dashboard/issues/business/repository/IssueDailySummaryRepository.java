package com.accenture.fsadd.dashboard.issues.business.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueDailySummaryEntity;

@Repository
public interface IssueDailySummaryRepository extends CrudBaseRepository<IssueDailySummaryEntity, LocalDateTime>{

	List<IssueDailySummaryEntity> findByDayBetween(LocalDate from, LocalDate to);
	
}
