package com.accenture.fsadd.dashboard.qa.business.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.qa.business.entity.QueryAnswerEntity;

@Repository
public interface QueryAnswerRepository extends CrudBaseRepository<QueryAnswerEntity, BigInteger> {
	
	//@Query(value="{'status':{'$not':{'$in':['CLOSED']}}}, 'expectedAnswerOn':{'$lt':?0}}")
	@Query(value="{'expectedAnswerOn':{'$lt':?0}, 'status':{'$not':{'$in':['CLOSED']}}}")
	List<QueryAnswerEntity> findByGreaterThanExpectedAnswerOn(LocalDate expectedAnswerOn);

}
