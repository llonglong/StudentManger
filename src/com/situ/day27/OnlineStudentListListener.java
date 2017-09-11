package com.situ.day27;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.situ.student.pojo.Admin;

public class OnlineStudentListListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Admin> onLineAdminList = new ArrayList<Admin>();
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("onLineAdminList", onLineAdminList);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	

}
