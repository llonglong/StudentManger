package com.situ.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class ContextServlet2 extends HttpServlet{public ContextServlet2() {
	// TODO Auto-generated constructor stub
}@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String name = (String) servletContext.getAttribute("name");
		System.out.println(name);
		List<Student> list = (List<Student>) servletContext.getAttribute("list");
		System.out.println(list);
		
	}

}
