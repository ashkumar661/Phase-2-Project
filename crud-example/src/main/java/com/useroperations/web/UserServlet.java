package com.useroperations.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.useroperations.dao.ClassDAO;
import com.useroperations.dao.StudentDAO;
import com.useroperations.dao.SubjectDAO;
import com.useroperations.dao.TeacherDAO;
import com.useroperations.dao.TeacherSubjectDAO;
import com.useroperations.pojo.ClassPOJO;
import com.useroperations.pojo.SubjectPOJO;
import com.useroperations.pojo.TeacherPOJO;
import com.useroperations.pojo.TeacherStudentPOJO;
import com.useroperations.pojo.StudentPOJO;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	private ClassDAO classDAO;
	private SubjectDAO subjectDAO;
	private TeacherSubjectDAO teacherSubjectDAO;
	StudentPOJO studentPOJO;
	Date dobFinal;

	public UserServlet() {
		this.studentDAO = new StudentDAO();
		this.teacherDAO = new TeacherDAO();
		this.classDAO = new ClassDAO();
		this.subjectDAO = new SubjectDAO();
		this.teacherSubjectDAO = new TeacherSubjectDAO();
		studentPOJO= new StudentPOJO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {

			// Student-Record option -
			case "/newStudent":
				newStudent(request, response);
				break;
			case "/insertStudent":
				insertStudent(request, response);
				break;
			case "/deleteStudent":
				deleteStudent(request, response);
				break;
			case "/editStudent":
				editStudent(request, response);
				break;
			case "/updateStudent":
				updateStudent(request, response);
				break;
			case "/listStudent":
				listStudent(request, response);
				break;

			// Teacher-Record option -
			case "/insertTeacher":
				insertTeacher(request, response);
				break;
			case "/deleteTeacher":
				deleteTeacher(request, response);
				break;
			case "/editTeacher":
				editTeacher(request, response);
				break;
			case "/updateTeacher":
				updateTeacher(request, response);
				break;
			case "/listTeacher":
				listTeachers(request, response);
				break;
			case "/newTeacher":
				newTeacher(request, response);
				break;

			// Class-Record options -
			case "/insertClass":
				insertClass(request, response);
				break;
			case "/deleteClass":
				deleteClass(request, response);
				break;
			case "/editClass":
				editClass(request, response);
				break;
			case "/updateClass":
				updateClass(request, response);
				break;
			case "/listClass":
				listClass(request, response);
				break;
			case "/newClass":
				newClass(request, response);
				break;

			// Subject-Record options
			case "/insertSubject":
				insertSubject(request, response);
				break;
			case "/deleteSubject":
				deleteSubject(request, response);
				break;
			case "/editSubject":
				editSubject(request, response);
				break;
			case "/updateSubject":
				updateSubject(request, response);
				break;
			case "/listSubject":
				listSubject(request, response);
				break;
			case "/newSubject":
				newSubject(request, response);
				break;

			// Teacher-Subject Record options
			case "/insertTeacherSubject":
				insertTeacherSubject(request, response);
				break;
			case "/deleteTeacherSubject":
				deleteTeacherSubject(request, response);
				break;
			case "/listTeacherSubjects":
				listTeacherSubjects(request, response);
				break;
			case "/newTeacherSubject":
				newTeacherSubject(request, response);
				break;
			case "/classReport":
				classReport(request, response);
				break;

			default:
				listStudent(request, response);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
		List<StudentPOJO> listStudent = studentDAO.listStudents();
//		out.print(listStudent);
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void newStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dobFinal = sdf.parse(dob);
			studentPOJO.setDob(dobFinal);
			String address = request.getParameter("address");
			String className = request.getParameter("className");
			// out.print(name + " " + gender + " " + dob + " " + address + " " + className);
			StudentPOJO newStudent = new StudentPOJO(name, gender, dobFinal, address, className);
			Boolean status = studentDAO.insertStudent(newStudent);
			// out.println(status);
			response.sendRedirect("listStudent");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDAO.deleteStudent(id);

		response.sendRedirect("listStudent");
	}

	private void editStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StudentPOJO existingStudent = studentDAO.selectStudent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("userStudent", existingStudent);
		dispatcher.forward(request, response);

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dobFinal = sdf.parse(dob);
		studentPOJO.setDob(dobFinal);
		String address = request.getParameter("address");
		String className = request.getParameter("className");
		StudentPOJO studentPOJO = new StudentPOJO(id, name, gender, dobFinal, address, className);
		studentDAO.updateStudent(studentPOJO);
		response.sendRedirect("listStudent");
	}

	// Teacher Servlet operations

	private void newTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listTeachers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TeacherPOJO> listTeacher = teacherDAO.listTeachers();
		request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		teacherDAO.deleteTeacher(id);
		response.sendRedirect("listTeacher");
	}

	private void insertTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException,ParseException {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dobFinal = sdf.parse(dob);
		studentPOJO.setDob(dobFinal);
		String address = request.getParameter("address");
		TeacherPOJO newTeacher = new TeacherPOJO(name, gender, dobFinal, address);
		teacherDAO.insertTeacher(newTeacher);
		response.sendRedirect("listTeacher");
	}

	private void editTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TeacherPOJO existingTeacher = teacherDAO.selectTeacher(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		request.setAttribute("userTeacher", existingTeacher);
		dispatcher.forward(request, response);

	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException,ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dobFinal = sdf.parse(dob);
		studentPOJO.setDob(dobFinal);
		String address = request.getParameter("address");
		TeacherPOJO teacherPOJO = new TeacherPOJO(id, name, gender, dobFinal, address);
		teacherDAO.updateTeacher(teacherPOJO);
		response.sendRedirect("listTeacher");
	}

	// Class-Record Servlet control

	private void newClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ClassPOJO> listClasses = classDAO.listClasses();
		request.setAttribute("listClass", listClasses);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		classDAO.deleteClass(id);
		response.sendRedirect("listClass");
	}

	private void insertClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String cname = request.getParameter("name");
		ClassPOJO newClass = new ClassPOJO(cname);
		classDAO.insertClass(newClass);
		response.sendRedirect("listClass");
	}

	private void editClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ClassPOJO existingClass = classDAO.selectClass(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		request.setAttribute("userClass", existingClass);
		dispatcher.forward(request, response);

	}

	private void updateClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cName = request.getParameter("name");
		ClassPOJO classPOJO = new ClassPOJO(id, cName);
		classDAO.updateClass(classPOJO);
		response.sendRedirect("listClass");
	}

	// Subject-Record Servlet control

	private void newSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<SubjectPOJO> listSubjects = subjectDAO.listSubjects();
		request.setAttribute("listSubject", listSubjects);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		subjectDAO.deleteSubject(id);
		response.sendRedirect("listSubject");
	}

	private void insertSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String clName = request.getParameter("cName");
		String subName = request.getParameter("sName");
		SubjectPOJO newSubject = new SubjectPOJO(clName, subName);
		subjectDAO.insertSubject(newSubject);
		response.sendRedirect("listSubject");
	}

	private void editSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SubjectPOJO existingSubject = subjectDAO.selectSubject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		request.setAttribute("userSubject", existingSubject);
		dispatcher.forward(request, response);

	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String clName = request.getParameter("cName");
		String subName = request.getParameter("sName");
		SubjectPOJO subjectPOJO = new SubjectPOJO(id, clName, subName);
		subjectDAO.updateSubject(subjectPOJO);
		response.sendRedirect("listSubject");
	}

	// Teaceher-Subject Record Servlet control

	private void newTeacherSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-subject-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listTeacherSubjects(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TeacherStudentPOJO> list = teacherSubjectDAO.listTeacherSubjects();
		request.setAttribute("assignedTeacherList", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-subject-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteTeacherSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		teacherSubjectDAO.deleteTeacherSubject(id);
		response.sendRedirect("listTeacherSubjects");
	}

	private void insertTeacherSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
//					PrintWriter out = response.getWriter();
//					response.setContentType("text/html");
		String clName = request.getParameter("className");
		String subName = request.getParameter("selectSubject");
		String tName = request.getParameter("selectTeacher");
//					out.print(clName + " " + " " + subName+""+ tName);
		TeacherStudentPOJO newSubject = new TeacherStudentPOJO(clName, subName, tName);
		teacherSubjectDAO.insertTeacherSubject(newSubject);
		response.sendRedirect("listTeacherSubjects");
	}

	// Display students as per class

	private void classReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String cName = request.getParameter("id");
		request.setAttribute("listStudent", cName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-report.jsp");
		dispatcher.forward(request, response);
	}

}
