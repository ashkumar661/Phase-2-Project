package com.useroperations.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.useroperations.pojo.ClassPOJO;
import com.useroperations.pojo.StudentPOJO;


public class ClassDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/LearnersAcademy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	private Connection jdbcConnection;
	private static final String insertClassSQL = "insert into classes(className) values(?)";
	private static final String selectClassSQL = "select id, className from classes where id=?";
	private static final String updateClassSQL = "update classes set className=? where id=?";
	private static final String deleteClassSQL = "delete from classes where id=?";
	private static final String listClassSQL = "select * from classes";

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
	public boolean insertClass(ClassPOJO pojo) throws SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(insertClassSQL);
		statement.setString(1, pojo.getClassName());
		Boolean status = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return status;
	}
	
	// delete record
		public boolean deleteClass(int id) throws ClassNotFoundException, SQLException {
			connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(deleteClassSQL);
			statement.setInt(1, id);
			boolean rowDeleted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			return rowDeleted;
		}

	// update record
	public boolean updateClass(ClassPOJO pojo) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(updateClassSQL);
		statement.setString(1, pojo.getClassName());
		statement.setInt(2, pojo.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	// list record by id
	public ClassPOJO selectClass(int id) throws SQLException {
		ClassPOJO pojo = null;
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(selectClassSQL);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String teaName = resultSet.getString("className");
			pojo = new ClassPOJO(id, teaName);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return pojo;
	}

	// list all record
	public List<ClassPOJO> listClasses() throws SQLException {
		List<ClassPOJO> classList = new ArrayList<>();
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(listClassSQL);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int cID = resultSet.getInt("id");
			String cName = resultSet.getString("className");
			ClassPOJO pojo = new ClassPOJO(cID, cName);
			classList.add(pojo);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return classList;
	}
}
