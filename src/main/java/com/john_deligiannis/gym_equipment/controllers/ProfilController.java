package com.john_deligiannis.gym_equipment.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.entities.session.DetailedOrder;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class ProfilController {

	@RequestMapping(
			value = "/profil",
			method = RequestMethod.GET
	)
	public ModelAndView getProfil(
			HttpSession session,
			ModelMap model
	) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			Users user = Queries.loadUserByUsername(session.getAttribute("username").toString());
			if(user != null) {
				mv.addObject("USER", user);
				mv.addObject("LOAD_PANEL", "PROFIL");
			} else {
				return new ModelAndView("redirect:/", model);
			}
		
			List<DetailedOrder> detailedOrders = getDetailedOrders(user);
			if(!detailedOrders.isEmpty()) {
				mv.addObject("ORDERS", detailedOrders);
			}
		}
		
		mv.setViewName("index");
		
        return mv; 
	}
	
	@RequestMapping(value="/profil/update", method=RequestMethod.POST)
    public ModelAndView updateShoppingCart(
    		@RequestBody MultiValueMap<String, String> formData,
    		HttpSession session,
    		ModelMap model
    ) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			Users user = Queries.loadUserRawByUsername(session.getAttribute("username").toString());
			if(user == null) {
				return new ModelAndView("redirect:/", model);	
			}
		
			user.setName(formData.get("name").get(0));
			user.setLastname(formData.get("lastname").get(0));
			user.setEmail(formData.get("email").get(0));
			user.setPhone(formData.get("phone").get(0));
			user.setCity(formData.get("city").get(0));
			user.setAddress(formData.get("address").get(0));
			
			EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
	    	em.getTransaction().begin();
	    	em.merge(user);
	    	em.getTransaction().commit();
	    	em.close();
		}
        
		return new ModelAndView("redirect:/profil", model);
    }
	
	public List<DetailedOrder> getDetailedOrders(Users user) {
		
		List<DetailedOrder> detailedOrders = new ArrayList<>();
		
		List<Orders> orders = Queries.loadUsersOrders(user);
		for(Orders order: orders) {
			
			DetailedOrder detailedOrder = new DetailedOrder();
			detailedOrder.setStatus(order.getStatus());
			
			Double totalPrice = 0d;
			List<OrdersItems> items = Queries.loadUsersOrdersItems(order);
			for(OrdersItems item: items) {
				detailedOrder.getProducts().put(item.getProducts(), item.getQuantity());
				totalPrice += item.getQuantity() * item.getProducts().getPrice();
			}
			
			detailedOrder.setTotalPrice(totalPrice);
			detailedOrders.add(detailedOrder);
		}
		
		return detailedOrders;
	}
	
}





