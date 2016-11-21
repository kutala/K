package com.k.springmvc.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k.springmvc.demo.dao.GeneralDao;
import com.k.springmvc.demo.service.GeneralService;
import com.k.springmvc.demo.sql.generalSql;
import com.k.springmvc.system.sql.SqlTemplate;

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
