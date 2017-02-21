package com.accenture.fsadd.dashboard.task.business.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.task.business.entity.TaskSummaryEntity;

@Repository
public interface TaskSummaryRepository extends CrudBaseRepository<TaskSummaryEntity, BigInteger> {

}
