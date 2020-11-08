package com.john_deligiannis.gym_equipment.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.entities.Offers;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class OfferController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/offer",
			method = RequestMethod.GET
	)
	public ModelAndView getOffer(
			@RequestParam("offersId") Long offersId,
			HttpSession session
	) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("OFFERS", Queries.loadOffers());
		
		
		if(offersId != null) {
			
			Offers offer = Queries.loadOffer(offersId);
			HashMap<Long, Long> cart = (HashMap<Long, Long>) session.getAttribute("cart");
			
			if(cart.containsKey(offer.getProducts().getProductsId())) {
				offer.getProducts().setQuantity(offer.getProducts().getQuantity() - cart.get(offer.getProducts().getProductsId()));
			}
			
			mv.addObject("OFFER", offer);
		}
		
		mv.addObject("LOAD_PANEL", "MAIN");
		mv.setViewName("index");
		
        return mv; 
	}

}
