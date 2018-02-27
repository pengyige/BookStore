<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>前台页面</title>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <frameset rows="25%,*">
  	<frame src="${pageContext.request.contextPath }/client/head.jsp" name="head">
  	<frame src="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" name="body">
  
  </frameset>
 <%
 	request.getParameter(null);
  %>
  
</html>
