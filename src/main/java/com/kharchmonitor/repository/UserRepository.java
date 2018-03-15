package com.kharchmonitor.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kharchmonitor.persistence.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findFirstByUserNameAndPassword(String userName, String password);
	
	public List<User> findAll();
	
	User findByUserName(String userName);
}
