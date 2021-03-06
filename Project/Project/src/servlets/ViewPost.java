package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Post;
import managers.PostManager;

@WebServlet("/ViewPost")
public class ViewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PostManager pm;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String forwardLocation = "";
		try{
			Post post = pm.getPost(id);			
			request.setAttribute("post", post);
			forwardLocation = "WEB-INF/displayPost.jsp";
		}catch(NullPointerException e){
			request.setAttribute("error", "Get the salt, you've hit a ghost post! This post does not exist.");
			forwardLocation = "WEB-INF/error.jsp";
		}
		request.getRequestDispatcher(forwardLocation).forward(request, response);
	}
}
