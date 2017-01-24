package com.accenture.fsadd.core.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudBaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID>{

}
