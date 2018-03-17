package com.kharchmonitor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kharchmonitor.persistence.entity.ExpenditureTypeLookup;

public interface LookupRepository extends MongoRepository<ExpenditureTypeLookup, String> {

//	public void delete(String value);

	public ExpenditureTypeLookup findByValue(String value);
}
