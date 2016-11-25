package com.k.shiro.system.filter;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("shiroCredentialsMatcher")
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroCredentialsMatcher.class);
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		
		LOGGER.debug("Enter doCredentialsMatch()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));
        Object accountCredentials = getCredentials(info);
        LOGGER.debug("Exit doCredentialsMatch()");
        return equals(tokenCredentials, accountCredentials);
    }

    public String encrypt(String data) {
    	LOGGER.debug("Enter encrypt()");
        String sha384Hex = new Sha384Hash(data).toBase64();
        LOGGER.debug("Exit encrypt()");
        return sha384Hex;
    }
}
