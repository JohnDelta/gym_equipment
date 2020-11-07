package com.john_deligiannis.gym_equipment.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.dto.ProductsAndTheirOffer;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class ShoppingCartController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shopping-cart", method=RequestMethod.GET)
    public ModelAndView getShoppingCart(HttpSession session) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			Users user = Queries.loadUserByUsername(session.getAttribute("username").toString());
			if(user != null) {
				mv.addObject("USER", user);
			}
		}
		
		if(!((HashMap<Long, Long>) session.getAttribute("cart")).isEmpty()) {
			mv.addObject("CART", (HashMap<Long, Long>) session.getAttribute("cart"));
		}
		
		session.setAttribute("total", getTotalPrice((HashMap<Long, Long>) session.getAttribute("cart")));
		
		mv.addObject("LOAD_PANEL", "SHOPPING_CART");
        mv.setViewName("index");
        
        return mv; 
    }
	
	public double getTotalPrice(HashMap<Long, Long> cart) {
		
		double total = 0;
		
		for(Map.Entry<Long, Long> item: cart.entrySet()) {
			total += item.getValue();
		}
		
		return total;
		
	}
	
	public List<ShoppingCartDetailedItem> fillShoppingCartDetailed(HashMap<Long, Long> cart) {
		
		List<ShoppingCartDetailedItem> detailedCart = new ArrayList<>();
		
		for(Map.Entry<Long, Long> item: cart.entrySet()) {
			
			ProductsAndTheirOffer product = Queries.loadProductAndItsOffer(item.getKey());
			
			ShoppingCartDetailedItem detailedItem = new ShoppingCartDetailedItem();
			detailedItem.setQuantity(item.getValue());
			detailedItem.setTitle(product.getTitle());
			detailedItem.setProductsId(item.getKey());
			detailedItem.setMaxQuantity(product.getQuantity());
			
			if(product.getOfferPrice() != null) {
				detailedItem.setPrice(product.getOfferPrice());
			} else {
				detailedItem.setPrice(product.getPrice());
			}
			
			detailedCart.add(detailedItem);
		}
		
		return detailedCart;
	}
	
	class ShoppingCartDetailedItem {
		
		private long productsId;
		private String title;
		private long quantity;
		private long maxQuantity;
		private double price;
		
		public long getProductsId() {
			return productsId;
		}
		
		public void setProductsId(long productsId) {
			this.productsId = productsId;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public long getQuantity() {
			return quantity;
		}
		
		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}
		
		public long getMaxQuantity() {
			return maxQuantity;
		}
		
		public void setMaxQuantity(long maxQuantity) {
			this.maxQuantity = maxQuantity;
		}
		
		public double getPrice() {
			return price;
		}
		
		public void setPrice(double price) {
			this.price = price;
		}
		
	}
	
}

















