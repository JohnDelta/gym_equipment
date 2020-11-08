<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<c:if test="${not empty OFFER}">

	<div class="show-product-div" id="showProductForm">
		<form class="show-product-form" method="POST" action="add-product">
			<a class="show-product-cancel-btn" onclick="redirectTo('')">X</a>
			
			<div class="show-product-form-title-div">
				<p class="show-product-form-title">Product info</p>
				<hr class="section-title-hr">
			</div>
			
			<img src="<c:url value="${OFFER.getProducts().getPhoto1()}"/>" class="show-product-img" id="showProductImg">
			
			<div class="show-product-photos-div">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo1" 
					checked="checked" onmouseover="showProductPhoto('photo1', '<c:url value="${OFFER.getProducts().getPhoto1()}"/>')">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo2" 
					onmouseover="showProductPhoto('photo2', '<c:url value="${OFFER.getProducts().getPhoto2()}"/>')">
			</div>
			
			<div class="show-product-info">
				<p class="show-product-title">${OFFER.getProducts().getTitle()}</p>
				<p class="show-product-desc">${OFFER.getProducts().getDescription()}</p>
				<p class="show-product-price">Price : ${OFFER.getPrice()}&euro;</p>
				<div class="show-product-number-div">
					<p class="show-product-number-desc">Quantity </p>
					<input type="hidden" name="productsId" value="${OFFER.getProducts().getProductsId()}">
					<input type="button" value="-" class="show-product-number-btn" onclick="subFromShowProductNumber()">
					<input type="number" value="1" min="1" readonly id="showProductNumber" class="show-product-number" name="productsQuantity" required>
					<input type="button" value="+" class="show-product-number-btn" onclick="addToShowProductNumber('${OFFER.getProducts().getQuantity()}')">
				</div>
				
				<c:if test="${OFFER.getProducts().getQuantity() > 0}">
					<input type="submit" class="show-product-add-btn" value="Add to cart">
				</c:if>
			
				<c:if test="${OFFER.getProducts().getQuantity() == 0}">
					<input type="submit" class="show-product-add-btn" value="Add to cart" disabled>
				</c:if>
			
			</div>
		</form>
	</div>
	
</c:if>

<c:if test="${not empty PRODUCT}">

	<div class="show-product-div" id="showProductForm">
		<form class="show-product-form" method="POST" action="products/add-product">
			<a class="show-product-cancel-btn" onclick="redirectTo('products')">X</a>
			
			<div class="show-product-form-title-div">
				<p class="show-product-form-title">Product info</p>
				<hr class="section-title-hr">
			</div>
			
			<img src="${PRODUCT.getPhoto1()}" class="show-product-img" id="showProductImg">
			
			<div class="show-product-photos-div">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo1" 
					checked="checked" onmouseover="showProductPhoto('photo1', '${PRODUCT.getPhoto1()}')">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo2" 
					onmouseover="showProductPhoto('photo2', '${PRODUCT.getPhoto2()}')">
			</div>
			
			<div class="show-product-info">
				<p class="show-product-title">${PRODUCT.getTitle()}</p>
				<p class="show-product-desc">${PRODUCT.getDescription()}</p>
				
				<c:if test="${not empty PRODUCT.getOfferPrice()}">
					<p class="show-product-price">Price : ${PRODUCT.getOfferPrice()}&euro;</p>
				</c:if>
				
				<c:if test="${empty PRODUCT.getOfferPrice()}">
					<p class="show-product-price">Price : ${PRODUCT.getPrice()}&euro;</p>
				</c:if>
				
				<div class="show-product-number-div">
					<p class="show-product-number-desc">Quantity </p>
					<input type="hidden" name="productsId" value="${PRODUCT.getProductsId()}">
					<input type="button" value="-" class="show-product-number-btn" onclick="subFromShowProductNumber()">
					<input type="number" value="1" min="1" readonly id="showProductNumber" class="show-product-number" name="productsQuantity" required>
					<input type="button" value="+" class="show-product-number-btn" onclick="addToShowProductNumber('${PRODUCT.getQuantity()}')">
				</div>
				
				<c:if test="${PRODUCT.getQuantity() > 0}">
					<input type="submit" class="show-product-add-btn" value="Add to cart">
				</c:if>
			
				<c:if test="${PRODUCT.getQuantity() == 0}">
					<input type="submit" class="show-product-add-btn" value="Add to cart" disabled>
				</c:if>

			</div>
		</form>
	</div>
	
</c:if>









