
$(document).ready(function(){
	
	
});

function redirectTo(url){
	if(url === null || url === "" || url === "/") {
		window.location.replace("/gym_equipment");
	} else {
		window.location.replace("/gym_equipment/"+url);
	}
}

function showProductPhoto(radioId,imgId){
	//imgId is the path to the photo in files
	if(radioId==='photo1'){
		document.getElementById("showProductImg").src = imgId;
		document.getElementById(radioId).checked = "true";
	}else if(radioId==='photo2'){
		document.getElementById("showProductImg").src = imgId;
		document.getElementById(radioId).checked = "true";
	}
}

function subFromShowProductNumber(){
	var n = document.getElementById("showProductNumber").value;
	if(parseInt(n) > 1){
		document.getElementById("showProductNumber").value = parseInt(n) - 1;
	}
}

function addToShowProductNumber(max){
	var n = document.getElementById("showProductNumber").value;
	if(parseInt(n)<parseInt(max)){
		document.getElementById("showProductNumber").value = parseInt(n) + 1;
	}
}

function shoppingCartAddQuantity(id_product,max,price){
	var n = document.getElementById("shoppingCartQuantity"+id_product).value;
	if(parseInt(n)<parseInt(max)){
		document.getElementById("shoppingCartQuantity"+id_product).value = parseInt(n) + 1;
		var x = document.getElementById("shoppingCartLinePrice"+id_product).value;
		document.getElementById("shoppingCartLinePrice"+id_product).value = (parseFloat(x) + parseFloat(price)).toFixed(1);
	}
}

function shoppingCartSubQuantity(id_product,price){
	var n = document.getElementById("shoppingCartQuantity"+id_product).value;
	if(n>1){
		document.getElementById("shoppingCartQuantity"+id_product).value = parseInt(n) - 1;
		var x = document.getElementById("shoppingCartLinePrice"+id_product).value;
		document.getElementById("shoppingCartLinePrice"+id_product).value = (parseFloat(x) - parseFloat(price)).toFixed(1);
	}
}

function sendOrderFormValidate(){
	var name = document.forms["createAccountForm"].name.value;
	var lastname = document.forms["createAccountForm"].lastname.value;
	var phone = document.forms["createAccountForm"].phone.value;
	var email = document.forms["createAccountForm"].email.value;
	var city = document.forms["createAccountForm"].city.value;
	var address = document.forms["createAccountForm"].address.value;
	
	if(/^[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ]+$/.test(name)===false){
		document.getElementById("profilBlockError").innerHTML = "Name must contain only letters";
		return false;
	}
	if(/^[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ]+$/.test(lastname)===false){
		document.getElementById("profilBlockError").innerHTML = "Lastname must contain only letters";
		return false;
	}
	if(city!=="" && /^[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ]+[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ ]*$/.test(city)===false){
		document.getElementById("profilBlockError").innerHTML = "City must contain only letters and space";
		return false;
	}
	if(address!=="" && /[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ]+[a-zA-Zα-ζΑ-ΖάίόέύώήΆΈΎΊΌΉΏ0-9 ]*$/.test(address)===false){
		document.getElementById("profilBlockError").innerHTML = "address must contain only letters, numbers and space";
		return false;
	}
	return true;
}

function displayNotification(msg){
	document.getElementById("messageDiv").innerHTML = ''+
			'<div class="message">'+
				'<button class="message-cancel-btn" onclick="closeNotification()">X</button>'+
				'<p class="message-title">Notification</p>'+
				'<p class="message-body">'+
					msg+
				'</p>'+
			'</div>';
	document.getElementById("messageDiv").style.display = "flex";
}

function closeNotification(){
	document.getElementById("messageDiv").style.display = "none";
	document.getElementById("messageDiv").innerHTML = "";
}

function displayUserProfil(id_user){
	/*make an ajax call, give the id_user and take the information about it.
	 then, display it to the page that called it(page).*/
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			var res = JSON.parse(this.responseText);
			document.getElementById("userProfilForm").innerHTML = ''+
			'<div class="user-profil">'+
				'<button class="user-profil-cancel-btn" onclick="closeUserProfil()">X</button>'+
				'<p class="user-profil-title">User\'s Info</p>'+
				'<p class="user-profil-label">Role</p><input type="text" readonly class="user-profil-input" value="'+res["role"]+'">'+
				'<p class="user-profil-label">Username</p><input type="text" readonly class="user-profil-input" value="'+res["username"]+'">'+
				'<p class="user-profil-label">Name</p><input type="text" readonly class="user-profil-input" value="'+res["name"]+'">'+
				'<p class="user-profil-label">Lastname</p><input type="text" readonly class="user-profil-input" value="'+res["lastname"]+'">'+
				'<p class="user-profil-label">E-mail</p><input type="text" readonly class="user-profil-input" value="'+res["email"]+'">'+
				'<p class="user-profil-label">Phone</p><input type="text" readonly class="user-profil-input" value="'+res["phone"]+'">'+
				'<p class="user-profil-label">Address</p><input type="text" readonly class="user-profil-input" value="'+res["address"]+'">'+
				'<p class="user-profil-label">City</p><input type="text" readonly class="user-profil-input" value="'+res["city"]+'">'+
				'<input type="submit" form="deleteUserForm" class="user-profil-delete-btn" value="Delete User">'+
				'<form method="POST" action="users.php" id="deleteUserForm">'+
					'<input type="hidden" name="id_user" value="'+id_user+'">'+
					'<input type="hidden" name="deleteUser" value="true">'+
					'<input type="hidden" name="username" value="'+res["username"]+'">'+
				'</form>'+
			'</div>';
			document.getElementById("userProfilForm").style.display = "flex";
		}
	}
	xhttp.open("GET","user-info.php?id_user="+id_user,true);
	xhttp.send();
}

function closeUserProfil(){
	document.getElementById("userProfilForm").style.display = "none";
	document.getElementById("userProfilForm").innerHTML = '';
}