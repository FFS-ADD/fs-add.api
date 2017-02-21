package com.accenture.fsadd.dashboard.overview.business.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.overview.business.entity.OverviewEntity;

@Repository
public interface OverviewRepository extends CrudBaseRepository<OverviewEntity, BigInteger> {
	


}
