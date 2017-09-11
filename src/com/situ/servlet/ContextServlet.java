package com.situ.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.situ.student.dao.IStudentDao;
import com.situ.student.pojo.Student;
import com.situ.student.serve.IStudentServe;
import com.situ.student.serve.impl.StudnetSreveImpl;
import java.util.List;

public class ContextServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("name", "码子");
		IStudentServe serve = new StudnetSreveImpl();
		List<Student> list = serve.findAll();
		servletContext.setAttribute("list", list);
		
	}

}
