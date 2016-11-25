package com.k.shiro.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.k.shiro.common.model.Credential;
import com.k.shiro.common.sql.SqlTemplate;
import com.k.shiro.common.sql.TableInfo;
import com.k.shiro.common.util.BeanToMapUtil;
import com.k.shiro.system.service.CredentialService;
import com.k.shiro.system.sql.systemSql;

@Service
public class CredentialServiceImpl implements CredentialService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CredentialServiceImpl.class);
	
	@Resource
	private SqlTemplate sqlTemplate;

	/**
	 * @note 查询用户详细(基本信息+角色信息+权限信息)
	 * @author kuta
	 * @date 2016年11月22日 下午5:31:37
	 */
	@Override
	public Credential queryCredentialDetailByCredentailId(Integer credentialId) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("credentialId", credentialId);
		Credential credential = sqlTemplate.selectOne(TableInfo.OSS_CREDENTIALS, searchMap);
		if (credential != null) {
			final List<String> accessNameList = sqlTemplate.selectList(systemSql.SYSTEM_QUERYPERMISSIONNAMELIST, searchMap);
			final List<String> accessProfileDisplayNameList = sqlTemplate.selectList(systemSql.SYSTEM_QUERYROLENAMELIST, searchMap);
			credential.setAccessNameList(accessNameList);
			credential.setAccessProfileDisplayNameList(accessProfileDisplayNameList);
		} else {
			LOGGER.debug("The user is not existing.");
		}
		return credential;
	}

	/**
	 * @note 登陆查询 
	 * @author kuta
	 * @date 2016年11月22日 下午5:31:17
	 */
	@Override
	public Credential queryCredential4Login(String userId, String password) {
		if(userId == null || password == null){
			return null;
		}
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		searchMap.put("password", password);
		Map<String, Object> resultMap = sqlTemplate.selectOne(TableInfo.OSS_CREDENTIALS, searchMap);
		Credential credential = null;
		if(resultMap == null || resultMap.isEmpty()){
			return credential;
		}
		try {
			credential = (Credential) BeanToMapUtil.convertMap(Credential.class, resultMap);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		return credential;
	}

}
