package com.john_deligiannis.gym_equipment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@RequestMapping("/sample")
    public ModelAndView showForm() {
		
		ModelAndView mv = new ModelAndView(); 
        mv.setViewName("sample.jsp"); 
        mv.addObject("result", 
                     "GeeksForGeeks Welcomes "
                         + "you to Spring!"); 
        return mv; 
    }
	
}
