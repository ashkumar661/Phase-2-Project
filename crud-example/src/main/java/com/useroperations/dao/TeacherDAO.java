package com.useroperations.dao;
import com.useroperations.pojo.TeacherPOJO;
import com.useroperations.pojo.StudentPOJO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeacherDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/LearnersAcademy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	private Connection jdbcConnection;
	private static final String insertTeacherSQL = "insert into teacher(tName, tGender, tDOB, tAddress) values(?,?,?,?)";
	private static final String selectTeacherSQL = "select id, tName, tGender, tDOB, tAddress from teacher where id=?";
	private static final String updateTeacherSQL = "update teacher set tName=?, tGender=?, tDOB=?, tAddress=? where id=?";
	private static final String deleteTeacherSQL = "delete from teacher where id=?";
	private static final String listTeachersSQL = "select * from teacher";

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
	public boolean insertTeacher(TeacherPOJO pojo) throws SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(insertTeacherSQL);
		statement.setString(1, pojo.getName());
		statement.setString(2, pojo.getGender());
		statement.setDate(3, new java.sql.Date(pojo.getDob().getTime()));
		statement.setString(4, pojo.getAddress());
		
		Boolean status = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return status;
	}
	
	// delete record
		public boolean deleteTeacher(int id) throws ClassNotFoundException, SQLException {
			connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(deleteTeacherSQL);
			statement.setInt(1, id);
			boolean rowDeleted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			return rowDeleted;
		}

	// update record
	public boolean updateTeacher(TeacherPOJO pojo) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(updateTeacherSQL);
		statement.setString(1, pojo.getName());
		statement.setString(2, pojo.getGender());
		statement.setDate(3, new java.sql.Date(pojo.getDob().getTime()));
		statement.setString(4, pojo.getAddress());
		statement.setInt(5, pojo.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	// list record by id
	public TeacherPOJO selectTeacher(int id) throws SQLException {
		TeacherPOJO pojo = null;
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(selectTeacherSQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String teaName = resultSet.getString("tName");
			String teaGender = resultSet.getString("tGender");
			java.util.Date tDOB = new java.util.Date();
			tDOB = resultSet.getDate("tDOB");
			String teaAddress = resultSet.getString("tAddress");			
			pojo = new TeacherPOJO(id, teaName, teaGender, tDOB, teaAddress);
		}
		resultSet.close();
		statement.close();
		disconnect();

		return pojo;
	}

	// list all record
	public List<TeacherPOJO> listTeachers() throws SQLException {
		List<TeacherPOJO> teacherList = new ArrayList<>();
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(listTeachersSQL);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int teaID = resultSet.getInt("id");
			String teaName = resultSet.getString("tName");
			String teaGender = resultSet.getString("tGender");
			java.util.Date tDOB = new java.util.Date();
			tDOB = resultSet.getDate("tDOB");
			String teaAddress = resultSet.getString("tAddress");
			TeacherPOJO pojo = new TeacherPOJO(teaID, teaName, teaGender, tDOB, teaAddress);
			teacherList.add(pojo);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return teacherList;
	}
}
