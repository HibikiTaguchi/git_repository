package jp.co.sss.practice.p11.q01.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class URLCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, 
	 HttpServletResponse response, FilterChain chain) 
	  throws IOException, ServletException{
		StringBuffer url = request.getRequestURL();
		
	}
}
