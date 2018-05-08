package com.kharchmonitor.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kharchmonitor.persistence.entity.Expenditure;

public interface ExpenditureRepository extends MongoRepository<Expenditure, String> {

	public Expenditure findByGroupNameAndDate(String groupName, Date date);

	public List<Expenditure> findByGroupNameOrderByDate(String groupName);
	
	@Query("{$and:[{groupName:{$eq:?0}},{$expr:{$and:[{$eq:[{$year:'$date'}, ?1]}, {$eq:[{$month:'$date'}, ?2]}]}}]}")
	public List<Expenditure> findByGroupnameMonthAndYear(String groupName, int year, int month,Sort orderBydate);
	
	@Query("{$and:[{groupName:{$eq:?0}},{date:{$gte: ?1,$lt: ?2}}]}")
	public List<Expenditure> findByGroupnameDateRange(String groupName, Date fromdate, Date toDate,Sort orderBydate);
}

