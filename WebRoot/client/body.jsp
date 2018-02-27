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
    
    <title>My JSP 'body.jsp' starting page</title>
    
	<meta charset="utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align:center;">
    <div id="content" style="width:840px ;height:100%;margin:0 auto" >
    
    	
    	<div id="category" style="float:left;width:200px ;height:300px;border:1px solid red;text-align:left" >
    		分类列表:
    		<c:forEach var="category" items="${categorys }">
    			<li>
    			<a href="${pageContext.request.contextPath }/client/IndexServlet?method=listBookWithCategory&category_id=${category.id}">${category.name }</a>
    			
    			</li>
    		</c:forEach> 
    		
    	</div>
    	
    	<div id="bookandpage" style="loat:left;width:500px;height:100%;margin-left:300px">
    		
    		<div id="books">
    				<c:forEach var="book" items="${page.list }">
    					<div id="book" style="margin-top:50px">
    						<div id="image" style="float:left">
    							<img src="${pageContext.request.contextPath }/images/${book.image}" style="width:150px;height:200px">
    						</div>
    						
    						<div id="bookinfo" style="float:left;text-align:center">
    							<ul>
    								<li>${book.name }</li>
    								<li>${book.author }</li>
    								<li>${book.price }</li>
    								<li>
    									<a href="${pageContext.request.contextPath }/client/BuyServlet?bookid=${book.id}">购买</a>
    								</li>
    							</ul>
    						</div>
    						
    						<div style="clear: both">
    						
    						</div>
    					</div>
    				
    				</c:forEach>
    		</div>
    		
    		<div id="page">
    			当前第[${page.pagenum }]页 &nbsp; &nbsp;
			    <c:forEach var="pagenum" begin="${page.startPage }" end="${page.endPage }">
			    		[<a href="${pageContext.request.contextPath }/client/IndexServlet?pagenum=${pagenum}&category_id=${param.category_id}&method=${param.method}">${pagenum }</a>]
			    </c:forEach>
			    &nbsp; &nbsp;  总共[${page.totalpage} ]页,总[${page.totalrecord }]记录
    		</div>
    		
    	</div>
    </div>
  </body>
</html>
