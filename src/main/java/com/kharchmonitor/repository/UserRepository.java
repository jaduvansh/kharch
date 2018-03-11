package com.kharchmonitor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kharchmonitor.persistence.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findFirstByUserNameAndPassword(String userName, String password);

}
