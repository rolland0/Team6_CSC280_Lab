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
public class PromoteMePlease extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getRemoteUser();
		User user = (User) userManager.getUserByName(userName);
		
		if(user != null && (boolean)request.getSession().getAttribute("isAdmin")) {
			request.getRequestDispatcher("WEB-INF/adminPage.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("GetPosts").forward(request, response);
	}
}