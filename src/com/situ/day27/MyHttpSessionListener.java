package com.situ.day27;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.situ.student.pojo.Admin;

public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession httpSession = se.getSession();
    	ServletContext servletContext = httpSession.getServletContext();
    	List<Admin> onLineAdminList = (List<Admin>) servletContext.getAttribute("onLineAdminList");
    	Admin admin = (Admin) httpSession.getAttribute("admin");
    	if (admin != null) {
    		onLineAdminList.remove(admin);

		
	}
	

}
}
