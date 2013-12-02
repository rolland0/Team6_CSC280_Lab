package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PostManager;
import managers.UserManager;
import entities.Post;
import entities.User;


@WebServlet("/CreatePost")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PostManager pm;
	@EJB
	UserManager um;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = request.getServletContext();
		
		User poster = (User) sc.getAttribute("currentUser");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post post = new Post();
		
		post.setTitle(title);
		post.setContent(content);
		post.setPoster(poster);
		
		poster.getPosts().add(post);
		um.update(poster);
		
		pm.create(post);
		
	}

}
