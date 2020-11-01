<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	
	String newUserLink = "";
	if(Integer.parseInt(session.getAttribute("role").toString()) == 0) {
		newUserLink = "<p>New to EComerse? click <a  class='create-btn' href='create-account?fromPage=' >here</a> to create a new account!</p>";
	}
	
	String logLink = "<a class='user-bar-login-btn' href='login?fromPage=' >Login</a>";
	if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
		logLink = "<a class='user-bar-login-btn' href='logout' >Logout</a>";
	}

%>

<div class="user-bar-div">
	<a class="basket-div" href="shopping-cart.php">
		<div class="basket-img"></div>
		<p class="basket-total">
			User : <%=session.getAttribute("username")%> | Total : <%=session.getAttribute("total")%>&euro;
		</p>
	</a>
	<div class="user-bar-login">
		<%=logLink%>
	</div>
</div>

<main>

	<div class="message-div" id="messageDiv"></div>
	
	<div class="showcase-div">
		<h2 class="showcase-title">
			Welcome to Gym Equipment E-store
			<span>Here you can find the best gym equipment</span>
		</h2>
		<img class="showcase-img" src="resources\showcasePhoto.jpg">
		<div class="showcase-create"> <%=newUserLink%> </div>
	</div>
	
	<div class="section-div">
		<h3 class="section-title">
			Offers
		</h3>
		<hr class="section-title-hr">
		<div class="products">
		
			<c:if test="${not empty offers}">
				<c:forEach var="offer" items="${offers}">
					<div class="product">';
						<img class="product-img" src="${offer.getProducts().getPhoto1()}">
						<p class="product-title"> ${offer.getProducts().getTitle()} </p>
						<p class="product-desc"> ${offer.getProducts().getDescription()} </p>
						<p class="product-price">
							Price : ${offer.getPrice()} &euro;<span style="font-size:14px;"> 
							from  ${offer.getProducts().getPrice()} &euro;</span>
						</p>
						<button class="product-btn" onclick="displayShowProduct('id product', 'index.jsp')">
							See product >>
							<span class="arrow">></span>
						</button>
					</div>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty offers}">
				No offers available
			</c:if>
	
		</div>
	</div>
	
	<div class="section-div">
		<h3 class="section-title">
			Features
		</h3>
		<hr class="section-title-hr">
		<div class="features">
			<div class="feature">
				<img src="resources/shippingIcon.svg">
				<div class="feature-desc">
					<h4>Free Shipping</h4>
					<p>We provide a free and secure transfer of your product</p>
				</div>
			</div>
			<div class="feature">
				<img src="resources/supportIcon.svg">
				<div class="feature-desc">
					<h4>Support service</h4>
					<p>If you have any trouble with your device, you can contact our support service team where our experienced employees will be there to help you.</p>
				</div>
			</div>
			<div class="feature">
				<img src="resources/warrantyIcon.svg">
				<div class="feature-desc">
					<h4>2+ Years Warranty</h4>
					<p>We assure you that our products are in excellent condition and in the case of a defective one we provide immediately replacement</p>
				</div>
			</div>
		</div>
	</div>
	
</main>