<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>列出所有分类</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align:center">
  	<h1>分类信息</h1>
    <table frame="border" width="60%" border="1">
    	<tr>
    		<td>分类名称</td>
    		<td>分类描述</td>
    		<td>操作</td>
    	</tr>
    	
    	<c:forEach var="c" items="${categorys }">
    		<tr>
    			<td>${c.name }</td>
    			<td>${c.description }</td>
    			<td>
    				<a href="#">删除</a>
    				<a href="#">修改</a>
    			</td>
    		</tr>
    	</c:forEach>
    	
    </table>
   </body>
</html>
