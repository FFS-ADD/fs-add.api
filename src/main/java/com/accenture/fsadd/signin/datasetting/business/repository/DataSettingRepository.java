package com.accenture.fsadd.signin.datasetting.business.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.signin.datasetting.business.entity.DataSettingEntity;

@Repository
public interface DataSettingRepository extends CrudBaseRepository<DataSettingEntity, BigInteger> {
		
	@Query(value="{'email':?0}")
	DataSettingEntity findSettingData(String email);
	
}
