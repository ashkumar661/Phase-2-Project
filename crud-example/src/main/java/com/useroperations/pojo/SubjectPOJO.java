package com.useroperations.pojo;

public class SubjectPOJO {
	private int id;
	private String className;
	private String subName;
	
	public SubjectPOJO(String className, String subName) {
		super();
		this.className = className;
		this.subName = subName;
	}
	public SubjectPOJO(int id, String className, String subName) {
		super();
		this.id = id;
		this.className = className;
		this.subName = subName;
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
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	
	
}
