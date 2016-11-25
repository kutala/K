package com.k.shiro.demo.controller;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Sha384Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.k.shiro.common.model.Credential;
import com.k.shiro.common.response.Response;
import com.k.shiro.demo.service.GeneralService;
import com.k.shiro.system.service.CredentialService;

@Controller
public class GeneralController {
	
	@Resource
	private GeneralService generalService;
	
	@Resource
	private CredentialService credentialService;

	@RequestMapping(value="index")
	public Response index(){
		Response response = new Response();
		response.setViewName("index");
		return response;
	}
	
	@RequestMapping(value="login")
	public Response login(@RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "password", required = true) String password){
		Credential credential = credentialService.queryCredential4Login(userId, new Sha384Hash(password).toBase64());
		System.out.println(credential.getUnSuccessfulAttempts());
		return null;
	}
}

