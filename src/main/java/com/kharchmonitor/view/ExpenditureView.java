package com.kharchmonitor.view;

import java.util.Date;

public class ExpenditureView {

	private String groupName;
	private String userName;
	private Date date;
	private String expenditureType;
	private float amount;
	private String comment;

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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getExpenditureType() {
		return expenditureType;
	}
	public void setExpenditureType(String expenditureType) {
		this.expenditureType = expenditureType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
