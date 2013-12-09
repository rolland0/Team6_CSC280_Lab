package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
		
		if(isUsernameValid(username)) {
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
		
		if(invalidInfo == null && !isPasswordValid(password)) {
			invalidInfo = "Please choose a password between " + 
					User.PASSWORD_MIN_LENGTH + " and " + 
					User.PASSWORD_MAX_LENGTH + " characters.";
		}
		
		if(invalidInfo == null && !isEmailValid(email)) {
			
			invalidInfo = "Please choose an email between " + 
					User.EMAIL_MIN_LENGTH + " and " + 
					User.EMAIL_MAX_LENGTH + " characters.";
		}
		
		if(invalidInfo == null){
			User user = new User();
			user.setUsername(username);
			try{
				user.setNewPassword(password);
			} catch (NoSuchAlgorithmException e){
				throw new ServletException(e);
			}
			user.setEmail(email);
			user.getGroups().add(UserGroups.members);
			
			userManager.create(user);
			
			//if someone is logged in log him/her out
			if (request.getRemoteUser() != null){
				request.logout();
			}
			//login the new user 
			request.login(username, password);
			
			response.sendRedirect("Setup");
		}
		else{
			request.setAttribute("error", invalidInfo);
			request.getRequestDispatcher("WEB-INF/CRUDpage/AddUser.jsp").forward(request, response);
			return;
		}
		
	}
	
	public boolean isWithinLength(String info, int minLength, int maxLength){
		return (info.length() >= minLength && 
				info.length() <= maxLength);
	}
	
	public boolean isPasswordValid(String password) {
		return !(password == null 
				|| password.isEmpty()
				|| !isWithinLength(password, User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH));
	}
	
	public boolean isEmailValid(String email) {
		return !(email == null
				|| email.isEmpty()
				|| !isWithinLength(email, User.EMAIL_MIN_LENGTH, User.EMAIL_MAX_LENGTH));
	}
	
	public boolean isUsernameValid(String username) {
		return !(username == null
				|| username.isEmpty()
				|| !isWithinLength(username, User.USERNAME_MIN_LENGTH, User.EMAIL_MAX_LENGTH));
	}


}
