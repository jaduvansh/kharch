package com.kharchmonitor.persistence.entity;

import java.util.Date;
import java.util.List;

public class Expenditure {
	
	private String _id;
	
	private String groupName;
	private Date date;
	private List<ExpenditureType> expenditureTypes;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<ExpenditureType> getExpenditureTypes() {
		return expenditureTypes;
	}
	public void setExpenditureTypes(List<ExpenditureType> expenditureTypes) {
		this.expenditureTypes = expenditureTypes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String userName) {
		this.groupName = userName;
	}

}
