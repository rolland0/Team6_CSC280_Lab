package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.UserManager;
import entities.User;
import entities.UserGroups;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/CRUDpage/AddUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invalidInfo = null;
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userManager.isUsernameValid(username)) {
			if(!userManager.isUsernameFree(username)) {
				invalidInfo = "This username is already taken. Choose another one between " +
						User.USERNAME_MIN_LENGTH + " and " +
						User.USERNAME_MAX_LENGTH + " characters.";
			}
		}
		else{
			invalidInfo = "Please choose a username between " + 
					User.USERNAME_MIN_LENGTH + " and " + 
					User.USERNAME_MAX_LENGTH + " characters.";
		}
		
		if(invalidInfo == null && !userManager.isPasswordValid(password)) {
			invalidInfo = "Please choose a password between " + 
					User.PASSWORD_MIN_LENGTH + " and " + 
					User.PASSWORD_MAX_LENGTH + " characters.";
		}
		
		if(invalidInfo == null && !userManager.isEmailValid(email)) {
			
			invalidInfo = "Please choose an email between " + 
					User.EMAIL_MIN_LENGTH + " and " + 
					User.EMAIL_MAX_LENGTH + " characters.";
		}
		
		if(invalidInfo == null){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.getGroups().add(UserGroups.members);
			
			userManager.create(user);
			request.getSession().setAttribute("accountCreated", user);
			response.sendRedirect("Setup");
		}
		else{
			request.setAttribute("error", invalidInfo);
			request.getRequestDispatcher("WEB-INF/CRUDpage/AddUser.jsp").forward(request, response);
			return;
		}
		
	}


}
