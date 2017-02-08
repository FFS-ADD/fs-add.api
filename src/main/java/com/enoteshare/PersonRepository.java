package com.enoteshare;

import java.math.BigInteger;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonExample, BigInteger> {
	@Query(value="{'firstname':?0}")
	public PersonExample findOneByFirstname(String firstname,Sort sort);
}

