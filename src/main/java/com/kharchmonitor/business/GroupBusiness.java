package com.kharchmonitor.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Group;
import com.kharchmonitor.persistence.entity.User;
import com.kharchmonitor.repository.GroupRepository;
import com.kharchmonitor.repository.UserRepository;

@Service
public class GroupBusiness {
	
	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	private UserRepository userRepo;

	public Group create(Group group) {
		User user = userRepo.findByUserName(group.getCreatedBy());
		List<User> users = new ArrayList<>();
		users.add(user);
		group.setUsers(users);
		return groupRepo.save(group);
	}

	public List<Group> findAll() {
		return groupRepo.findAll();
	}

	public Group addUser(String groupId, String userName) {
		Group group = groupRepo.findOne(groupId);
		User user = userRepo.findByUserName(userName);
		group.addUser(user);
		return groupRepo.save(group);
	}

}
