<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@page import="com.situ.student.pojo.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
 
	  $(function(){
			$("#name").blur(function(){
				var name = $("#name").val();
				$.post(
					"${pageContext.request.contextPath}/students?method=checkName", //url
					{"name":name}, //data
					function(data) {
						// {"isExist":isExist}
						if(data.isExist) {
							$("#nameInfo").html("该用户已存在");
							$("#nameInfo").css("color", "red");
						} else {
							$("#nameInfo").html("该用户可以使用");
							$("#nameInfo").css("color", "green");
						}
					},//callback
					"json" //type
				);
			});
			
		});
</script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.css" />
</head>
<body>
    <%@include file="common/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<div class="list-group">
					<a
						href="${pageContext.request.contextPath}/students?method=pageList"
						class="list-group-item ">学生管理</a> 
				    <a
						href="${pageContext.request.contextPath}/jsp/add_student.jsp" class="list-group-item active">添加学生</a>
					<a
						href="${pageContext.request.contextPath}/jsp/Search_student.jsp" class="list-group-item">查询学生</a>
				</div>
			</div>
			<div class="col-md-10">
				<ul class="nav nav-tabs">
					<li><a
						href="${pageContext.request.contextPath}/students?method=pageList">学生列表</a>
					</li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/jsp/add_student.jsp">添加学生</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/jsp/Search_student.jsp">查询学生</a>
					</li>
				</ul>
				<form action="${pageContext.request.contextPath}/students?method=addStudent" method="post" class="form_border">
					
					<div class="form-group">
	                        <label>姓名</label>
	                        <input type="text" name="name" value="${student.name}" class="form-control" id="name"><span id="nameInfo"></span><br />
	                </div>
	                <div class="form-group">
	                        <label>年龄</label>
	                        <input type="text" name="age" value="${student.age }" class="form-control" id="name"><br />
	                </div>
	                <div class="form-group">
	                        <label>性别</label>
	                        <input type="text" name="gender" value="${student.gender }" class="form-control" id="name"><br />
	                </div>
					
					<input  class="btn btn-success btn-lg" type="submit" value="添加" />
				</form>
			</div>
		</div>
	</div>
	</div>






	
</body>
</html>