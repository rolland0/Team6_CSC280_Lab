package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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

@WebServlet("/UserProfile")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"members","admins"}))
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/userProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> changes = new ArrayList<>();
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		User user = userManager.getUserByName(((User)session.getAttribute("currentUser")).getUsername());
		
		if(userManager.isEmailValid(email)) {
			user.setEmail(email);
			try{
				userManager.update(user);
			}catch(DatabaseException| EJBException| NullPointerException e){
				
			}
			changes.add("Success: Email changed successfully.");
		}
		else {
			changes.add("Error: Email could not be changed.");
		}
		
		if(userManager.isPasswordValid(password)) {
			try {
				user.setNewPassword(password);
				try{
					userManager.update(user);
				}catch(DatabaseException| EJBException| NullPointerException e){
					
				}
			} catch (NoSuchAlgorithmException e) {
				changes.add("Error: Password could not be changed.");
			}
			changes.add("Success: password changed successfully.");
		}
		else {
			changes.add("Error: Password could not be changed.");
		}
		session.setAttribute("profileChanges", changes);
		response.sendRedirect("UserProfile");
	}

}
