package com.useroperations.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.useroperations.pojo.StudentPOJO;

public class StudentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/LearnersAcademy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	private Connection jdbcConnection;
	private static final String insertStudentSQL = "insert into student(stuName,stuGender,stuDOB,stuAddress,stuClass) values(?,?,?,?,?)";
	private static final String deleteStudentSQL = "delete from student where id=?";
	private static final String updateStudentSQL = "update student set stuName=?, stuGender=?, stuDOB=?, stuAddress=?, stuClass=? where id=?";
	private static final String selectStudentSQL = "select id,stuName,stuGender,stuDOB,stuAddress,stuClass from student where id=?";
	private static final String listStudentsSQL = "select * from student";

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

	// insert record
	public boolean insertStudent(StudentPOJO pojo) throws SQLException {
		connect();
		// "insert into student(stuName,stuGender,stuDOB,stuAddress,stuClass)
		// values(?,?,?,?,?)";
		PreparedStatement statement = jdbcConnection.prepareStatement(insertStudentSQL);
		statement.setString(1, pojo.getName());
		statement.setString(2, pojo.getGender());
		statement.setDate(3, new java.sql.Date(pojo.getDob().getTime()));
		statement.setString(4, pojo.getAddress());
		statement.setString(5, pojo.getClassName());
		Boolean status = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return status;
	}

	// update record
	public boolean updateStudent(StudentPOJO pojo) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(updateStudentSQL);
		statement.setString(1, pojo.getName());
		statement.setString(2, pojo.getGender());
		statement.setDate(3, new java.sql.Date(pojo.getDob().getTime()));
		statement.setString(4, pojo.getAddress());
		statement.setString(5, pojo.getClassName());
		statement.setInt(6, pojo.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	// list record by id
	public StudentPOJO selectStudent(int id) throws SQLException {
		StudentPOJO pojo = null;
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(selectStudentSQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String stuName = resultSet.getString("stuName");
			String stuGender = resultSet.getString("stuGender");
			java.util.Date stuDOB = new java.util.Date();
			stuDOB = resultSet.getDate("stuDOB");
			String stuAddress = resultSet.getString("stuAddress");
			String stuClassName = resultSet.getString("stuClass");
			pojo = new StudentPOJO(id, stuName, stuGender, stuDOB, stuAddress, stuClassName);
		}
		resultSet.close();
		statement.close();
		disconnect();

		return pojo;
	}

	// list all record
	public List<StudentPOJO> listStudents() throws SQLException {
		List<StudentPOJO> listStudents = new ArrayList<>();
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(listStudentsSQL);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int stuID = resultSet.getInt("id");
			String stuName = resultSet.getString("stuName");
			String stuGender = resultSet.getString("stuGender");
			java.util.Date stuDOB = new java.util.Date();
			stuDOB = resultSet.getDate("stuDOB");
			String stuAddress = resultSet.getString("stuAddress");
			String stuClassName = resultSet.getString("stuClass");
			StudentPOJO pojo = new StudentPOJO(stuID, stuName, stuGender, stuDOB, stuAddress, stuClassName);
			listStudents.add(pojo);
		}
		resultSet.close();
		statement.close();
		disconnect();

		return listStudents;
	}

	// delete record
	public boolean deleteStudent(int id) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(deleteStudentSQL);
		statement.setInt(1, id);
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

}
