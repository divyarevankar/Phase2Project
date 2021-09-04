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
 * Servlet implementation class InsertDB
 */
@WebServlet("/insert-db")
public class InsertDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String studentName = request.getParameter("studentName");
		int stPhone = Integer.parseInt(request.getParameter("stPhone"));
		String stAddress = request.getParameter("stAddress");
		String courseName = request.getParameter("courseName");
		String JoiningDate = request.getParameter("JoiningDate");
		
		try {
			
			String userName = "root";
			String password = "Awesome@2021";
			String jdbcUrl = "jdbc:mysql://localhost:3306/learnersacademy";
			
			DbManager dbManager = new DbManager(userName, password, jdbcUrl);
			
			Connection conn = dbManager.getConnection();

			String query = "INSERT INTO student values(?, ?, ?, ?, ?, ?);";
			PreparedStatement pstm = conn.prepareStatement(query);

			pstm.setInt(1, studentID);
			pstm.setString(2, studentName);
			pstm.setInt(3,stPhone);
			pstm.setString(4, stAddress);
			pstm.setString(5,courseName);
			pstm.setString(6,JoiningDate);
			

			int noOfRowsAffected = pstm.executeUpdate();

			if (noOfRowsAffected >= 0) {out.println("<html><body bgcolor=#117864>");
			out.println("<center> <span style='color:black'><h2>Details Saved Sucessfully</h2></span></center><br>");
			out.println("<center><a href=\"Class.html\">Add new details of Student</a></center><br>");
			out.println("<center><a href=\"View.html\">View Details of Student</a><br><br></center>");
			out.println("<center><a href=\"Delete.html\">Delete Details of Student</a><br><br></center>");
			out.println("<center><a href=\"DashBoard.html\">Dashboard</a><br><br></center>");
			out.println("</html></body>");
			} else {
				out.println("<h1 style='color:red'> Product creation failed !</h1>");
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