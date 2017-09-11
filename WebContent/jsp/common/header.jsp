<%@ page language="java"
    pageEncoding="utf-8"%>


<nav class="navbar navbar-default">
  <div class="container">
    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/students?method=pageList">教室管理系统</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
             <li class="active"><a href="${pageContext.request.contextPath}/students?method=pageList"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;学生管理</a></li>
             <li><a href="user_list.html"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;班级管理</a></li>
             <li><a href="content.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课程管理</a></li>
             <li><a href="tag.html"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;教务管理</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/admin?method=getOnLineList">${admin.name}</a></li>
        <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>