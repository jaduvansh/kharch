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
		return userTranslator.toView(userRepo.findFirstByUserNameAndPassword(user.getUserName(), user.getPassword()));
	}
	public User create(User user){
		if(null==userRepo.findByUserName(user.getUserName())) {
			userRepo.save(user);
		}else {
			System.out.println("Username already exist");
			return null;
		}
		return user;
	}
	public void delete(String id) {
		userRepo.delete(id);
	}
	public void update(User user) {
		userRepo.save(user);
	}
}
