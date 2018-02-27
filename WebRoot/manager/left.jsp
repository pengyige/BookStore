<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台左侧导航</title>
    
	<meta charset="utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="#" target="body">分类管理</a><br/>
   	&nbsp&nbsp  <a href="${pageContext.request.contextPath }/manager/addcategory.jsp" target="body">添加分类</a><br/>
     &nbsp&nbsp <a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=listall" target="body">查看分类</a><br/><br/>
    <br/><br/>
    <a href="#" target="body">图书管理</a><br/><br/>
    &nbsp&nbsp <a href="${pageContext.request.contextPath }/manager/BookServlet?method=addUI" target="body">添加图书</a>
  	 &nbsp&nbsp <a href="${pageContext.request.contextPath }/manager/BookServlet?method=list" target="body">查看图书</a>
    <br/><br/>
    <a href="#" target="body">订单管理</a><br/><br/>
     &nbsp&nbsp <a href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=false" target="body">未查询订单</a>
  	 &nbsp&nbsp <a href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=true" target="body">已发货订单</a>
  </body>
</html>
