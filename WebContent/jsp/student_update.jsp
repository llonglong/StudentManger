<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@page import="com.situ.student.pojo.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
    
    <form action="${pageContext.request.contextPath}/students?method=updateStudent&id=${student.id}" method="post">
    
      姓名：<input type="text" name="name" value="${student.name }"/></br>
      年龄：<input type="text" name="age" value="${student.age }"/></br>
     性别：<input type="text" name="gender" value="${student.gender }"/></br>
     地址：<input type="text" name="address" value="${student.address }"/></br>
      <input type="submit" value="修改"/>
    </form>
</body>
</html>