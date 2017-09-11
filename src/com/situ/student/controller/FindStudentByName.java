package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.dao.IStudentDao;
import com.situ.student.pojo.Student;
import com.situ.student.serve.IStudentServe;
import com.situ.student.serve.impl.StudnetSreveImpl;

public class FindStudentByName extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("searchname");
		IStudentServe serve = new StudnetSreveImpl();
		List<Student> list = serve.findByName(name);
		req.setAttribute("list", list);
		req.getRequestDispatcher("display").forward(req, resp);
	}
}
