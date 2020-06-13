package com.saraha.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.saraha.demo.service.ThankingService;

@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class ThankingController {

	@Autowired
	private ThankingService thankingService;

	@GetMapping(value = "/send-message")
	public ModelAndView saveDataForMessageWithPerson(@RequestParam("code") String authorizationCode) {

		String page = thankingService.saveDataForMessageWithPerson(authorizationCode);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("code", authorizationCode);

		modelAndView.setViewName(page);

		return modelAndView;
	}
}