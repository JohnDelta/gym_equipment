<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<c:if test="${not empty LOAD_LOGIN}">

	<div class="login-div" id="loginDiv">	
		<form class="login-form" method="POST" action="login">
			<button class="login-cancel-btn" onclick="redirectTo('${FROM_VIEW}')" id="closeLoginForm">X</button>
			<p class="login-form-title">Fill the form to login</p>
			<p id="login-error">${ERROR}</p>
			<p class="login-label">Username</p>
			<input class="login-input" type="text" minlength="4" maxlength="40" placeholder="user" name="username" required>
			<p class="login-label">Password</p>
			<input class="login-input" type="password" minlength="4" maxlength="40" placeholder="abc123" name="password" required>
			<input class="login-btn" type="submit" value="Login" name="login">
			<a onclick="redirectTo('${FROM_VIEW}/create-account')" class="login-create-new-link">Create new account here</a>
		</form>
	</div>	

</c:if>

