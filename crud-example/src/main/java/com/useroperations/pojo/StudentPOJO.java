package com.useroperations.pojo;

import java.util.Date;

public class StudentPOJO {
	private int id;
	private String name;
	private String gender;
	private Date dob;
	private String address;
	private String className;
	
	
	public StudentPOJO(String name, String gender, Date dob, String address, String className) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.className = className;
	}
	public StudentPOJO(int id, String name, String gender, Date dob, String address, String className) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.className = className;
	}
	public StudentPOJO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
