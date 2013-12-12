package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.UserManager;
import entities.User;
import entities.UserGroups;

@WebServlet("/PromotionPassword")
public class PromotionPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_PASS = "team6";

	@EJB
	UserManager um;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String message = "";
		if(password.equals(ADMIN_PASS)){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			if(user != null && !user.getGroups().contains(UserGroups.admins)) {
				user.getGroups().add(UserGroups.admins);
				um.update(user);
				message = "You are now an admin";
			}
			else{
				message = "You are already an admin";
			}
		}
		else{
			message = "You entered the incorrect password. You may not become an admin";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("GetPosts").forward(request, response);
	}
}
