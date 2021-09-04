package com.simplilearn.db.utils;

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

/**
 * Servlet implementation class ReadClass
 */
@WebServlet("/ReadClass")
public class ReadClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadClass() {
        super();
        // TODO Auto-generated constructor stub
    }

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
			ResultSet rs = stmt.executeQuery("select * from class");
			
			out.print("<html><body bgcolor=#117864>");
			out.print("<h1> Class Details :- </h1>");
			out.print("<style> table,td,th {" + "border:2px solid black;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Class Name</th>");
			out.print("<th> Teacher Name</th>");
			out.print("<th> Teacher ID</th>");
			out.print("<th> Joining Date</th>");
			out.print("</tr>");
			
			while(rs.next() ) {
				
				out.print("<tr>");
				
				out.print("<td>" + rs.getString("className") + "</td>");
				out.print("<td>" + rs.getString("teachName") + "</td>");
				out.print("<td>" + rs.getInt("teachId") + "</td>");
				out.print("<td>" + rs.getString("JoiningDate") + "</td>");
				out.print("</tr>");
				
				}
			out.println("</table>");
			out.println("</br></br>");
			out.println("<center><a href=\"DashBoard.html\">DashBoard</a><br><br></center>");
			out.println("</body> </html>");	
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.append("<h1 style='color:red'> Problem connection to DB !</h1>");

		}
}}
