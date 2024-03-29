<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="user-bar-div">
	<a class="basket-div" href="shopping-cart">
		<div class="basket-img"></div>
		<p class="basket-total">
			User : <c:out value="${sessionScope.username}"></c:out> | Total : <c:out value="${sessionScope.total}"></c:out>&euro;
		</p>
	</a>
	<div class="user-bar-login">
	
		<c:if test="${sessionScope.role == 1}">
			<a class='user-bar-login-btn' href='logout' >Logout</a>
		</c:if>
		
		<c:if test="${sessionScope.role == 0}">
			<a class='user-bar-login-btn' href='login' >Login</a>
		</c:if>
		
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
		<div class="showcase-create">

			<c:if test="${sessionScope.role == 0}">
				<p>New to EComerse? click <a  class='create-btn' href='create-account' >here</a> to create a new account!</p>
			</c:if>

		</div>
	</div>
	
	<div class="section-div">
		<h3 class="section-title">
			Offers
		</h3>
		<hr class="section-title-hr">
		<div class="products">
		
			<c:if test="${not empty OFFERS}">
				<c:forEach var="offer" items="${OFFERS}">
					<div class="product">
						<img class="product-img" src="<c:url value="${offer.getProducts().getPhoto1()}"/>">
						<p class="product-title"> ${offer.getProducts().getTitle()} </p>
						<p class="product-desc"> ${offer.getProducts().getDescription()} </p>
						<p class="product-price">
							Price : ${offer.getPrice()} &euro;<span style="font-size:14px;"> 
							from  ${offer.getProducts().getPrice()} &euro;</span>
						</p>
						<a class="product-btn" href="offer?offersId=${offer.getOffersId()}">
							See product >>
							<span class="arrow">></span>
						</a>
					</div>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty OFFERS}">
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