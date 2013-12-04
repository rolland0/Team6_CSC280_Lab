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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int MAX_USERNAME = 21;
		int MAX_PASSWORD = 51;
		
		boolean validAccount = true, validUsername = true, validPassword = true;

		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		String invalidInfo = null;
		
		if(isInfoValidLength(username, 4, MAX_USERNAME)){
			validUsername = !isUsernameTaken(username);
			
			if(!validUsername){
				invalidInfo = "This username is already taken. Choose another one between 5 and 20 characters.";
			}
		}
		else{
			invalidInfo = "Please choose a username between 5 and 20 characters.";
		}
		
		
		
		validPassword = isInfoValidLength(password, 4, MAX_PASSWORD);
		
		if(!validPassword){
			invalidInfo = "Please enter a password between 5 and 50 characters.";
		}
		
		
		validAccount = validUsername && validPassword;
		
		if(validAccount){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);

			userManager.create(user);
		}
		else{
			request.setAttribute("error", invalidInfo);
			request.getRequestDispatcher("AddUser.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("Setup").forward(request, response);
	}
	
	private boolean isUsernameTaken(String username) {
		boolean nameTaken = false;
		
		List<User> allUsers = userManager.getUsers();	
		for(User u : allUsers){
			if(u.getUsername().equals(username)){
				nameTaken = true;
			}
		}
		return nameTaken;
	}

	public boolean isInfoValidLength(String info, int minLength, int maxLength){
		return (info.length()>minLength && info.length()<maxLength);
	}


}
