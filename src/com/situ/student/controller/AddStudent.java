package com.situ.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.pojo.Student;
import com.situ.student.serve.IStudentServe;
import com.situ.student.serve.impl.StudnetSreveImpl;

public class AddStudent extends HttpServlet{
	@Override
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		
		Student student = new Student(name, Integer.parseInt(age), gender, "青岛");
		
		IStudentServe serve = new StudnetSreveImpl();
		serve.add(student);
		resp.sendRedirect("/Java1707Web/findAllStudent");
	}

}
