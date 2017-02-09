package com.enoteshare;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonExample, BigInteger> {
	@Query(value="{'firstname':?0}")
	public PersonExample findOneByFirstname(String firstname,Sort sort);
	
	@Query(value="{'firstname':{'$gte':?0,'$lte':?1}}")
	public List<PersonExample> findWeelyByFirstname(String from,String to,Sort sort);
}

