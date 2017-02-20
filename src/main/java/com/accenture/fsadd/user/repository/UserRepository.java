package com.accenture.fsadd.user.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.accenture.fsadd.core.repository.CrudBaseRepository;
import com.accenture.fsadd.user.entity.User;

@Repository
public interface UserRepository  extends CrudBaseRepository<User, BigInteger>{
	
	User findByEmail(String email);

	User getUserByNameAndPasswd(String userName, String password);
}
