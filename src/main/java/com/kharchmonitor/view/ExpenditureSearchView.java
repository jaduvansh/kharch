package com.kharchmonitor.view;

import java.util.List;

import com.kharchmonitor.persistence.entity.ExpenditureType;

public class ExpenditureSearchView {

	private String groupName;
	private String date;
	private List<ExpenditureType> expenditureTypes;
	
	public List<ExpenditureType> getExpenditureTypes() {
		return expenditureTypes;
	}
	public void setExpenditureTypes(List<ExpenditureType> expenditureTypes) {
		this.expenditureTypes = expenditureTypes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
