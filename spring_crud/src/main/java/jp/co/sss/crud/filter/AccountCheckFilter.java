package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;

public class AccountCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response,
		      FilterChain chain) throws IOException, ServletException {
		String requestURL = request.getRequestURI();
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("user");
		if (requestURL.contains("regist") || requestURL.contains("delete")) {
			if (loginUser.getAuthority() == 1) {
				response.sendRedirect("/spring_crud/");
				return;
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
