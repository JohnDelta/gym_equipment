<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<main>

	<div class="message-div" id="messageDiv"></div>
	
	<div class="showcase-div">
		<h2 class="showcase-title">
			Welcome to Gym Equipment E-store
			<span>Here you can find the best gym equipment</span>
		</h2>
		<img class="showcase-img" src="style\showcasePhoto.jpg">
		<div class="showcase-create">
			<?PHP
				if($_SESSION["role"]=="3"){
							echo '<p>New to EComerse? click <a  class="create-btn" onclick="displayCreateAccount(\'\')">here</a> to create a new account!</p>';
						}
					?>
		</div>
	</div>
	
	<div class="section-div">
		<h3 class="section-title">
			Offers
		</h3>
		<hr class="section-title-hr">
		<div class="products">
		<?PHP
			/*
						Here you take the three offers - products from data base
					*/
					// find the offers
					$sql = "SELECT * FROM offers";
					$result = $conn->query($sql);
					if($result->num_rows > 0){
						while($row = $result->fetch_assoc()){
							// for every offer, find the product
							$sql2 = "SELECT * FROM product WHERE id_product = '".$row["id_product"]."'";
							$result2 = $conn->query($sql2);
							if($result2->num_rows > 0){
								while($row2 = $result2->fetch_assoc()){
									// display every offer - product
									echo '<div class="product">';
									echo '<img class="product-img" src="'.$row2["photo1"].'">';
									echo '<p class="product-title">'.$row2["title"].'</p>';
									echo '<p class="product-desc">'.$row2["description"].'</p>';
									echo '<p class="product-price">Price : '.$row2["price"].'&euro;<span style="font-size:14px;"> from '.$row["price"].'&euro;</span></p>';
									echo '<button class="product-btn" onclick="displayShowProduct(\''.$row["id_product"].'\',\'index.php\')">See product >><span class="arrow">></span></button>';
									echo '</div>';
								}
							}
						}
					}else{
						echo "No offers found!";
					}
					$conn->close();
				?>
		</div>
	</div>
	
	<div class="section-div">
		<h3 class="section-title">
			Features
		</h3>
		<hr class="section-title-hr">
		<div class="features">
			<div class="feature">
				<img src="style/shippingIcon.svg">
				<div class="feature-desc">
					<h4>Free Shipping</h4>
					<p>We provide a free and secure transfer of your product</p>
				</div>
			</div>
			<div class="feature">
				<img src="style/supportIcon.svg">
				<div class="feature-desc">
					<h4>Support service</h4>
					<p>If you have any trouble with your device, you can contact our support service team where our experienced employees will be there to help you.</p>
				</div>
			</div>
			<div class="feature">
				<img src="style/warrantyIcon.svg">
				<div class="feature-desc">
					<h4>2+ Years Warranty</h4>
					<p>We assure you that our products are in excellent condition and in the case of a defective one we provide immediately replacement</p>
				</div>
			</div>
		</div>
	</div>
</main>