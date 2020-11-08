<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<script src="<c:url value="/resources/jquery-3.3.js"/>"></script>
		<script src="<c:url value="/resources/main.js"/>"></script>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
		
		<c:if test="${LOAD_PANEL == 'PRODUCTS'}">
			<title>Gym Equipment | Products</title>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'MAIN'}">
			<title>Gym Equipment | Home</title>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'SHOPPING_CART'}">
			<title>Gym Equipment | Cart</title>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'PROFIL'}">
			<title>Gym Equipment | Profil</title>
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
		
		<c:if test="${LOAD_PANEL == 'SHOPPING_CART'}">
			<%@ include file="shopping-cart-panel.jsp" %>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'PROFIL'}">
			<%@ include file="profil-panel.jsp" %>
		</c:if>
		
		<%@ include file="footer.jsp" %>
		
		<%@ include file="login.jsp" %>
		
		<%@ include file="create-account.jsp" %>
		
		<%@ include file="product.jsp" %>
		
	</body>
</html>



