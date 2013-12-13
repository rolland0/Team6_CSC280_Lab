package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CommentManager;
import managers.PostManager;
import managers.UserManager;

import org.eclipse.persistence.exceptions.DatabaseException;

import entities.Comment;
import entities.Post;
import entities.User;

@WebServlet("/AddComment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"members","admins"}))
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CommentManager cm;
	@EJB
	PostManager pm;
	@EJB 
	UserManager um;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		
		String username = request.getRemoteUser();
		User user = um.getUserByName(username);

		String content = request.getParameter("comment");
		int id = Integer.parseInt(request.getParameter("origPost"));

		Post thePost = pm.getPost(id);
		
		if(content!=null && !content.isEmpty()){
			Comment comment = new Comment();

			comment.setContent(content);
			comment.setPoster(user);
			comment.setPost(thePost);

			String origComment = request.getParameter("origComment");
			
			if(origComment != null && !origComment.isEmpty()) {
				int parentCommentId = Integer.parseInt(request.getParameter("origComment"));
				Comment parentComment = cm.getComment(parentCommentId);
				comment.setParentComment(parentComment);

			}
					
			try{
				cm.createComment(comment);
			}catch(DatabaseException | NumberFormatException e){
				request.setAttribute("error", "We're sorry, we cannot create a comment at this time. The post/comment you are commenting on may have been deleted.");
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
		}
		request.setAttribute("post", thePost);
		request.getRequestDispatcher("WEB-INF/displayPost.jsp").forward(request, response);
	}
}
