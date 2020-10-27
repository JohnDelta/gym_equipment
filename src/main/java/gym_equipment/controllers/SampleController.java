package gym_equipment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@GetMapping("/sample")
    public ModelAndView showForm() {
		
		ModelAndView mv = new ModelAndView(); 
        mv.setViewName("sample.jsp"); 
        mv.addObject("result", 
                     "GeeksForGeeks Welcomes "
                         + "you to Spring!"); 
        return mv; 
    }
	
}
