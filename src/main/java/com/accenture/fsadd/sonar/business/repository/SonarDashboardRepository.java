package com.accenture.fsadd.sonar.business.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.sonar.business.entity.Sonardashboard;

@Repository
public interface SonarDashboardRepository extends CrudBaseRepository<Sonardashboard, BigInteger> {
	//@Query("find({'projectKey':?0}).sort({'createDate':-1}).limit(1)")
	@Query(value="{'projectKey':?0}")
	public Sonardashboard findOneByProjectKey(String projectKey,Sort sort);
	
	//@Query(value="{'createDate':{'$gt' : fromDate, '$lt' : toDate}}")
	//public List<Sonardashboard> findBycreateDateBetween(String projectKey,String fromDate,String toDate,Sort sort);
}
