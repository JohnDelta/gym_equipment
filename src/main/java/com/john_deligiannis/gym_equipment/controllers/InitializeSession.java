package com.john_deligiannis.gym_equipment.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.john_deligiannis.gym_equipment.queries.Queries;

public class InitializeSession {
	
	public void init(HttpSession session) {
		session.setAttribute("username", "Guest");
		session.setAttribute("role", 0);
		initCart(session);
		initPaginationVars(session);
	}
	
	public void initCart(HttpSession session) {
		session.setAttribute("cart", new HashMap<Long, Long>());
		session.setAttribute("total", 0.0);
	}
	
	public void initPaginationVars(HttpSession session) {
		Long totalNumberOfProducts = Queries.getNumberOfProducts();
		Integer numberOfPages = (int) (totalNumberOfProducts / 6);
		numberOfPages += (totalNumberOfProducts % 6 > 0) ? 1 : 0;
		session.setAttribute("numberOfPages", numberOfPages);
		session.setAttribute("productsPage", 0);
	}

}
