package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CommentManager;
import managers.PostManager;
import managers.UserManager;
import entities.Comment;
import entities.Post;
import entities.User;

/**
 * Servlet implementation class DebugData
 */
@WebServlet("/DebugData")
public class DebugData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;
	
	@EJB
	PostManager postManager;
	
	@EJB
	CommentManager commentManager;
	
	private static boolean initialized = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!initialized) {
			for(int i = 0; i < 10; i++) {
				User user = new User();
				user.setUsername("user" + i);
				user.setEmail("email" + i + "@email.email");
				user.setPassword("password" + i);
				user.setSalt("salt" + i);
				userManager.create(user);
			}
			
			List<User> users = userManager.getUsers();
			for(int i = 0; i < 5; i++) {
				Post post = new Post();
				post.setContent("Content " + i);
				post.setPoster(users.get(i));
				post.setTitle("Title " + i);
				users.get(i).getPosts().add(post);
				postManager.create(post);
				userManager.update(users.get(i));
			}
			
			List<Post> posts = postManager.getPosts();
			for(int i = 0; i < 20; i++) {
				Comment comment = new Comment();
				comment.setContent("Comment " + i);
				Post post = posts.get(new Random().nextInt(posts.size()));
				comment.setPost(post);
				comment.setPoster(post.getPoster());
				post.getComments().add(comment);
				commentManager.createComment(comment);
				postManager.update(post);
			}
		}
		
		initialized = true;
		request.getRequestDispatcher("Setup").forward(request, response);
	}
}
