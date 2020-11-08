package com.john_deligiannis.gym_equipment.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView getProfil(HttpSession session) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			Users user = Queries.loadUserByUsername(session.getAttribute("username").toString());
			if(user != null) {
				mv.addObject("USER", user);
			}
		
			List<DetailedOrder> detailedOrders = getDetailedOrders(user);
			if(!detailedOrders.isEmpty()) {
				mv.addObject("ORDERS", detailedOrders);
			}
		}
		
		mv.setViewName("index");
		
        return mv; 
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





