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
		<div class="split-main">
		
			<div class="profil-div">
				<h3 class="section-title push-up-title">
					<c:if test="${not empty USER}">
						Order as ${USER.getUsername()}
					</c:if>
					
					<c:if test="${empty USER}">
						Order as unregistred user
					</c:if>
				</h3>
				<hr class="section-title-hr">
				<div class="profil-block">
				
					<c:if test="${empty CART}">
						<p class="profil-block-title">Επιλέξτε κάποια προϊόν για να πραγματοποιείσετε την παραγγελία σας</p>
					</c:if>
					
					<c:if test="${not empty CART}">
						<p class="profil-block-title">Συμπληρώστε ή επεξεργαστείτε τα στοιχεία σας για την ολοκλήρωση της παραγγελίας</p>
						<p class="profil-block-error" id="profilBlockError"></p>
					</c:if>
					
					<c:if test="${not empty USER}">
						<p class="profil-label">Name</p>
						<input id="name" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="name" readonly value="${USER.getName()}" required>
						<p class="profil-label">Lastname</p>
						<input id="lastname" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="lastname" readonly value="${USER.getLastname()}" required>
						<p class="profil-label">E-mail</p>
						<input id="email" form="sendOrderForm" maxlength="40" class="profil-input" type="email" name="email" readonly value="${USER.getEmail()}" required>
						<p class="profil-label">Phone</p>
						<input id="phone" form="sendOrderForm" maxlength="10" min="6900000000" max="6999999999" class="profil-input" type="number" name="phone" value="${USER.getPhone()}" required>
						<p class="profil-label">City</p>
						<input id="city" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="city" value="${USER.getCity()}" required>
						<p class="profil-label">Address</p>
						<input id="address" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="address" value="${USER.getAddress()}" required>
					</c:if>
					
					<c:if test="${empty USER}">
						<p class="profil-label">Name</p>
						<input id="name" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="name" value="" required>
						<p class="profil-label">Lastname</p>
						<input id="lastname" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="lastname" value="" required>
						<p class="profil-label">E-mail</p>
						<input id="email" form="sendOrderForm" maxlength="40" class="profil-input" type="email" name="email" value="" required>
						<p class="profil-label">Phone</p>
						<input id="phone" form="sendOrderForm" maxlength="10" min="6900000000" max="6999999999" class="profil-input" type="number" name="phone" value="" required>
						<p class="profil-label">City</p>
						<input id="city" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="city" value="" required>
						<p class="profil-label">Address</p>
						<input id="address" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="address" value="" required>
					</c:if>
				</div>
			</div>
			
			<div class="shopping-cart-div">
				<h3 class="section-title push-up-title">
					Shopping Cart
				</h3>
				<hr class="section-title-hr">
				<div class="shopping-cart-div">
					<input type="hidden" name="shoppingCartRefresh" value="true">
					<p class="shopping-cart-label">Μπορείτε να επεξεργαστείτε τα προϊόντα που επιλέξατε πριν καταχωρείσετε την παραγγελία σας</p>
					<div class="shopping-cart">
					
						<c:if test="${not empty CART}">
							<c:forEach var="product" items="${CART}" >
								<div class="shopping-cart-line">
									<p class="shopping-cart-line-label">${product.getTitle()}</p>
									<input class="shopping-cart-remove-btn" type="submit" name="removeFromCart" value="X">
									<div class="shopping-cart-quantity-div">
										<p class="shopping-cart-quantity-title">Quantity</p>
										<input class="shopping-cart-quantity-btn" type="button" value="-"  onclick="shoppingCartSubQuantity('${product.getProductsId()}','${product.getProductsPrice()}')">
										<input form="shoppingCartRefreshForm" class="shopping-cart-quantity-number" readonly type="number" value="${product.getQuantity()}" id="shoppingCartQuantity${product.getProductsId()}">
										<input class="shopping-cart-quantity-btn" type="button" value="+" onclick="shoppingCartAddQuantity('${product.getProductsId()}','${product.getMaxQuantity()}','${product.getProductsPrice()}')">
									</div>
									<div>
										<p class="shopping-cart-line-price">Price : </p>
										<input class="shopping-cart-line-price-input" id="shoppingCartLinePrice${product.getProductsId()}" type="number" value="${product.getTotalPrice()}" readonly>
									</div>
								</div>
							</c:forEach>
							
							<form id="shoppingCartRefreshForm" method="POST" action="shopping-cart.php">
								<input type="hidden" name="shoppingCartRefresh" value="true">
								<input type="submit" class="shopping-cart-refresh-btn" value="Refresh Cart">
							</form>
							<p class="profil-label" style="margin:2px;">Τρόπος αποστολής</p>
							<select class="profil-input"><option>Αντικαταβολή</option></select>
							
							<form method="POST" action="shopping-cart.php" id="sendOrderForm">
								<p class="shopping-cart-total-price">
									Total Price : <c:out value="${sessionScope.total}"></c:out>&euro;
								</p>
								<input type="submit" class="shopping-cart-send-btn" value="Send Order">
							</form>
						</c:if>
						
						<c:if test="${empty CART}">
							Το καλάθι σας είναι άδειο!
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
</main>



