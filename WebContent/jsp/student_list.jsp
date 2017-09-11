<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.situ.student.pojo.*"%>
<%@page import="com.situ.student.vo.PageBean"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function goPage(pageIndex) {
		$('#pageIndex').val(pageIndex);
		$('#searchForm').submit();
	}
	$(function() {
		$("#gender option[value='${searchCondition.gender}']").prop("selected",
				true);
	});
	
	
	function selectAlls() {
	    $("input[name=selectIds]").prop("checked", $("#selectAll").is(":checked"));
	}
	
	function deleteAll(){
	    var isDel = confirm("您确认要删除吗？");
	    if (isDel) {
	       //要删除
	       $("#mainForm").attr("action", "${pageContext.request.contextPath}/student?method=deleteAll");
	       $("#mainForm").submit();
	    }
	}


</script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.css" />
</head>
<body>
    <%@include file="common/header.jsp"%>
	<div class="container">
	
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<div class="list-group">
						<a
							href="${pageContext.request.contextPath}/students?method=pageList"
							class="list-group-item active">学生管理</a>
					    <a
							href="${pageContext.request.contextPath}/jsp/add_student.jsp"
							class="list-group-item">添加学生</a>
						<a
							href="${pageContext.request.contextPath}/jsp/Search_student.jsp"
							class="list-group-item">查询学生</a>
					</div>
				</div>
					<div class="col-md-10">
						<ul class="nav nav-tabs">
							<li class="active"><a
								href="${pageContext.request.contextPath}/students?method=pageList">学生列表</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/jsp/add_student.jsp">添加学生</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/jsp/Search_student.jsp">查询学生</a>
							</li>
						</ul>
						<form id="searchForm"
							action="${pageContext.request.contextPath}/students?method=searchByCondition"
							method="post">
							<input type="hidden" name="pageIndex" id="pageIndex" /> 姓名:<input
								type="text" name="name" value="${searchCondition.name}" /> 年龄:<input
								type="text" name="age" value="${searchCondition.age}" /> 性别:<select
								id="gender" name="gender">
								<option value="">不限</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
							<button class="btn btn-danger">搜索</button>
						</form>
						

					<form action="${pageContext.request.contextPath}/students?method=delectAll" method="post" id="mainForm">
						<input type="submit" value="批量删除" onclick="deleteAll"/>
						<table class="table table-striped">
							<tr>
								<td><input type="checkbox" id="selectAll" onclick="selectAlls()"/></td>
								<td>id</td>
								<td>姓名</td>
								<td>年龄</td>
								<td>性别</td>
								<td>地址</td>
								<td>删除</td>
								<td>修改</td>
							</tr>
							<c:forEach items="${list}" var="student">
								<tr>
									<td><input type="checkbox" name="selectIds" value="${student.id}"/></td>
									<td>${student.id}</td>
									<td>${student.name}</td>
									<td>${student.age}</td>
									<td>${student.gender}</td>
									<td>${student.address}</td>
									<td><a
										href="${pageContext.request.contextPath}/students?method=delectById&id=${student.id}">删除</a></td>
									<td><a
										href="${pageContext.request.contextPath}/students?method=toUpdateStudent&id=${student.id}">修改</a></td>

								</tr>
							</c:forEach>

						</table>
					</form>
						<!--分页开始-->
					<nav aria-label="Page navigation">
					<ul class="pagination">
						<!-- 上一页开始 -->
						<c:if test="${pageBean.pageIndex==1}">
							<li class="disabled"><a href="javascript:void(0);"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:if test="${pageBean.pageIndex!=1}">
							<li><a href="javascript:goPage('${pageBean.pageIndex-1}')"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

						<c:forEach begin="1" end="${pageBean.totalPage}" var="page">
							<c:if test="${pageBean.pageIndex!=page}">
								<li><a href="javascript:goPage('${page}')">${page}</a></li>
							</c:if>
							<c:if test="${pageBean.pageIndex==page}">
								<li class="active">
								<li class="active"><a href="javascript:goPage('${page}')">${page}</a></li>
								</li>
							</c:if>
						</c:forEach>


						<c:if test="${pageBean.pageIndex!=pageBean.totalPage}">
							<li><a href="javascript:goPage('${pageBean.pageIndex+1}')"
								aria-label="Previous"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
					</nav>
				</div>
				</div>
			</div>
		</div>

		<!--分页结束-->
	
</body>
</html>