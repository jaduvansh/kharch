package com.kharchmonitor.persistence.entity;

public class ExpenditureTypeLookup {

	private String _id;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = (value==null?null:value.toUpperCase());
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
