package com.useroperations.pojo;

public class TeacherStudentPOJO {
	private int id;
	private String cName;
	private String sName;
	private String tName;

	public TeacherStudentPOJO(String cName, String sName, String tName) {
		super();
		this.cName = cName;
		this.sName = sName;
		this.tName = tName;
	}

	public TeacherStudentPOJO(int id, String cName, String sName, String tName) {
		super();
		this.id = id;
		this.cName = cName;
		this.sName = sName;
		this.tName = tName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

}
