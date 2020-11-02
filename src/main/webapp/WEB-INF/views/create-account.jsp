<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<c:if test="${not empty LOAD_CREATE_ACCOUNT}">

	<div class="create-account-div" id="createAccountForm">
		<form class="create-account-form" method="POST" name="createAccountForm" action="create-account">
				<button class="create-account-cancel-btn" onclick="redirectTo('${FROM_VIEW}')">X</button>
				<p class="create-account-form-title">Fill the form to create a new account</p>
				<p id="create-account-error">${ERROR}</p>
				<p class="create-account-label">*Name</p>
				<input class="create-account-input" type="text" minlength="4" maxlength="40" name="name" placeholder="John" required>
				<p class="create-account-label">*Lastname</p>
				<input class="create-account-input" type="text" minlength="4" maxlength="40" name="lastname" placeholder="Deligiannis" required>
				<p class="create-account-label">*Username</p>
				<input class="create-account-input" type="text" minlength="4" maxlength="40" name="username" placeholder="john123" required>
				<p class="create-account-label">*password</p>
				<input class="create-account-input" type="password" minlength="4" maxlength="40" name="password" placeholder="drowssap123" required>
				<p class="create-account-label">*Email</p>
				<input class="create-account-input" type="email" name="email" placeholder="john123@hotmail.com" required>
				<p class="create-account-label">Phone</p>
				<input class="create-account-input" type="number" min="6900000000" max="6999999999" name="phone" placeholder="6901111111">
				<p class="create-account-label">City</p>
				<input class="create-account-input" type="text" minlength="4" maxlength="40" name="city" placeholder="Athens">
				<p class="create-account-label">Address</p>
				<input class="create-account-input" type="text" minlength="4" maxlength="40" name="address" placeholder="monastiraki">
				<input class="create-account-btn" type="submit" value="Create Account" name="createAccount">
				<a onclick="redirectTo('${FROM_VIEW}/login')" class="create-account-has-account-link">Already have an account? login here</a>
		</form>
	</div>
	
</c:if>


