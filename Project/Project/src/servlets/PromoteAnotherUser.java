package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.exceptions.DatabaseException;

import entities.User;
import entities.UserGroups;
import managers.UserManager;

@WebServlet("/PromoteAnotherUser")
public class PromoteAnotherUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserManager userManager;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userList", userManager.getUsers());
		request.getRequestDispatcher("WEB-INF/PromoteAUser.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
		User userToPromote = userManager.getUserByName(name);
		
		if(userToPromote != null && !userToPromote.getGroups().contains(UserGroups.admins)) {
			userToPromote.getGroups().add(UserGroups.admins);
			try{
				userManager.update(userToPromote);
			}catch(DatabaseException| EJBException| NullPointerException e){
				request.setAttribute("error", "We couldn't make " + name + " an Administrator at this time.");
				request.getRequestDispatcher("WEB-INF/error.jsp");
			}
		}
		response.sendRedirect("Promote");
	}

}
