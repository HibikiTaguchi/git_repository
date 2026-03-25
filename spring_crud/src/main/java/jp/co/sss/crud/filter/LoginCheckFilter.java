package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.form.LoginForm;

@Component
public class LoginCheckFilter extends HttpFilter {
	@Override 
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException { 
		String requestURL = request.getRequestURI();
		if (requestURL.endsWith("/spring_crud/") || requestURL.endsWith("/login")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = request.getSession();
			LoginForm loginForm = (LoginForm) session.getAttribute("loginForm");
			if (loginForm == null) {
				response.sendRedirect("/spring_crud/");
				return;
			} else {
				chain.doFilter(request, response);
			}
 		}
        
        
    }
}
