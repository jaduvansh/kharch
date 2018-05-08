package com.kharchmonitor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kharchmonitor.persistence.entity.Group;

public interface GroupRepository extends MongoRepository<Group, String>{

}
