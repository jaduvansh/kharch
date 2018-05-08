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

import com.kharchmonitor.business.GroupBusiness;
import com.kharchmonitor.persistence.entity.Group;
import com.kharchmonitor.view.UserView;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupBusiness groupBusiness;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Group create(@RequestBody Group group) {
		return groupBusiness.create(group);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<Group> getAll() {
		return groupBusiness.findAll();
	}
	
	@RequestMapping(value="/{groupId}", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Group addUser(@PathVariable String groupId, @RequestBody UserView user) {
		return groupBusiness.addUser(groupId, user.getUserName());
	}

}
