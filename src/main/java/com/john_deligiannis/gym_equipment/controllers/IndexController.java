package com.john_deligiannis.gym_equipment.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView showDefault(HttpSession session) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("OFFERS", Queries.loadOffers());
		mv.addObject("LOAD_PANEL", "MAIN");
        mv.setViewName("index");
        
        return mv; 
    }
	
}
