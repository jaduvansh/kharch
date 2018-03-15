package com.kharchmonitor.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.User;
import com.kharchmonitor.repository.UserRepository;
import com.kharchmonitor.translator.UserTranslator;
import com.kharchmonitor.view.UserView;

@Service
public class UserBusiness {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserTranslator userTranslator;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public UserView login(User user) {
		User existingUser = userRepo.findFirstByUserNameAndPassword(user.getUserName(), user.getPassword());
		return existingUser==null ? null : userTranslator.toView(existingUser);
	}
	
	public User create(User user){
		if(isUsernameExist(user.getUserName())) {
			System.out.println("Username already exist");
			return null;
		}
		return userRepo.save(user);
	}
	
	public void delete(String id) {
		userRepo.delete(id);
	}
	
	public void update(User user) {
		userRepo.save(user);
	}

	private boolean isUsernameExist(String user) {
		return null!=userRepo.findByUserName(user);
	}
}
