package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/CRUDpage/AddPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User poster = (User) session.getAttribute("currentUser");

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Post post = new Post();

		post.setTitle(title);
		post.setContent(content);
		post.setPoster(poster);
		poster.getPosts().add(post);
		um.update(poster);

		pm.create(post);

		response.sendRedirect("GetPosts");
	}
}