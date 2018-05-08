package com.kharchmonitor.persistence.entity;

import java.util.Date;
import java.util.List;

public class Group {

	private String _id;
	private String groupName;
	private List<User> users;
	private Date createdDate;
	private String createdBy;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<User> getUsers() {
		return users;
	}	
	public void setUsers(List<User> users) {
		this.users = users;
	}	
	public void addUser(User user) {
		this.users.add(user);
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	

}
