package com.john_deligiannis.gym_equipment.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class InitializeSession {
	
	public void init(HttpSession session) {
		session.setAttribute("username", "Guest");
		session.setAttribute("total", "0");
		session.setAttribute("role", "0");
		session.setAttribute("cart", new HashMap<Long, Long>());
	}

}