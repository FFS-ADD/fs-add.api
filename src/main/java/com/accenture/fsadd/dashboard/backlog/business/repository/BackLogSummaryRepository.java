package com.accenture.fsadd.dashboard.backlog.business.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogSummaryEntity;

@Repository
public interface BackLogSummaryRepository extends CrudBaseRepository<BackLogSummaryEntity, BigInteger> {

}
