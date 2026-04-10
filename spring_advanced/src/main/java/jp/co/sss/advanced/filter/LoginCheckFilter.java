package jp.co.sss.advanced.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.advanced.entity.Staff;

@Component
public class LoginCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
		String url = request.getRequestURL().toString();
		System.out.println(url);
		if (!url.contains("/admin/") || url.equals("http://localhost:7788/pizzashop/admin/login")) {
			System.out.println("権限チェック不要");
			chain.doFilter(request, response);
		} else {
			System.out.println("権限チェック");
			HttpSession session = request.getSession();
			Staff loginUser = (Staff) session.getAttribute("loginUser");
			if (Objects.equals(loginUser, null)) {
				System.out.println("権限チェック失敗");
				response.sendRedirect("/pizzashop/admin");
				return;
			} else {
				System.out.println("権限チェック通過");
				chain.doFilter(request, response);
			}
		}
	}
}
