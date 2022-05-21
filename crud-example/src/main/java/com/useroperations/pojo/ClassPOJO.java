package com.useroperations.pojo;

public class ClassPOJO {
private int id;
	private String className;
	
	
	
	public ClassPOJO(String className) {
		super();
		this.className = className;
	}
	public ClassPOJO(int id, String className) {
		super();
		this.id = id;
		this.className = className;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
