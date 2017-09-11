package com.situ.day5;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.pojo.Admin;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
	        return;*/
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI();
		
		String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		if (requestPath.equals("login.html") ||requestPath.equals("login")
				||requestPath.equals("checkImg")) {
			chain.doFilter(request, response);
		}
		else {
			HttpSession session = httpServletRequest.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
	               httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/html/login.html");
	               return;
	           }
			chain.doFilter(request, response);

		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
