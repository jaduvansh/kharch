package com.kharchmonitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharchmonitor.business.ExpenditureBusiness;
import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.view.ExpenditureAddView;

@RestController
@RequestMapping(value = "/expenditure")
public class ExpenditureController {
	
	@Autowired
	ExpenditureBusiness expenditureBusiness;
	
	@CrossOrigin
	@RequestMapping(value="/{userName}",method = RequestMethod.GET)
	public List<Expenditure> getAllExpenditure(@PathVariable String userName){
		return expenditureBusiness.getAllExpenditure(userName);
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addExpenditure(@RequestBody ExpenditureAddView expenditureAddView){
		expenditureBusiness.addExpenditure(expenditureAddView);
	}
}
