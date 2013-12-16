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
import managers.UserManager;
import managers.VoteManager;
import entities.Comment;
import entities.User;
import entities.Vote;

@WebServlet("/RateComment")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"members","admins"}))
public class RateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CommentManager cm;
	@EJB
	UserManager um;
	@EJB
	VoteManager vm;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Comment comment = cm.getComment(id);
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		boolean voteUp = true;
		if(request.getParameter("upvote") != null){
			voteUp = true;
		}
		else if(request.getParameter("downvote") != null){
			voteUp = false;
		}
		else {
			request.setAttribute("error", "We're sorry we couldn't rate the comment at this time. Perhaps the comment or post doesn't exist any longer.");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		Vote vote = new Vote();
		vote.setComment(comment);
		vote.setValue(voteUp);
		vote.setVoter(currentUser);
		
		try{
			vm.createVote(vote);
			comment.addVote(vote);
			cm.updateComment(comment);
		}catch(Exception e)
		{
			request.setAttribute("error", "We're sorry we couldn't rate the comment at this time. Perhaps the comment or post doesn't exist any longer.");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			return;
		}
		request.setAttribute("post", comment.getPost());
		request.getRequestDispatcher("WEB-INF/displayPost.jsp").forward(request, response);
	}
}
