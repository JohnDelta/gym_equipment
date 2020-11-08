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
		
		<a class='user-bar-login-btn' href='logout' >Logout</a>
		
		<c:if test="${sessionScope.role == 0}">
			<c:redirect url="/login" />
		</c:if>
		
		<c:if test="${empty USER}">
			<c:redirect url="/login" />
		</c:if>

	</div>
</div>

<main>

	<div class="message-div" id="messageDiv"></div>
	<div class="section-div push-up-navbar">
		<div class="split-main">
		
			<div class="profil-div">
				<h3 class="section-title push-up-title">Profil</h3>
				<hr class="section-title-hr">
				<div class="profil-block">
					<p class="profil-block-title">Μπορείτε να τροποποιείσετε τα στοιχεία του λογαριασμού σας</p>
					<p class="profil-block-error" id="profilBlockError"></p>
					<p class="profil-label">Username</p><input id="username" form="saveInfoForm" class="profil-input" type="text" name="username" readonly value="${USER.getUsername()}" required>
					<p class="profil-label">Name</p><input id="name" form="saveInfoForm" maxlength="40" class="profil-input" type="text" name="name" value="${USER.getName()}" required>
					<p class="profil-label">Lastname</p><input id="lastname" form="saveInfoForm" maxlength="40" class="profil-input" type="text" name="lastname" value="${USER.getLastname()}" required>
					<p class="profil-label">E-mail</p><input id="email" form="saveInfoForm" maxlength="40" class="profil-input" type="email" name="email" value="${USER.getEmail()}" required>
				</div>
				<div class="profil-block">
					<p class="profil-label">Phone</p><input id="phone" form="saveInfoForm" maxlength="10" min="6900000000" max="6999999999" class="profil-input" type="number" name="phone" value="${USER.getPhone()}" >
					<p class="profil-label">City</p><input id="city" form="saveInfoForm" maxlength="40" class="profil-input" type="text" name="city" value="${USER.getCity()}" >
					<p class="profil-label">Address</p><input id="address" form="saveInfoForm" maxlength="40" class="profil-input" type="text" name="address" value="${USER.getAddress()}" >
				</div>
				<form method="POST" action="profil/update" class="profil-form" id="saveInfoForm">
					<input class="profil-block-btn" type="submit" value="Save Info">
				</form>
			</div>
			
			<div class="profil-orders-div">
				<h3 class="section-title push-up-title">My Orders</h3>
				<hr class="section-title-hr">
				<div class="profil-orders">
					<p class="profil-orders-title">Δείτε τις παραγγελίας σας και την κατάσταση τους</p>
					<div class="profil-orders-list">
					
						<c:if test="${not empty ORDERS}">
							<c:forEach var="order" items="${ORDERS}">
							
								<div class="profil-order">
									<p class="profil-order-title">Order</p>
									<p class="profil-order-status">${order.getStatus()}</p>
									
									<c:forEach var="orderItem" items="${order.getProducts()}">
										<p class="profil-order-product-title">${orderItem.getKey().getTitle()}</p>
										<p class="profil-order-quantity">Quantity : ${orderItem.getValue().getQuantity()} x  ${orderItem.getValue().getPrice()}&euro;</p>
									</c:forEach>
									
									<p class="profil-order-price">Price : ${order.getTotalPrice()}&euro;</p>
								</div>
								<hr class="profil-order-hr">
							
							</c:forEach>
						</c:if>
						
						<c:if test="${empty ORDERS}">
							<p style="padding-left:5px;">Δεν έχετε κάποια παραγγελία</p>
						</c:if>
					
					</div>
				</div>
			</div>
		</div>
	</div>
	
</main>



