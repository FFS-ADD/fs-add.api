package com.accenture.fsadd.web.setting.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.web.setting.controller.model.ProjectModel;

@Repository
public interface ProjectsRepository extends CrudBaseRepository<ProjectModel, BigInteger>{
    

}

