package com.useroperations.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.useroperations.pojo.TeacherStudentPOJO;

public class TeacherSubjectDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/LearnersAcademy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	private Connection jdbcConnection;
	private static final String insertTeacherSQL = "insert into sub_teacher(className, subName, tName) values(?,?,?)";
	private static final String deleteTeacherSQL = "delete from sub_teacher where id=?";
	private static final String listTeachersSQL = "select * from sub_teacher";

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	// Insert record
	public boolean insertTeacherSubject(TeacherStudentPOJO pojo) throws SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(insertTeacherSQL);
		statement.setString(1, pojo.getcName());
		statement.setString(2, pojo.getsName());
		statement.setString(3, pojo.gettName());
		Boolean status = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return status;
	}
	
	// delete record
		public boolean deleteTeacherSubject(int id) throws ClassNotFoundException, SQLException {
			connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(deleteTeacherSQL);
			statement.setInt(1, id);
			boolean rowDeleted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			return rowDeleted;
		}

	// list all record
	public List<TeacherStudentPOJO> listTeacherSubjects() throws SQLException {
		List<TeacherStudentPOJO> teacherList = new ArrayList<>();
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(listTeachersSQL);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String cName = resultSet.getString("className");
			String sName = resultSet.getString("subName");
			String tName = resultSet.getString("tName");
			TeacherStudentPOJO pojo = new TeacherStudentPOJO(id, cName, sName,tName);
			teacherList.add(pojo);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return teacherList;
	}
}
