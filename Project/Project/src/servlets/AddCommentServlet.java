package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Comment;
import entities.Post;
import entities.User;
import managers.CommentManager;
import managers.PostManager;
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
	@EJB
	PostManager pm;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("currentUser");
		
		String content = request.getParameter("comment");
		int id = Integer.parseInt(request.getParameter("origPost"));
		
		Post thePost = pm.getPost(id);
		Comment comment = new Comment();
		
		comment.setContent(content);
		comment.setOriginalPoster(user);
		comment.setPost(thePost);
		
		//comment.setReplies(replies); 
		
		cm.createComment(comment);
	}

}
