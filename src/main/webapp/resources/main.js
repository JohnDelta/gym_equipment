
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

function closeUserProfil(){
	document.getElementById("userProfilForm").style.display = "none";
	document.getElementById("userProfilForm").innerHTML = '';
}