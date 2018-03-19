package com.kharchmonitor.translator;

import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.User;
import com.kharchmonitor.view.UserView;

@Service
public class UserTranslator {

	public UserView toView(User user) {
		
		UserView userView = new UserView();
		userView.setEmail(user.getEmail());
		userView.setFirstName(user.getFirstName());
		userView.setLastName(user.getLastName());
		userView.setUserName(user.getUserName());
		return userView;

	}
}
