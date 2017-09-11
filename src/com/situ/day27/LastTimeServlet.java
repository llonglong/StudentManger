package com.situ.day27;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LastTimeServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = simpleDateFormat.format(date);
		Cookie cookie = new Cookie("LastTime", time);
		cookie.setMaxAge(60*60*24*30);
		resp.addCookie(cookie);
		
		String LastTime = null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null){
			for (Cookie cookie2 : cookies) {
				if(cookie2.getName().equals("LastTime")){
					LastTime = cookie2.getValue();
				}
			}
		}
		resp.setContentType("text/html; charset=utf-8");
		if (LastTime == null) {
			resp.getWriter().println("欢迎您第一次访问");
		}else {
			resp.getWriter().println("您上次访问的时间是:" + LastTime);
		}
		
	}

}
