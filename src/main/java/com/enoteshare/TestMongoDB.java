package com.enoteshare;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface TestMongoDB extends CrudRepository<UserTest, String> {

}
