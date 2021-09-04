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
 * Servlet implementation class ReadTeachers
 */
@WebServlet("/ReadTeachers")
public class ReadTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadTeachers() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
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
			ResultSet rs = stmt.executeQuery("select * from teacher");
			
			out.print("<html><body bgcolor=#117864>");
			out.print("<h1> Teacher Details :- </h1>");
			out.print("<style> table,td,th {" + "border:2px solid black;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Teacher Name</th>");
			out.print("<th> Teacher PhoneNumber</th>");
			out.print("<th> Teacher Address</th>");
			out.print("<th> Class Name</th>");
			out.print("<th> Joining Date</th>");
			out.print("</tr>");
			
			while(rs.next() ) {
				
				out.print("<tr>");
				
				out.print("<td>" + rs.getString("teachName") + "</td>");
				out.print("<td>" + rs.getInt("teachPhone") + "</td>");
				out.print("<td>" + rs.getString("teachAdrs") + "</td>");
				out.print("<td>" + rs.getString("studCrse") + "</td>");
				out.print("<td>" + rs.getString("studJngDate") + "</td>");
				out.print("</tr>");
				
				}
			out.println("</table>");
			out.println("</br></br>");
			out.println("<center><a href=\"DeleteTeacher.html\">Delete Details of Teacher</a><br><br></center>");
			out.println("<center><a href=\"Teacher.html\">Add Details of Teacher</a></center><br>");
			out.println("<center><a href=\"DashBoard.html\">DashBoard</a><br><br></center>");
			out.println("</body> </html>");
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.append("<h1 style='color:red'> Problem connection to DB !</h1>");

		}
}}
