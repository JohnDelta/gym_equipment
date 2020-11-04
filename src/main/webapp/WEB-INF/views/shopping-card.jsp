<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	
	String logLink = "<a class='user-bar-login-btn' href='products/login' >Login</a>";
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
		<div class="split-main">
		
			<div class="profil-div">
				<h3 class="section-title push-up-title">
				Profil
				<?PHP if($_SESSION["role"] == 3) 
								echo " - Order as unregistred user";
							else echo " - Order as ".$_SESSION["username"];
						?>
				</h3>
				<hr class="section-title-hr">
				<div class="profil-block">
				<?PHP
				//display users shopping cart - they can delete products from their cart or change the quantity
						//display users info
						/*
							if the user is not registed they have to fill all the input about the order.
							else their info is displayed in the field, and they can change only the destination fields
						*/
							if(isset($_SESSION["cart"])){
								echo '<p class="profil-block-title">Συμπληρώστε ή επεξεργαστείτε τα στοιχεία σας για την ολοκλήρωση της παραγγελίας</p>';
								echo '<p class="profil-block-error" id="profilBlockError"></p>';
							}else{
								echo '<p class="profil-block-title">Επιλέξτε κάποια προϊόν για να πραγματοποιείσετε την παραγγελία σας</p> </div>';
							}
						
							$username = "";
							$name = "";
							$lastname = "";
							$email = "";
							$phone = "";
							$city = "";
							$address = "";
							
							if($_SESSION["role"] != 3){
								//if the user is assigned , take his info and post it, else let him fill the fields
								$sql = "SELECT name,lastname,username,email,phone,city,address FROM users WHERE username = '".$_SESSION["username"]."'";
								$result = $conn -> query($sql);
								if($result -> num_rows > 0){
									if($row = $result -> fetch_assoc()){
										$username = $row["username"];
										$name = $row["name"];
										$lastname = $row["lastname"];
										$email = $row["email"];
										$phone = $row["phone"];
										$city = $row["city"];
										$address = $row["address"];
									}
								}
								if(isset($_SESSION["cart"])){
									echo '<p class="profil-label">Username</p><input form="sendOrderForm" id="username" class="profil-input" type="text" name="username" readonly value="'.$username.'">';
								}
							}
							if(isset($_SESSION["cart"]))
							{
						?>
					<p class="profil-label">Name</p><input id="name" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="name" <?PHP if($name!="") echo "readonly" ?> value="<?PHP if($name!="") echo $name ?>" required>
					<p class="profil-label">Lastname</p><input id="lastname" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="lastname" <?PHP if($lastname!="") echo "readonly" ?> value="<?PHP if($lastname!="") echo $lastname ?>" required>
					<p class="profil-label">E-mail</p><input id="email" form="sendOrderForm" maxlength="40" class="profil-input" type="email" name="email" <?PHP if($email!="") echo "readonly" ?> value="<?PHP if($email!="") echo $email ?>" required>
					<p class="profil-label">Phone</p><input id="phone" form="sendOrderForm" maxlength="10" min="6900000000" max="6999999999" class="profil-input" type="number" name="phone" value="<?PHP if($phone!="") echo $phone ?>" required>
					<p class="profil-label">City</p><input id="city" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="city" value="<?PHP if($city!="") echo $city ?>" required>
					<p class="profil-label">Address</p><input id="address" form="sendOrderForm" maxlength="40" class="profil-input" type="text" name="address" value="<?PHP if($address!="") echo $address ?>" required>
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
						<?PHP
							if(isset($_SESSION["cart"])){
										foreach($_SESSION["cart"] as $product){
											//first find the product price and title
											$title = "";
											$price = "";
											$max = 0;
											$localTotalPrice = 0;
											$sql = "SELECT title,price,quantity FROM product WHERE id_product = '".$product["id_product"]."'";
											$result = $conn -> query($sql);
											if($result -> num_rows > 0){
												if($row = $result -> fetch_assoc()){
													$title = $row["title"];
													$price = $row["price"];
													$max = $row["quantity"];
												}
											}
											//check if the product has an offer in price and update it
											$sql = "SELECT price FROM offers WHERE id_product = '".$product["id_product"]."'";
											$result = $conn -> query($sql);
											if($result -> num_rows > 0){
												if($row = $result -> fetch_assoc()){
													$price = $row["price"];
												}
											}
											$localTotalPrice = $product["quantity"] * $price;
											//display every product
											echo '<div class="shopping-cart-line">';
												echo '<p class="shopping-cart-line-label">'.$title.'</p>';
												echo '<form name="ds" method="POST" action="shopping-cart.php" id="shoppingCartRemove'.$product["id_product"].'">';
													echo '<input type="hidden" name="id_product" value="'.$product["id_product"].'">';
													echo '<input type="hidden" name="price" value="'.$localTotalPrice.'">';
													echo '<input class="shopping-cart-remove-btn" type="submit" form="shoppingCartRemove'.$product["id_product"].'" name="removeFromCart" value="X">';
												echo '</form>';
												echo '<div class="shopping-cart-quantity-div">';
													echo '<p class="shopping-cart-quantity-title">Quantity</p>';
													echo '<input class="shopping-cart-quantity-btn" type="button" value="-"  onclick="shoppingCartSubQuantity('.$product["id_product"].','.$price.')">';
													echo '<input form="shoppingCartRefreshForm" class="shopping-cart-quantity-number" readonly type="number" value="'.$product["quantity"].'" id="shoppingCartQuantity'.$product["id_product"].'" name="shoppingCartQuantity'.$product["id_product"].'">';
													echo '<input class="shopping-cart-quantity-btn" type="button" value="+" onclick="shoppingCartAddQuantity('.$product["id_product"].','.$max.','.$price.')">';
												echo '</div>';
												echo '<div>';
													echo '<p class="shopping-cart-line-price">Price : </p>';
													echo '<input class="shopping-cart-line-price-input" id="shoppingCartLinePrice'.$product["id_product"].'" type="number" value="'.$localTotalPrice.'" readonly>';
													echo '</form>';
												echo '</div>';
											echo '</div>';
										}
										echo '<form id="shoppingCartRefreshForm" method="POST" action="shopping-cart.php">';
											echo '<input type="hidden" name="shoppingCartRefresh" value="true">';
											echo '<input type="submit" class="shopping-cart-refresh-btn" value="Refresh Cart">';
										echo '</form>';
										echo '<p class="profil-label" style="margin:2px;">Τρόπος αποστολής</p>
											<select class="profil-input"><option>Αντικαταβολή</option></select>';
									}else{
										echo "Το καλάθι σας είναι άδειο!";
									}
								?>
						</div>
						<?PHP
							if(isset($_SESSION["cart"])){
										echo '<form method="POST" action="shopping-cart.php" id="sendOrderForm" onsubmit="return sendOrderFormValidate()">';
											echo '<p class="shopping-cart-total-price">Total Price : '.$_SESSION["total"].'&euro;</p>';
											echo '<input type="submit" class="shopping-cart-send-btn" value="Send Order">';
											echo '<input type="hidden" name="sendOrder" value="true">';
										echo '</form>';
									}
								?>
				</div>
			</div>
		</div>
	</div>
	
</main>



