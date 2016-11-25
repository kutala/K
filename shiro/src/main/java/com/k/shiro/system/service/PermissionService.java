package com.k.shiro.system.service;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

/**
 * @author kuta
 * 权限service
 */
public interface PermissionService {


	public SimpleAuthorizationInfo updatePermissionCacheByCredentialsId(final int credentialsId);

	public void updatePermissionCacheByAccessProfileId(final int accessProfileId);

}
