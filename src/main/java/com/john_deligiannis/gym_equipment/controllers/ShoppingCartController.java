package com.john_deligiannis.gym_equipment.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Orders;
import com.john_deligiannis.gym_equipment.entities.OrdersItems;
import com.john_deligiannis.gym_equipment.entities.Products;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer;
import com.john_deligiannis.gym_equipment.entities.session.ShoppingCartDetailedItem;
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
		
		List<ShoppingCartDetailedItem> detailedItems = new ArrayList<>();
		if(!((HashMap<Long, Long>) session.getAttribute("cart")).isEmpty()) {
			detailedItems = fillShoppingCartDetailed((HashMap<Long, Long>) session.getAttribute("cart"));
		}
		mv.addObject("CART", detailedItems);
		
		mv.addObject("LOAD_PANEL", "SHOPPING_CART");
        mv.setViewName("index");
        
        return mv; 
    }
	
	
	// Required form naming convention in order to distinguish each products id - quantity
	// Each row - item has a name productsQuantity_{id} with a value of {quantity}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shopping-cart/update", method=RequestMethod.POST)
    public ModelAndView updateShoppingCart(
    		@RequestBody MultiValueMap<String, String> formData,
    		HttpSession session,
    		ModelMap model
    ) {
		
		HashMap<Long, Long> cart = (HashMap<Long, Long>) session.getAttribute("cart");
		
		for(Entry<String, List<String>> item: formData.entrySet()) {
			
			Long id = Long.parseLong(item.getKey().split("_")[1].toString());
			Long quantity = Long.parseLong(item.getValue().get(0).toString());
			
			if(cart.containsKey(id)) {
				cart.put(id, quantity);
			}
		}
		
		session.setAttribute("total", calculateTotalPrice(cart));
		session.setAttribute("cart", cart);
        
		return new ModelAndView("redirect:/shopping-cart", model);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shopping-cart/remove-item", method=RequestMethod.POST)
    public ModelAndView remoteItemShoppingCart(
    		@RequestBody MultiValueMap<String, String> formData,
    		HttpSession session,
    		ModelMap model
    ) {
		
		HashMap<Long, Long> cart = (HashMap<Long, Long>) session.getAttribute("cart");
		
		Long id = Long.parseLong(formData.get("productsId").get(0).toString());
		if(cart.containsKey(id)) {
			cart.remove(id);
		}
		
		session.setAttribute("total", calculateTotalPrice(cart));
		session.setAttribute("cart", cart);
        
		return new ModelAndView("redirect:/shopping-cart", model);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shopping-cart/order", method=RequestMethod.POST)
    public ModelAndView orderShoppingCart(
    		@RequestBody MultiValueMap<String, String> formData,
    		HttpSession session,
    		ModelMap model
    ) {
		
		Users user = null;
		HashMap<Long, Long> cart = (HashMap<Long, Long>) session.getAttribute("cart");
		
		if(cart.isEmpty() || user == null) {
			new ModelAndView("redirect:/logout", model);
		}
		
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
    	em.getTransaction().begin();
		
    	String username = session.getAttribute("username").toString();
		if(username != null && !username.equals("Guest") && !username.isEmpty()) {
			user = Queries.loadUserByUsername(session.getAttribute("username").toString());
		} else {
			int r = new Random().nextInt(25);
			user = new Users();
			user.setUsername("Guest#"+r);
			user.setPassword("asdfzxcvASDF"+r);
			user.setEmail(formData.get("email").get(0));
			user.setName(formData.get("name").get(0));
			user.setLastname(formData.get("lastname").get(0));
			user.setCity(formData.get("city").get(0));
			user.setAddress(formData.get("address").get(0));
			user.setPhone(formData.get("phone").get(0));
			
			em.persist(user);
			em.flush();
	        em.clear();
		}
		
		Orders order = new Orders();
		order.setStatus("initial");
		order.setUsers(user);
		
		em.persist(order);
		em.flush();
        em.clear();
		
		boolean commit = true;
		for(Map.Entry<Long, Long> item: cart.entrySet()) {
			
			Products product = Queries.loadProduct(item.getKey());
			
			if(product.getQuantity() < item.getValue()) {
				commit = false;
				break;
			}
			
			OrdersItems orderItem = new OrdersItems();
			orderItem.setOrders(order);
			orderItem.setProducts(product);
			orderItem.setQuantity(item.getValue());
			
			em.persist(orderItem);
			em.flush();
	        em.clear();
		}
		
		if(commit) {
			em.getTransaction().commit();
		}
		em.close();
		
		new InitializeSession().initCart(session);
		return new ModelAndView("redirect:/shopping-cart", model);
    }
	
	public static Double calculateTotalPrice(HashMap<Long, Long> items) {
		Double totalPrice = 0d;
		
		for(Map.Entry<Long, Long> item: items.entrySet()) {
			ProductsAndTheirOffer product = Queries.loadProductAndItsOffer(item.getKey());
			Double price = product.getPrice();
			if(product.getOfferPrice() != null) {
				price = product.getOfferPrice();
			}
			totalPrice += price * item.getValue();	
		}
		
		return totalPrice;
	}
	
	public static List<ShoppingCartDetailedItem> fillShoppingCartDetailed(HashMap<Long, Long> cart) {
		
		List<ShoppingCartDetailedItem> detailedCart = new ArrayList<>();
		
		for(Map.Entry<Long, Long> item: cart.entrySet()) {
			
			ProductsAndTheirOffer product = Queries.loadProductAndItsOffer(item.getKey());
			
			ShoppingCartDetailedItem detailedItem = new ShoppingCartDetailedItem();
			detailedItem.setQuantity(item.getValue());
			detailedItem.setTitle(product.getTitle());
			detailedItem.setProductsId(item.getKey());
			detailedItem.setMaxQuantity(product.getQuantity());
			
			Double price = product.getPrice();
			if(product.getOfferPrice() != null) {
				price = product.getOfferPrice();
			}
			
			detailedItem.setPrice(price);
			detailedItem.setTotalPrice(price * item.getValue());
			
			detailedCart.add(detailedItem);
		}
		
		return detailedCart;
	}
	
}

















