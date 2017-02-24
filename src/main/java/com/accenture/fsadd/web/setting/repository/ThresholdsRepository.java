package com.accenture.fsadd.web.setting.repository;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.web.setting.controller.model.ThresholdModel;

@Repository
public interface ThresholdsRepository extends CrudBaseRepository<ThresholdModel, String>{
    

}

