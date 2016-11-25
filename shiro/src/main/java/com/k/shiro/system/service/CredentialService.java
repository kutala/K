package com.k.shiro.system.service;

import com.k.shiro.common.model.Credential;

/**
 * @author kuta
 * 用户service
 */
public interface CredentialService {

	public Credential queryCredentialDetailByCredentailId(Integer credentialId);
	
	public Credential queryCredential4Login(String userId, String password);


}
