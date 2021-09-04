package com.simplilearn.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout.java")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();  
         session.invalidate();           
         PrintWriter out = response.getWriter();
         out.println("<html><body bgcolor=#117864>");
     out.println("<center> <span style='color:black'>Logged Out Sucessfully</span></center></br></br>");
     out.println("<center><span style='color:black'><a href=\"AdminLogin.html\">Click here to Login Again</a></span></center><br><br>");
     out.println("</body></html>");

	}
}
