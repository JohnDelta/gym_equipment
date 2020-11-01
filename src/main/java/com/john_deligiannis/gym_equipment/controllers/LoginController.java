package com.john_deligiannis.gym_equipment.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class LoginController {
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.GET)
	public ModelAndView getLogin(@RequestParam(value = "fromPage", required = false) String fromPage) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("login", "true");
		mv.addObject("error", "");
		
		if(fromPage.isEmpty()) {
			mv.addObject("fromPage", "");
			mv.addObject("offers", Queries.loadOffers());
			mv.setViewName("index");
		} else { // here load login for other views later
			mv.addObject("fromPage", fromPage);
			mv.setViewName(fromPage);
		}
		
        return mv; 
		
	}

	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView postLogin(@RequestBody MultiValueMap<String, String> formData) {
		
		ModelAndView mv = new ModelAndView();
		
		String ans = formData.get("username").toString() + " " + formData.get("password").toString();
		
		mv.addObject("login", "true");
		mv.addObject("error", ans);
		
		if(formData.get("fromPage").isEmpty() || formData.get("formPage") == null) {
			mv.addObject("fromPage", "");
			mv.addObject("offers", Queries.loadOffers());
			mv.setViewName("index");
		} else { // here load login for other views later
			mv.addObject("fromPage", formData.get("fromPage"));
			mv.setViewName("");
		}
		
        return mv; 
		
	}
	
	
}
