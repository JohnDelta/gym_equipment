<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String profilLink = "";

	if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
		profilLink = "<a class='nav-link' href='profil'>Profil</a>";
	}
%>

<header>
	<div class="title-div">
		<h1><span>Gym</span>Equipment</h1>
	</div>
	
	<button class="nav-btn">&#9776;</button>
	<nav>
		<c:if test="${LOAD_PANEL == 'PRODUCTS'}">
			<a class="nav-link" href="/gym_equipment/">Home</a>
			<a class="nav-link nav-selected" href="/gym_equipment/products">Products</a>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'MAIN'}">
			<a class="nav-link nav-selected" href="/gym_equipment/">Home</a>
			<a class="nav-link" href="/gym_equipment/products">Products</a>
		</c:if>
		
		<%=profilLink%> 
	</nav>
</header>


		
		
		