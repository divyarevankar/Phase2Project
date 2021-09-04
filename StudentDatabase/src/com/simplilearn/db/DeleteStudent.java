package com.simplilearn.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.db.utils.DbManager;

/**
 * Servlet implementation class DeleteStudent
 */
@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();

		String userName = "root";
		String password = "Awesome@2021";
		String jdbcUrl = "jdbc:mysql://localhost:3306/learnersacademy";
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement ps ;
    	
		try {
			String studentName=request.getParameter("studentName");
				DbManager dbManager = new DbManager(userName, password, jdbcUrl);
				con = dbManager.getConnection();
				//out.append("<h1 style='color:green'> DB connection is initialized !</h1>");
				ps= con.prepareStatement("Delete From student Where studName=?");
				
				String query = "Delete From student Where studName=?";
				ps.setString(1,studentName);
				int i = ps.executeUpdate();
				out.println("<html><body bgcolor=#117864>");
				 out.println("<center> <span style='color:green'><h2>Details Deleted Sucessfully</h2></span></center><br><br>");
				 out.println("<center><a href=\"Class.html\">Add details of Student</a></center><br>");
				 out.println("<center><a href=\"View.html\">View Details of Student</a></center><br>");
				 out.println("<center><a href=\"Delete.html\">Delete More Details</a></center><br>");
				 out.println("</body> </html>");
		}catch(Exception e) {
			e.printStackTrace();
		}


}}