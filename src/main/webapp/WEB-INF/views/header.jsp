<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
	<div class="title-div">
		<h1><span>Gym</span>Equipment</h1>
	</div>
	
	<button class="nav-btn">&#9776;</button>
	<nav>
		<c:if test="${LOAD_PANEL == 'PRODUCTS'}">
			<a class="nav-link" href="/gym_equipment/">Home</a>
			<a class="nav-link nav-selected" href="/gym_equipment/products">Products</a>
			<c:if test="${sessionScope.role == 1}">
				<a class='nav-link' href='profil'>Profil</a>
			</c:if>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'MAIN'}">
			<a class="nav-link nav-selected" href="/gym_equipment/">Home</a>
			<a class="nav-link" href="/gym_equipment/products">Products</a>
			<c:if test="${sessionScope.role == 1}">
				<a class='nav-link' href='profil'>Profil</a>
			</c:if>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'SHOPPING_CART'}">
			<a class="nav-link" href="/gym_equipment/">Home</a>
			<a class="nav-link" href="/gym_equipment/products">Products</a>
			<c:if test="${sessionScope.role == 1}">
				<a class='nav-link' href='profil'>Profil</a>
			</c:if>
		</c:if>
		
		<c:if test="${LOAD_PANEL == 'PROFIL'}">
			<a class="nav-link" href="/gym_equipment/">Home</a>
			<a class="nav-link" href="/gym_equipment/products">Products</a>
			<a class='nav-link nav-selected' href='profil'>Profil</a>
		</c:if>
	</nav>
</header>


		
		
		