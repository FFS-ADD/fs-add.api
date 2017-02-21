package com.accenture.fsadd.dashboard.backlog.business.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity;
import com.accenture.fsadd.dashboard.backlog.business.entity.BackLogEntity.Status;


@Repository
public interface BackLogRepository extends CrudBaseRepository<BackLogEntity, BigInteger> {
	
	@Query(value="{'status':{'$not':{'$in':?0}}}")
	List<BackLogEntity> findByNotInStatus(Status[] status);

}
