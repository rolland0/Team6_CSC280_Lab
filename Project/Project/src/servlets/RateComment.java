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

import entities.Comment;
import managers.CommentManager;
import managers.UserManager;

@WebServlet("/RateComment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"members","admins"}))
public class RateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CommentManager cm;
	@EJB
	UserManager um;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Comment comment = cm.getComment(id);
		
		if(request.getParameter("upvote") != null){
			comment.incrementRating();
		}
		else if(request.getParameter("downvote") != null){
			comment.decrementRating();
		}
		cm.updateComment(comment);
		
		request.setAttribute("post", comment.getPost());
		request.getRequestDispatcher("displayPost.jsp").forward(request, response);
	}
}
