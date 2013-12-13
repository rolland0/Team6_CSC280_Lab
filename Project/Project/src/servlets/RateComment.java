package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.exceptions.DatabaseException;

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
		try{
			cm.updateComment(comment);
		}catch(DatabaseException| EJBException| NullPointerException e)
		{
			request.setAttribute("error", "We're sorry we couldn't rate the comment at this time. Perhaps the comment or post doesn't exist any longer.");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		request.setAttribute("post", comment.getPost());
		request.getRequestDispatcher("WEB-INF/displayPost.jsp").forward(request, response);
	}
}
