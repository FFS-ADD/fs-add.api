package com.accenture.fsadd.dashboard.qa.business.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerDailySummaryEntity;

@Repository
public interface QueryAnswerDailySummaryRepository extends CrudBaseRepository<QueryAnswerDailySummaryEntity, LocalDateTime>{

	List<QueryAnswerDailySummaryEntity> findByDayBetween(LocalDate from, LocalDate to);
	
}
