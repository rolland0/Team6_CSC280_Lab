package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.UserManager;
import entities.User;

@WebServlet("/UserProfile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		request.setAttribute("username", user.getUsername());
		request.setAttribute("password", user.getPassword());
		request.setAttribute("email", user.getEmail());
		request.getRequestDispatcher("WEB-INF/userProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> changes = new ArrayList<>();
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		if(userManager.isEmailValid(email)) {
			user.setEmail(email);
			changes.add("Success: Email changed successfully.");
		}
		else {
			changes.add("Error: Email could not be changed.");
		}
		
		if(userManager.isPasswordValid(password)) {
			user.setPassword(password);
			changes.add("Success: password changed successfully.");
		}
		else {
			changes.add("Error: Password could not be changed.");
		}
		
		session.setAttribute("profileChanges", changes);
		response.sendRedirect("UserProfile");
	}

}
