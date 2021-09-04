package com.simplilearn.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.db.utils.DbManager;

/**
 * Servlet implementation class DeleteTeacher
 */
@WebServlet("/DeleteTeacher")
public class DeleteTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTeacher() {
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
			String teachName=request.getParameter("teachName");
				DbManager dbManager = new DbManager(userName, password, jdbcUrl);
				con = dbManager.getConnection();
				//out.append("<h1 style='color:green'> DB connection is initialized !</h1>");
				ps= con.prepareStatement("Delete From teacher where teachName=?");
				
				
				ps.setString(1,teachName);
				int i = ps.executeUpdate();
				out.println("<html><body bgcolo=#117864>");
				 out.println("<center> <span style='color:green'><h2>Details Deleted Sucessfully</h2></span></center><br><br>");
				 out.println("<center><a href=\"Teacher.html\">Add details of Teacher</a></center><br>");
				 out.println("<center><a href=\"View.html\">View Details of Teacher</a></center><br>");
				 out.println("<center><a href=\"DeleteTeacher.html\">Delete More Details</a></center><br>");
				 out.println("</body> </html>");
		}catch(Exception e) {
			e.printStackTrace();
		}


}}

