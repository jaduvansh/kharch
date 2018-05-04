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
import com.kharchmonitor.translator.ExpenditureTranslator;
import com.kharchmonitor.view.ExpenditureSearchView;
import com.kharchmonitor.view.ExpenditureView;

@RestController
@RequestMapping(value = "/expenditure")
public class ExpenditureController {
	
	@Autowired
	ExpenditureTranslator translator;
	
	@Autowired
	ExpenditureBusiness expenditureBusiness;
	
	@CrossOrigin
	@RequestMapping(value="/{userName}",method = RequestMethod.GET)
	public List<ExpenditureSearchView> getAllExpenditureByUserName(@PathVariable String userName){
		return translator.toView(expenditureBusiness.getAllExpenditure(userName));
	}
	
	@CrossOrigin
	@RequestMapping(value="/{userName}/{monthYear}",method = RequestMethod.GET)
	public List<ExpenditureSearchView> getAllExpenditureByUserNameAndMonth(@PathVariable String userName, @PathVariable String monthYear){
		//return translator.toView(expenditureBusiness.getAllExpenditureByMonthYear(userName, monthYear));
		return translator.toView(expenditureBusiness.findByUsernameDateRange(userName, monthYear));
	}
	
	@CrossOrigin
	@RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addExpenditure(@RequestBody ExpenditureView expenditureView){
		expenditureBusiness.addExpenditure(expenditureView);
	}
}
