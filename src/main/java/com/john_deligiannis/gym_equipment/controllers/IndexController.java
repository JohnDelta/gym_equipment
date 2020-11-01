package com.john_deligiannis.gym_equipment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView showDefault() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("offers", Queries.loadOffers());
        mv.setViewName("index");
        
        return mv; 
    }
	
}
