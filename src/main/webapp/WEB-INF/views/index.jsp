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
		
		<c:if test="${LOAD_PANEL == 'PRODUCTS'}">
			<title>Gym Equipment | Products</title>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'MAIN'}">
			<title>Gym Equipment | Home</title>
		</c:if>
	</head>
	<body>
	
		<%@ include file="header.jsp" %>
		
		<c:if test="${LOAD_PANEL == 'PRODUCTS'}">
			<%@ include file="products-panel.jsp" %>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'MAIN'}">
			<%@ include file="main-panel.jsp" %>
		</c:if>
		
		<%@ include file="footer.jsp" %>
		
		<%@ include file="login.jsp" %>
		
		<%@ include file="create-account.jsp" %>
		
		<%@ include file="product.jsp" %>
		
	</body>
</html>



