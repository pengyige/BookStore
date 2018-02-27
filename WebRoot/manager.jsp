<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager.jsp' starting page</title>
    <meta charset="utf-8">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
	<frameset rows="25%,*">
		<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head"/>
		<frameset cols="15%,*">
			<frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left"/>
			<frame src="${pageContext.request.contextPath }/manager/body.jsp" name="body"/>
		</frameset>
	</frameset>

</html>
