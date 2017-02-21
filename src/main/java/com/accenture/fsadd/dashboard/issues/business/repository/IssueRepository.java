package com.accenture.fsadd.dashboard.issues.business.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity;
import com.accenture.fsadd.dashboard.issues.business.entity.IssueEntity.Status;

@Repository
public interface IssueRepository extends CrudBaseRepository<IssueEntity, BigInteger> {
	
	@Query(value="{'status':{'$not':{'$in':['?0']}}}")
	List<IssueEntity> findByNotInStatus(Status status);

}
