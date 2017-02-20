package com.accenture.fsadd.dashboard.issues.business.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueSummaryEntity;

@Repository
public interface IssueSummaryRepository extends CrudBaseRepository<IssueSummaryEntity, BigInteger> {

}
