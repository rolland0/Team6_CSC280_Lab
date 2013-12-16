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

import entities.User;
import managers.UserManager;


@WebServlet("/RemoveUser")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"members","admins"}))
public class RemoveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserManager um;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("id"));
		
		User user = um.getUser(userID);
		user.setActive(false);
		um.update(user);
		
		
	}

}
