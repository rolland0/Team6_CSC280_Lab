package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.exceptions.DatabaseException;

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
		String userName = request.getRemoteUser();
		User user = (User) userManager.getUserByName(userName);
		
		if(user != null) {
//			if(user.getGroups().contains(UserGroups.admins)){
//				boolean isAdmin = true;
//				request.setAttribute("admin", isAdmin);
//			}
			else{
				request.getRequestDispatcher("WEB-INF/adminPassword.jsp").forward(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
		User user = userManager.getUserByName(name);
		if(user != null && !user.getGroups().contains(UserGroups.admins)) {
			user.getGroups().add(UserGroups.admins);
			try{
				userManager.update(user);
			}catch(DatabaseException| EJBException| NullPointerException e){
				
			}
		}
		response.sendRedirect("GetPosts");
	}
}