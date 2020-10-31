<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
	String profilLink = "";
	String logForm = "<button class='user-bar-login-btn' onclick='displayLogin(\'\',\'index.php\')' >Login</button>";

	if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
		profilLink = "<a class='nav-link' href='profil.php'>Profil</a>";
		logForm = "<form method='POST' action='logout.php'>" +
					"<input type='submit' class='user-bar-login-btn' value='Logout' name='logout'>" +
				 "</form>";
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

<div class="user-bar-div">
	<a class="basket-div" href="shopping-cart.php">
		<div class="basket-img"></div>
		<p class="basket-total">
			User : <%=session.getAttribute("username")%> | Total : <%=session.getAttribute("total")%>&euro;
		</p>
	</a>
	<div class="user-bar-login">
		<%=logForm%>
	</div>
</div>
		
		
		