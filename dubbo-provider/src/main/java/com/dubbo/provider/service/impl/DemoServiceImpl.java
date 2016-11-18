package com.dubbo.provider.service.impl;

import org.springframework.stereotype.Service;

import com.dubbo.api.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	public String sayHi(String content) {
		return "service say" + content;
	}

}
