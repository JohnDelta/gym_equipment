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
			
			<img src="${OFFER.getProducts().getPhoto1()}" class="show-product-img" id="showProductImg">
			
			<div class="show-product-photos-div">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo1" 
					checked="checked" onmouseover="showProductPhoto('photo1', '${OFFER.getProducts().getPhoto1()}')">
				<input class="show-product-select-photo" type="radio" name="showProductPhotos" id="photo2" 
					onmouseover="showProductPhoto('photo2', '${OFFER.getProducts().getPhoto2()}')">
			</div>
			
			<div class="show-product-info">
				<p class="show-product-title">${OFFER.getProducts().getTitle()}</p>
				<p class="show-product-desc">${OFFER.getProducts().getDescription()}</p>
				<p class="show-product-price">Price : ${OFFER.getPrice()}&euro;</p>
				<div class="show-product-number-div">
					<p class="show-product-number-desc">Quantity </p>
					<input type="hidden" name="productsId" value="${OFFER.getProducts().getProductsId()}">
					<input type="button" value="-" class="show-product-number-btn" onclick="subFromShowProductNumber()">
					<input type="number" value="0" readonly id="showProductNumber" class="show-product-number" name="productsQuantity">
					<input type="button" value="+" class="show-product-number-btn" onclick="addToShowProductNumber('${OFFER.getProducts().getQuantity()}')">
				</div>
				<input type="submit" class="show-product-add-btn" value="Add to cart">
			</div>
		</form>
	</div>
	
</c:if>






