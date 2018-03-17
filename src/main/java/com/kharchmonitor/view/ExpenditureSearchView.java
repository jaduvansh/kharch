package com.kharchmonitor.view;

import java.util.Date;
import java.util.List;

import com.kharchmonitor.persistence.entity.ExpenditureType;

public class ExpenditureSearchView {

	private String userName;
	private Date date;
	private List<ExpenditureType> expenditureTypes;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
