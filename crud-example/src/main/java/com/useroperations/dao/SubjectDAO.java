package com.useroperations.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.useroperations.pojo.SubjectPOJO;
import com.useroperations.pojo.StudentPOJO;


public class SubjectDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/LearnersAcademy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	private Connection jdbcConnection;
	private static final String insertSubjectSQL = "insert into subjects(className, subName) values(?,?)";
	private static final String selectSubjectSQL = "select id, className, subName from subjects where id=?";
	private static final String updateSubjectSQL = "update subjects set className=?, subName=? where id=?";
	private static final String deleteSubjectSQL = "delete from subjects where id=?";
	private static final String listSubjectSQL = "select * from subjects";

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
	public boolean insertSubject(SubjectPOJO pojo) throws SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(insertSubjectSQL);
		statement.setString(1, pojo.getClassName());
		statement.setString(2, pojo.getSubName());
		Boolean status = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return status;
	}

	// delete record
	public boolean deleteSubject(int id) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(deleteSubjectSQL);
		statement.setInt(1, id);
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	// update record
	public boolean updateSubject(SubjectPOJO pojo) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(updateSubjectSQL);
		statement.setString(1, pojo.getClassName());
		statement.setString(2, pojo.getSubName());
		statement.setInt(3, pojo.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	// list record by id
	public SubjectPOJO selectSubject(int id) throws SQLException {
		SubjectPOJO pojo = null;
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(selectSubjectSQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String cName = resultSet.getString("className");
			String subName = resultSet.getString("subName");
			pojo = new SubjectPOJO(id, cName, subName);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return pojo;
	}

	// list all record
	public List<SubjectPOJO> listSubjects() throws SQLException {
		List<SubjectPOJO> subjectList = new ArrayList<>();
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(listSubjectSQL);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int cID = resultSet.getInt("id");
			String cName = resultSet.getString("className");
			String subName = resultSet.getString("subName");
			SubjectPOJO pojo = new SubjectPOJO(cID, cName, subName);
			subjectList.add(pojo);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return subjectList;
	}
}
