package com.k.springmvc.demo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k.springmvc.common.response.Response;
import com.k.springmvc.demo.service.GeneralService;

@Controller
public class GeneralController {
	
	@Resource
	private GeneralService generalService;

	@RequestMapping(value="index")
	public Response index_jsp(){
		Response response = new Response();
		List<Map<String, Object>> resultList = generalService.queryTest();
		response.setViewName("index");
		response.addObject("resultList", resultList);
		return response;
	}
	
}

