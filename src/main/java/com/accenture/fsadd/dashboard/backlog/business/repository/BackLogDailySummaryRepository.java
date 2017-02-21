package com.accenture.fsadd.dashboard.backlog.business.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogDailySummaryEntity;

@Repository
public interface BackLogDailySummaryRepository extends CrudBaseRepository<BackLogDailySummaryEntity, LocalDateTime> {

	List<BackLogDailySummaryEntity> findByDayBetween(LocalDate from, LocalDate to);

}
