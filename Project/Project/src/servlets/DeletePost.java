package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.exceptions.DatabaseException;

import entities.Comment;
import entities.Post;
import entities.User;
import managers.CommentManager;
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
	@EJB
	CommentManager cm;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		String userName = request.getRemoteUser();
		User currentUser = um.getUserByName(userName);
		//this method needs to be called in order to create the groups set
		//simply calling toString does not actually hit the database
		currentUser.getGroups().isEmpty();
		if(currentUser.getGroups().toString().contains("admins")){
			int id = Integer.parseInt(request.getParameter("id"));
			try{
				pm.delete(id);
				currentUser.getPosts().remove(pm.getPost(id));
				um.update(currentUser);
				message = "Post successfully deleted.";
			}catch(DatabaseException | EJBException | NullPointerException e){
				request.setAttribute("error", "The post cannot be deleted at this time. It could've been deleted already.");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
		}
		else
			message = "You do not have permission to delete posts";

		request.setAttribute("message", message);
		request.getRequestDispatcher("GetPosts").forward(request, response);
	}
}
