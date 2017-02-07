package com.accenture.fsadd.sonar.business.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.sonar.business.entity.SonarDashboardEntity;

@Repository
public interface SonarDashboardRepository extends CrudBaseRepository<SonarDashboardEntity, BigInteger> {
	@Query("find({projectKey:?0}).sort({createDate:-1}).limit(1)")
	public SonarDashboardEntity findByProjectKey(String projectKey);
}
