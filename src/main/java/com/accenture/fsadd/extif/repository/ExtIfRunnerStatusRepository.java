package com.accenture.fsadd.extif.repository;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.extif.entity.ExtIfRunnerStatus;

@Repository
public interface ExtIfRunnerStatusRepository extends CrudBaseRepository<ExtIfRunnerStatus, String> {

}
