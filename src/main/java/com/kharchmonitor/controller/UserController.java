package com.kharchmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kharchmonitor.business.UserBusiness;
import com.kharchmonitor.persistence.entity.User;
import com.kharchmonitor.view.UserView;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserBusiness userBusiness;

	//TODO: to be deleted before deploy
//	@CrossOrigin
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public List<User> read() {
//		return userBusiness.findAll();
//	}
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserView login(@RequestBody User user) {
		return userBusiness.login(user);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User create(@RequestBody User user) {
		return userBusiness.create(user);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/validate/userName/{userName}", method = RequestMethod.GET)
	public boolean isUserNameAvailable(@PathVariable String userName){
		return userBusiness.isUserNameAvailable(userName);
	}
	
//	@CrossOrigin
//	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void update(@RequestBody User user){
//		userBusiness.updatePassword(user);
//	}

//	@CrossOrigin
//	@RequestMapping(method = RequestMethod.DELETE)
//	public void delete(@RequestBody User user) {
//		userBusiness.delete(user);
//	}

}
