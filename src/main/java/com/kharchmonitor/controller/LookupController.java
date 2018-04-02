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

import com.kharchmonitor.business.LookupBusiness;
import com.kharchmonitor.persistence.entity.ExpenditureTypeLookup;

@RestController
@RequestMapping("/expenditureType")
public class LookupController {

	@Autowired
	private LookupBusiness lookupBusiness;
	
//	//TODO Delete before deploy
//	@CrossOrigin
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public List<ExpenditureTypeLookup> read() {
//		return lookupBusiness.findAll();
//	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<String> getLookupTypes() {
		return lookupBusiness.getLookupTypes();
	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExpenditureTypeLookup add(@RequestBody ExpenditureTypeLookup expenditureTypeLookup) {
		return lookupBusiness.add(expenditureTypeLookup);
	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.TEXT_PLAIN_VALUE)
	public void delete(@RequestBody String expenditureTypeLookup) {
		lookupBusiness.delete(expenditureTypeLookup.toUpperCase());
	}
	@CrossOrigin
	@RequestMapping(value="/update/{oldValue}/{newValue}", method = RequestMethod.PUT)
	public void update(@PathVariable String oldValue,@PathVariable String newValue) {
		lookupBusiness.update(oldValue,newValue);
	}
	
}
