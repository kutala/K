package com.k.springmvc.demo.sql;

import com.k.springmvc.system.sql.ISql;

public enum generalSql implements ISql{
	GENEARL_QUERYTEST("general.queryTest")
	;

	private String value;
	
	private generalSql(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
