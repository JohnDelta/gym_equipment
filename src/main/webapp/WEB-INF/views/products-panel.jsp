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
			<a class='user-bar-login-btn' href='products/login' >Login</a>
		</c:if>

	</div>
</div>

<main>

	<div class="message-div" id="messageDiv"></div>
	
	<div class="section-div push-up-navbar">
	
		<h3 class="section-title push-up-title">Product</h3>
		<hr class="section-title-hr">
	
		<div class="products">
	
			<c:if test="${not empty PRODUCTS}">
				<c:forEach var="product" items="${PRODUCTS}">
					<div class="product">
						<img class="product-img" src="<c:url value="${product.getPhoto1()}?productsPage=${sessionScope.productsPage}"/>" >
						<p class="product-title"> ${product.getTitle()} </p>
						<p class="product-desc"> ${product.getDescription()} </p>
						<p class="product-price">
						
						<c:if test="${not empty product.getOfferPrice()}">
							Price : ${product.getOfferPrice()} &euro;<span style="font-size:14px;"> 
							from  ${product.getPrice()} &euro;</span>
						</c:if>
						
						<c:if test="${empty product.getOfferPrice()}">
							Price : ${product.getPrice()} &euro;
						</c:if>
						
						</p>
						<a class="product-btn" href="products?productsPage=${sessionScope.productsPage}&productsId=${product.getProductsId()}">
							See product >>
							<span class="arrow">></span>
						</a>
					</div>
				</c:forEach>
				
			</c:if>
			
			<c:if test="${empty PRODUCTS}">
				No products available
			</c:if>
	
		</div>
		
		<c:if test="${not empty PRODUCTS}">
			<div class="pagination-div">
				<div class="block">
					<c:if test="${sessionScope.productsPage > 0}">
						<a class="change-page" href="?productsPage=${sessionScope.productsPage - 1}" >PREV</a>
					</c:if>
					<c:if test="${sessionScope.productsPage <= 0}">
						<a class="change-page disabled" href="?productsPage=${sessionScope.productsPage - 1}" >PREV</a>
					</c:if>
					
					<div class="current-page"> <c:out value="${sessionScope.productsPage + 1}"></c:out> </div>
					
					<c:if test="${sessionScope.productsPage + 1 < sessionScope.numberOfPages}">
						<a class="change-page" href="?productsPage=${sessionScope.productsPage + 1}" >NEXT</a>
					</c:if>
					<c:if test="${sessionScope.productsPage + 1 >= sessionScope.numberOfPages}">
						<a class="change-page disabled" href="?productsPage=${sessionScope.productsPage + 1}" >NEXT</a>
					</c:if>
				</div>
			</div>
		</c:if>
		
	</div>
	
</main>



