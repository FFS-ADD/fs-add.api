package com.accenture.fsadd.dashboard.task.business.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.dashboard.task.business.entity.TaskEntity;

@Repository
public interface TaskRepository extends CrudBaseRepository<TaskEntity, BigInteger> {
	
	@Query(value="{'planedEndDate':{'$lt':?0}, 'status':{'$not':{'$in':['CLOSED','PENDING']}}}")
	List<TaskEntity> findAllDelayTasks(LocalDate planedEndDate);
	
	List<TaskEntity> findByBackLogId(BigInteger backLogId);
	
	

}
