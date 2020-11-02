<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	
	String logLink = "<a class='user-bar-login-btn' href='login' >Login</a>";
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
	
	<div class="section-div push-up-navbar">
	
		<h3 class="section-title push-up-title">Product</h3>
		<hr class="section-title-hr">
	
		<div class="products">
	
			<c:if test="${not empty PRODUCTS}">
				<c:forEach var="product" items="${PRODUCTS}">
					<div class="product">
						<img class="product-img" src="${product.getPhoto1()}">
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
						<a class="product-btn" href="products?productsId=${product.getProductsId()}">
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
	</div>
	
</main>



