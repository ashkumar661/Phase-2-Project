package com.useroperations.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher =null;
				
		try {
			connect();
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String statement = "select * from login where uname=? and password=?";
			PreparedStatement ps = jdbcConnection.prepareStatement(statement);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				dispatcher = request.getRequestDispatcher("dashboard.jsp");
				dispatcher.forward(request, response);
			}
			else {
				out.print("<center>");
				out.println("Invalid Credentials");
				out.println("<a href=login-form.jsp>Click here</a> to login");
				out.print("</center>");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void connect() throws SQLException {
		jdbcURL = getServletContext().getInitParameter("jdbcURL");
		jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
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

}
