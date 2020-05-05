package com.capitalone.dashboard.model;

/*A generic object to serve as a data object to ComponentData*/
public class Series {

	private Object name;
	private Object value;

	public Series() {
		// TODO Auto-generated constructor stub
	}

	public Series(Object name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
