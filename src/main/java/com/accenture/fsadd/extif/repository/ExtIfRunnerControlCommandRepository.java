package com.accenture.fsadd.extif.repository;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.extif.entity.ExtIfRunnerControlCommand;

@Repository
public interface ExtIfRunnerControlCommandRepository extends CrudBaseRepository<ExtIfRunnerControlCommand, String>{

}
