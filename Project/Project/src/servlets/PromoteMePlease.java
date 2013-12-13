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
import javax.servlet.http.HttpSession;

import managers.UserManager;
import entities.User;
import entities.UserGroups;

@WebServlet("/Promote")
@ServletSecurity(@HttpConstraint(rolesAllowed={"members", "admins"}))
public class PromoteMePlease extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		if(user != null) {
//			if(user.getGroups().contains(UserGroups.admins)){
//				boolean isAdmin = true;
//				request.setAttribute("admin", isAdmin);
//			}
			request.setAttribute("userList", userManager.getUsers());
			request.getRequestDispatcher("WEB-INF/adminPage.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
		User user = userManager.getUserByName(name);
		if(user != null && !user.getGroups().contains(UserGroups.admins)) {
			user.getGroups().add(UserGroups.admins);
			userManager.update(user);
		}
		response.sendRedirect("GetPosts");
	}
}