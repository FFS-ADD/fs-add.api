package com.accenture.fsadd.dashboard.qa.business.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerSummaryEntity;

@Repository
public interface QueryAnswerSummaryRepository extends CrudBaseRepository<QueryAnswerSummaryEntity, BigInteger>{

}
