package servlets;

import java.io.IOException;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.UserManager;


import entities.User;


@WebServlet("/Promote")
@ServletSecurity(@HttpConstraint(rolesAllowed={"members", "admins"}))
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getRemoteUser();
		User user = (User) userManager.getUserByName(userName);
		
		if(user != null){
			if(request.getSession().getAttribute("isAdmin")!=null) {
				if(request.getAttribute("success")!=null) request.setAttribute("success", request.getAttribute("success"));
				request.getRequestDispatcher("WEB-INF/adminPage.jsp").forward(request, response);
				return;
			}
			else{
				request.getRequestDispatcher("WEB-INF/adminPassword.jsp").forward(request, response);				
			}
		}
	}
}