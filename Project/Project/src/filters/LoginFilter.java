package filters;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import managers.UserManager;
import entities.User;
import entities.UserGroups;


@WebFilter("/*")
public class LoginFilter implements Filter {
	
	@EJB
	UserManager um;

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String username = request.getRemoteUser();

		if(username != null){
			
			HttpSession session = request.getSession();
			
			if(session.getAttribute("currentUser") == null){
				session.setAttribute("currentUser", um.getUserByName(username));
			}
			
			if(((User)session.getAttribute("currentUser")).getGroups().contains(UserGroups.admins)){
		
				session.setAttribute("isAdmin", true);
			}
		}
		chain.doFilter(request, resp);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
