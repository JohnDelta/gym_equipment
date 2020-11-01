<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
	String profilLink = "";

	if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
		profilLink = "<a class='nav-link' href='profil.php'>Profil</a>";
	}
%>

<header>
	<div class="title-div">
		<h1><span>Gym</span>Equipment</h1>
	</div>
	
	<button class="nav-btn">&#9776;</button>
	<nav>
		<a class="nav-link nav-selected" href="index.php">Home</a>
		<a class="nav-link" href="products.php">Products</a>
		<%=profilLink%>
	</nav>
</header>


		
		
		