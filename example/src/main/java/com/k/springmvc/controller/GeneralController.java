package com.k.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

	@RequestMapping(value="index")
	public ModelAndView index_jsp(ModelAndView model){
		model.addObject("sayHi", "你好");
		model.setViewName("index");
		return model;
	}
	
}

