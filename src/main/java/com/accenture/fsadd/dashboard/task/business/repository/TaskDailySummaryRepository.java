package com.accenture.fsadd.dashboard.task.business.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.task.business.entity.TaskDailySummaryEntity;

@Repository
public interface TaskDailySummaryRepository extends CrudBaseRepository<TaskDailySummaryEntity, LocalDateTime>{

	List<TaskDailySummaryEntity> findByDayBetween(LocalDate from, LocalDate to);
	
}
