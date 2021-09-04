package com.simplilearn.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.db.utils.DbManager;

/**
 * Servlet implementation class Teacher
 */
@WebServlet("/Teacher")
public class Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Class.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		//int studentID = Integer.parseInt(request.getParameter("studentID"));
		String teacherName = request.getParameter("teacherName");
		int teacherPhone = Integer.parseInt(request.getParameter("teacherPhone"));
		String teacherAddress = request.getParameter("teacherAddress");
		String className = request.getParameter("className");
		String JoiningDate = request.getParameter("JoiningDate");
		
		try {
			
			String userName = "root";
			String password = "Awesome@2021";
			String jdbcUrl = "jdbc:mysql://localhost:3306/learnersacademy";
			
			DbManager dbManager = new DbManager(userName, password, jdbcUrl);
			
			Connection conn = dbManager.getConnection();

			String query = "INSERT INTO teacher values(?, ?, ?, ?, ?);";
			PreparedStatement pstm = conn.prepareStatement(query);

			//pstm.setInt(1, studentID);
			pstm.setString(1, teacherName);
			pstm.setInt(2,teacherPhone);
			pstm.setString(3, teacherAddress);
			pstm.setString(4,className);
			pstm.setString(5,JoiningDate);
			

			int noOfRowsAffected = pstm.executeUpdate();

			if (noOfRowsAffected >= 0) {out.println("<html><body bgcolo=#117864>");
    		out.println("<center> <span style='color:black'><h2>Details Saved Sucessfully</h2></span></center><br><br>");
    		out.println("<center><a href=\"Teacher.html\">Add Details of Teacher</a></center><br><br>");
    		out.println("<center><a href=\"View.html\">View Details of Teacher</a></center><br><br>");
    		out.println("<center><a href=\"DeleteTeacher.html\">Delete Details Teacher</a><br><br></center>");
    		out.println("<center><a href=\"DashBoard.html\">Dashboard</a><br><br></center>");
    		out.println("</html></body>");
    		
			} else {
				out.println("<h1 style='color:red'>failed !</h1>");
			}
			
			if(conn != null) {
				pstm.close();
				conn.close();
			}
			

		} catch (Exception e) {
			out.println("<h1 style='color:red'> Product creation failed !</h1>");
			out.println(e);
		}

	}

}