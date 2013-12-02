package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Comment;
import entities.User;
import managers.CommentManager;
import managers.UserManager;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddComment")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CommentManager cm;
	@EJB
	UserManager um;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		
		User user = (User) context.getAttribute("currentUser");
		
		String content = request.getParameter("comment");
		
		Comment comment = new Comment();
		
		comment.setContent(content);
		comment.setOriginalPoster(user);
		//comment.setPost(origPost);
		//comment.setReplies(replies);    ???
		
		//double check on this one
		user.getComments().add(comment);
		
		um.update(user);
		cm.createComment(comment);
	}

}
