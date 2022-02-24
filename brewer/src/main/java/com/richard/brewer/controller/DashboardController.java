package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.repository.Beers;
import com.richard.brewer.repository.Clients;
import com.richard.brewer.repository.Sales;

@Controller
public class DashboardController {
	
	@Autowired
	private Sales sales;
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private Clients clients;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboard");
		
		mv.addObject("salesTotalValueYear", sales.totalValueYear());
		mv.addObject("salesTotalValueMonth", sales.totalValueMonth());
		mv.addObject("tickedValue", sales.tickedValue());
		
		mv.addObject("stockValueItems", beers.valueItemsStock());
		mv.addObject("totalClients", clients.count());
		
		return mv;
	}

}
