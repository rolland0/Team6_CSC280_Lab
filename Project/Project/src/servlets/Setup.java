package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PostManager;

@WebServlet("/Setup")
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PostManager pm;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("posts", pm.getPosts());
		request.getRequestDispatcher("home.jsp");
	}
}
