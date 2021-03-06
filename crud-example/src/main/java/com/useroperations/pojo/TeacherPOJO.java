package com.useroperations.pojo;

import java.util.Date;

public class TeacherPOJO {
	private int id;
	private String name;
	private String gender;
	private Date dob;
	private String address;
	
	
	
	public TeacherPOJO(int id, String name, String gender, Date dob, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
	}
	public TeacherPOJO(String name, String gender, Date dob, String address) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
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
	
}
