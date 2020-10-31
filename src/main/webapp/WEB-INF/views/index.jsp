<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		
		<div class="login-div" id="loginDiv"></div>
		<div class="create-account-div" id="createAccountForm"></div>
		<div class="show-product-div" id="showProductForm"></div>
		
	</body>
</html>