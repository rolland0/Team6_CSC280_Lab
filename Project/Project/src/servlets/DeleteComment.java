package servlets;

import java.io.IOException;

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

@WebServlet("/DeleteComment")
@DeclareRoles({"members","admins"})
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CommentManager cm;
	@EJB
	UserManager um;
	@EJB
	PostManager pm;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getRemoteUser();
		User currentUser = um.getUserByName(userName);
		int commentId = Integer.parseInt(request.getParameter("comment"));
		Comment comment = cm.getComment(commentId);
		User poster = comment.getPoster();

		String message = null;
		String location = null;
		//this method needs to be called in order to create the groups set
		//simply calling toString does not actually hit the database
		currentUser.getGroups().isEmpty();
		if(currentUser.getGroups().toString().contains("admins") || currentUser.getUsername().equals(poster.getUsername())){
			try{
				Post post = comment.getPost();
				post.getComments().remove(comment);
				System.out.println("comment removed from post");
				pm.update(post);
				poster.getComments().remove(comment);
				System.out.println("comment removed from poster");
				um.update(poster);
				cm.deleteComment(commentId);
				System.out.println("comment deleted");
				message = "Comment successfully deleted";
				location = "GetPosts";
			}catch(DatabaseException | EJBException | NullPointerException e){
				request.setAttribute("error", "The comment cannot be deleted at this time. It could've been deleted already.");
				location = "WEB-INF/error.jsp";
			}
		}
		else{
			message = "You do not have permission to delete this comment";
			location = "GetPosts";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher(location).forward(request, response);
	}
}
