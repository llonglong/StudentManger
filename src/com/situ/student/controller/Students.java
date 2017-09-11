package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.situ.student.pojo.Student;
import com.situ.student.serve.IStudentServe;
import com.situ.student.serve.impl.StudnetSreveImpl;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.SearchCondition;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class Students extends BaseServlet{
	
	/*protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String serveletPath = req.getServletPath();
		System.out.println(serveletPath);
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
	        return;

		}
				
		if ("/addStudent.do".equals(serveletPath)) {
			addStudent(req, resp);
		}else if ("/findAllStudent.do".equals(serveletPath)) {
			findAllStudent(req,resp);
		}else if ("/searchByname.do".equals(serveletPath)) {
			seachByname(req,resp);
		}else if ("/toUpdateStudent.do" .equals(serveletPath)) {
			toUpdateStudent(req,resp);
		}else if ("/updateStudent.do".equals(serveletPath)) {
			updateStudent(req, resp);
		}else if ("/delectStudent.do" .equals(serveletPath)) {
			delectById(req,resp);
		}
		 
	}*/

	private void delectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    String[] ids = req.getParameterValues("selectIds");
	    for (String id : ids) {
			System.out.println(id);
		}
	    IStudentServe serve = new StudnetSreveImpl();
	    serve.delectAll(ids);
	    resp.sendRedirect(req.getContextPath() + "/students?method=pageList");
	
	}
	
	private void delectById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		IStudentServe serve = new StudnetSreveImpl();
		boolean result = serve.delectById(Integer.parseInt(id));
		if (result) {
			System.out.println("删除成功");
			resp.sendRedirect(req.getContextPath() + "/students?method=pageList");
		} else {
			System.out.println("删除失败");
		}
	}

	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		Student student = new Student(Integer.parseInt(id), name, Integer.parseInt(age), gender, address);
		IStudentServe serve = new StudnetSreveImpl();
		serve.updateStudent(student);
		resp.sendRedirect("/Java1707Web/students?method=pageList");
	}

	private void toUpdateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		IStudentServe serve = new StudnetSreveImpl();
		Student student = serve.findById(Integer.parseInt(id));
		req.setAttribute("student", student);
		req.getRequestDispatcher("/jsp/student_update.jsp").forward(req, resp);
		
	}

	private void seachByname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("searchname");
		IStudentServe serve = new StudnetSreveImpl();
		List<Student> list = serve.findByName(name);
		req.setAttribute("list", list);
		req.getRequestDispatcher("display").forward(req, resp);
		
	}

	private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		Student student = new Student(name, Integer.parseInt(age), gender, "青岛");
		IStudentServe serve = new StudnetSreveImpl();
		serve.add(student);
		resp.sendRedirect("/Java1707Web/students?method=pageList");
	}
	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//1。收集参数并封装
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String pageIndexStr = req.getParameter("pageIndex");
	    String pageSizeStr = req.getParameter("pageSize");
	    int pageIndex = 1;  //默认取第一页的数据
	    if (pageIndexStr != null && !pageIndexStr.equals("")) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
	    int pageSize = 3;  //默认每一页数量
	    if (pageSizeStr != null && !pageIndexStr.equals("")) {
	    	 pageSize = Integer.parseInt(pageSizeStr);
		}
		SearchCondition searchCondition = new SearchCondition(pageIndex, pageSize, name, age, gender);
	    System.out.println(searchCondition);
		
	    //2.业务处理
		IStudentServe serve = new StudnetSreveImpl();
		PageBean pageBean = serve.searchByCondition(searchCondition);
		//3.将数据放到域对象中,跳转显示数据
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("searchCondition", searchCondition);
		List<Student>list = pageBean.getList();
	    req.setAttribute("list", list);
	    req.getRequestDispatcher("/jsp/student_list.jsp").forward(req, resp);
	}
	    
	private void pageList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	     String pageIndexStr = req.getParameter("pageIndex");
	     String pageSizeStr = req.getParameter("pageSize");
	     int pageIndex = 1;  //默认取第一页的数据
	     if (pageIndexStr != null && !pageIndexStr.equals("")) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
	     int pageSize = 3;  //默认每一页数量
	     if (pageSizeStr != null && !pageIndexStr.equals("")) {
	    	 pageSize = Integer.parseInt(pageSizeStr);
		}
	     IStudentServe serve = new StudnetSreveImpl();
	     PageBean pageBean = serve.getPageBean(pageIndex, pageSize);
	     System.out.println(pageBean);
	     List<Student>list = pageBean.getList();
	     req.setAttribute("list", list);
	     req.setAttribute("pageBean", pageBean);
	     req.getRequestDispatcher("/jsp/student_list.jsp").forward(req, resp);

	}
	
	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	    String name = req.getParameter("name");
	    IStudentServe serve = new StudnetSreveImpl();
	    boolean isExit = serve.checkName(name);
	    String str = "{\"isExist\":"+isExit+"}";
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write(str);
	}
	
	private void findAllStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		IStudentServe serve = new StudnetSreveImpl();
		List<Student> list = serve.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/student_list.jsp").forward(req, resp);
		
		
		
		/*HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");*/
		
		/*resp.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.println("<table border='1' cellspacing='0'>");
		printWriter.println("<h1>欢迎回来" + username + "</h1>");
		printWriter.println("<a href='/Java1707Web/logout'>退出</a>");
		printWriter.println("<tr>");
		printWriter.println("<td>id</td>");
		printWriter.println("<td>姓名</td>");
		printWriter.println("<td>年龄</td>");
		printWriter.println("<td>性别</td>");
		printWriter.println("<td>地址</td>");
		printWriter.println("</tr>");
		for (Student student : list) {
			printWriter.println("<tr>");
			printWriter.println("<td>" + student.getId() + "</td>");
			printWriter.println("<td>" + student.getName() + "</td>");
			printWriter.println("<td>" + student.getAge() + "</td>");
			printWriter.println("<td>" + student.getGender() + "</td>");
			printWriter.println("<td>" + student.getAddress() + "</td>");
			printWriter.println("</tr>");
		}
		printWriter.println("</table>");
		printWriter.close(); */
	}

}


