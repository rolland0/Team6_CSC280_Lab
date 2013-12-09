package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PostManager;
import entities.Post;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PostManager postManager;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keywords = request.getParameter("searchQuery");
		if(keywords == null || keywords.isEmpty()) {
			response.sendRedirect("Setup");
			return;
		}
		
		List<Post> posts = postManager.getPosts();
		ArrayList<Post> titleMatches = new ArrayList<>();
		ArrayList<Post> contentMatches = new ArrayList<>();
		
		for (Post post : posts) {
			if(post.getTitle().contains(keywords)) {
				titleMatches.add(post);
			}
			if(post.getContent().contains(keywords)) {
				contentMatches.add(post);
			}
		}
		request.setAttribute("titleMatches", titleMatches);
		request.setAttribute("contentMatches", contentMatches);
		request.getRequestDispatcher("WEB-INF/searchResults.jsp").forward(request, response);
	}
}
