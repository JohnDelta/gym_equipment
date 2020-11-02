package com.john_deligiannis.gym_equipment.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping(
			value = {"/logout"},
			method = RequestMethod.GET
	)
	public ModelAndView getLogin(
			ModelMap model,
			HttpSession session
	) {
		session.removeAttribute("username");
		session.removeAttribute("role");
		session.removeAttribute("total");
		session.removeAttribute("cart");
		return new ModelAndView("redirect:/", model);
	}
	
}
