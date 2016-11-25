package com.k.shiro.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.stereotype.Service;

import com.k.shiro.common.model.Credential;
import com.k.shiro.common.sql.SqlTemplate;
import com.k.shiro.common.sql.TableInfo;
import com.k.shiro.system.cache.Cache;
import com.k.shiro.system.cache.ShiroCacheManager;
import com.k.shiro.system.service.CredentialService;
import com.k.shiro.system.service.PermissionService;

/**
 * @author kuta
 * 权限serviceImpl
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private ShiroCacheManager shiroCacheManager;

	@Resource
	private CredentialService credentialService;

	@Resource
	private SqlTemplate sqlTemplate;

	public SimpleAuthorizationInfo updatePermissionCacheByCredentialsId(final int credentialsId) {
		final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		final Credential credential = credentialService.queryCredentialDetailByCredentailId(credentialsId);
		if (credential != null) {
			info.addRoles(credential.getAccessProfileDisplayNameList());
			info.addStringPermissions(credential.getAccessNameList());
		}
		shiroCacheManager.put(Cache.PERMISSION_DATA_CACHE, credentialsId, info);
		return info;
	}

	public void updatePermissionCacheByAccessProfileId(final int accessProfileId) {
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessProfileId", accessProfileId);
		final List<Map<String, Object>> credentialsAccessprofileXrefList = sqlTemplate.selectList(TableInfo.OSS_CREDENTIALSACCESSPROFILEXREF, paramMap);
		for (final Map<String, Object> credentialsAccessprofileXref : credentialsAccessprofileXrefList) {
			final int credentialsId = Integer.valueOf(credentialsAccessprofileXref.get("credentialsId").toString());
			updatePermissionCacheByCredentialsId(credentialsId);
		}
	}
}
