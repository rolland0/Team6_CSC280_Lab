package servlets;

import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.persistence.indirection.IndirectSet;
import entities.User;
import managers.PostManager;
import managers.UserManager;

@WebServlet("/DeletePost")
@DeclareRoles({"admins"})
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PostManager pm;
	@EJB
	UserManager um;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
		String userName = request.getRemoteUser();
		System.out.println(userName);
		User currentUser = um.getUserByName(userName);
		IndirectSet groups = (IndirectSet) currentUser.getGroups();
		//this method needs to be called in order to create the groups set
		//simply calling toString does not actually hit the database
//		currentUser.getGroups().isEmpty();
//		System.out.println(currentUser.getGroups());
//		if(currentUser.getGroups().toString().contains("admins")){
		if(groups.contains("admins")){
			System.out.println("you are an admin");
			int id = Integer.parseInt(request.getParameter("id"));
			pm.delete(id);
			message = "Post successfully deleted";
		}
		else
			message = "You do not have permission to delete posts";
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("GetPosts").forward(request, response);
	}
}
