package com.k.shiro.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k.shiro.common.sql.SqlTemplate;
import com.k.shiro.demo.dao.GeneralDao;
import com.k.shiro.demo.service.GeneralService;
import com.k.shiro.demo.sql.generalSql;

@Service
public class GeneralServiceImpl implements GeneralService{
	
	@Resource
	private GeneralDao generalDao;
	
	@Resource
	private SqlTemplate sqlTemplate;

	@Override
	@Transactional(readOnly = false)
	public List<Map<String, Object>> queryTest() {
		return sqlTemplate.selectList(generalSql.GENEARL_QUERYTEST, null);
	}

}
