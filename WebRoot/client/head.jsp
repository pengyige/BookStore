<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    

  </head>
  
  <body style="text-align:center">
    	<h1 style="text-align:center">326书店</h1>
    	<br/><br/>
    	<div style="float:left;margin-left:600px">
	    	<a href="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" target="body">首页</a>
	    	<a href="">查看购物车</a>
			<a href="">查看订单</a>
		</div>
		
		<div style="float:right;margin-right:50px" >
		<c:if test="${user==null }">
		<form action="${pageContext.request.contextPath }/client/LoginServlet">
			用户名:<input type="text" name="username" style="width:100">
			密码:<input type="password" name="password" style="width:100">
			<input type="submit" value="登入">
			<input type="button" value="注册" onclick="javascript:window.parent.body.location.href='${pageContext.request.contextPath}/register.jsp'" >
		</form>
		</c:if>
		
		<c:if test="${user!=null }">
			欢迎您:${user.username} <a href="${pageContext.request.contextPath }/client/LogoutServlet">注销</a>
		</c:if>
		</div>
		
		
  </body>
</html>
