package com.kharchmonitor.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kharchmonitor.persistence.entity.Expenditure;

public interface ExpenditureRepository extends MongoRepository<Expenditure, String> {

	public Expenditure findByUserNameAndDate(String userName, String date);

	public List<Expenditure> findByUserName(String userName);
}

