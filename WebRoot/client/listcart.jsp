<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车列表</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align:center">
    	<table witdh="80%" frame="border" >
    		<tr>
    			<td>书名</td>
    			<td>作者</td>
    			<td>单价</td>
    			<td>数量</td>
    			<td>小计</td>
    			<td>
    				<a href="#">删除</a>
    			</td>
    		</tr>
    		
    			<c:forEach var="me" items="${cart.cart }">
    			<tr>
    			<td>${me.value.book.name }</td>
    			<td>${me.value.book.author }</td>
    			<td>${me.value.book.price }</td>
    			<td>${me.value.quantity }</td>
    			<td>${me.value.price }</td>
    			<td>
    				<a href="#">删除</a>
    			</td>
    		</tr>
    		
    		
    	</c:forEach>
    		<tr>
    		<td colspan="3">总价</td>
    		<td colspan="3">${cart.price }</td>
    		</tr>
    	</table>
    	<a href="${pageContext.request.contextPath }/client/OrderServlet">下订单</a>
    	
    
    	
  </body>
</html>
