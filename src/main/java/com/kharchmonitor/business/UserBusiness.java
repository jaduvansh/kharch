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
		if(null==userRepo.findByUserName(user.getUserName())) {
			userRepo.save(user);
		}else {
			System.out.println("Username already exist");
			return null;
		}
		return user;
	}
	public void delete(User user) {
		User existingUser = userRepo.findByUserName(user.getUserName());
		userRepo.delete(existingUser.get_id());
	}
	public void updatePassword(User user) {
		User existingUser = userRepo.findByUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		userRepo.save(existingUser);
	}
	public boolean isUserNameAvailable(String userName) {
		return (null == userRepo.findByUserName(userName));
	}
}
