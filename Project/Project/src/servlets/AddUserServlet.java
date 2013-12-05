package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.UserManager;
import entities.User;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/CRUDpage/AddUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean usernameIsValid = false;
		boolean passwordIsValid = false;
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(username == null || username.isEmpty() ||
		   password == null || password.isEmpty() ||
		   email == null    || email.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "All fields are required");
		}
		
		
		String invalidInfo = null;
		System.out.println("User info check");
		
		if(isWithinLength(username, User.USERNAME_MIN_LENGTH, User.USERNAME_MAX_LENGTH)){
			System.out.println("Username is valid length");
			
			if(isUsernameFree(username)) {
				usernameIsValid = true;
			}
			else {
				invalidInfo = "This username is already taken. Choose another one between 5 and 20 characters.";
			}
		}
		else{
			invalidInfo = "Please choose a username between 5 and 20 characters.";
		}
		
		if(isWithinLength(password, User.USERNAME_MIN_LENGTH, User.USERNAME_MAX_LENGTH)){
			System.out.println("Password is valid length");
			passwordIsValid = true;
		}
		else{
			invalidInfo = "Please choose a password between 5 and 50 characters.";
		}
		
		if(usernameIsValid && passwordIsValid){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);

			userManager.create(user);
		}
		else{
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, invalidInfo);
			return;
		}
		response.sendRedirect("Setup");
	}
	
	private boolean isUsernameFree(String username) {
		boolean nameTaken = false;
		
		List<User> allUsers = userManager.getUsers();
		for(User u : allUsers){
			if(u.getUsername().equals(username)){
				nameTaken = true;
			}
		}
		return !nameTaken;
	}

	public boolean isWithinLength(String info, int minLength, int maxLength){
		return (info.length() >= minLength && 
				info.length() <= maxLength);
	}


}
