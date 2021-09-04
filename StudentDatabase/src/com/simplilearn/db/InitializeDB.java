package com.simplilearn.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.db.utils.DbManager;

/**
 * Servlet implementation class InitializeDB
 */
@WebServlet("/init-db")
public class InitializeDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public static void main(String[] args) {
		
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializeDB() {
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

		String userName = "root";
		String password = "Awesome@2021";
		String jdbcUrl = "jdbc:mysql://localhost:3306/learnersacademy";
		
		Connection connection = null;
		Statement stmt = null;
		try {
			DbManager dbManager = new DbManager(userName, password, jdbcUrl);
			connection = dbManager.getConnection();
			//out.append("<h1 style='color:green'> DB connection is initialized !</h1>");
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			
			out.print("<html><body bgcolor=#117864>");
			out.print("<h1> Student Details :- </h1>");
			out.print("<style> table,td,th {" + "border:2px solid black;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Student Id</th>");
			out.print("<th> Student Name</th>");
			out.print("<th> Student PhoneNumber</th>");
			out.print("<th> Student Address</th>");
			out.print("<th> CourseName</th>");
			out.print("<th> Joining Date</th>");
			out.print("</tr>");
			
			while(rs.next() ) {
				
				out.print("<tr>");
				out.print("<td>" + rs.getInt("studID") + "</td>");
				out.print("<td>" + rs.getString("studName") + "</td>");
				out.print("<td>" + rs.getInt("studPhone") + "</td>");
				out.print("<td>" + rs.getString("studAddrs") + "</td>");
				out.print("<td>" + rs.getString("studCrse") + "</td>");
				out.print("<td>" + rs.getString("studJngDate") + "</td>");
				out.print("</tr>");
				
				}
			out.println("</table>");
			out.println("</br></br>");
			out.println("<center><a href=\"Delete.html\">Delete Details of Student</a><br><br></center>");
			out.println("<center><a href=\"Class.html\">Add details of Student</a></center><br>");
			out.println("<center><a href=\"DashBoard.html\">DashBoard</a><br><br></center>");
			out.println("</body> </html>");
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.append("<h1 style='color:red'> Problem connection to DB !</h1>");

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}