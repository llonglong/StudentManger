package com.situ.day27;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.pojo.Admin;
import com.situ.student.serve.IAdminServe;
import com.situ.student.serve.impl.AdminServeImpl;

public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		//1.获取参数
		String checkCode = req.getParameter("checkCode");
		if (checkCode == null || checkCode.equals("")) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
			return;
		}
		String checkCodeSession = (String) req.getSession().getAttribute("checkCodeSession");
		if (!checkCode.equals(checkCodeSession)) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
			return;
		}
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		IAdminServe adminService = new AdminServeImpl();
		Admin admin = adminService.findByNameAndPassword(name,password);
		
		//2.业务处理
		if (admin != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("admin", admin);
			List<Admin> onLineAdminList = (List<Admin>) getServletContext().getAttribute("onLineAdminList");
			onLineAdminList.add(admin);
			resp.sendRedirect(req.getContextPath() + "/students?method=pageList");
		}else {
			resp.sendRedirect(req.getContextPath() + "/html/fail.html");
		}
	}

}
