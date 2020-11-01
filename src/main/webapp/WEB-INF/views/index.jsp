<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	if(session.getAttribute("username") == null) {
		session.setAttribute("username", "Guest");
		session.setAttribute("total", "0");
		session.setAttribute("role", "0");
	}

%>

<!DOCTYPE html>
<html>
	<head>
		<script src="resources/jquery-3.3.js"></script>
		<script src="resources/main.js"></script>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="resources/style.css">
		<title>Gym Equipment | Home</title>
	</head>
	<body>
	
		<%@ include file="header.jsp" %>
		
		<%@ include file="mainIndex.jsp" %>
		
		<%@ include file="footer.jsp" %>
		
		<%@ include file="login.jsp" %>
		
		<%@ include file="create-account.jsp" %>
		
		<div class="show-product-div" id="showProductForm"></div>
		
	</body>
</html>