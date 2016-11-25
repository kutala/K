package com.k.shiro.system.sql;

import com.k.shiro.common.sql.ISql;

public enum systemSql implements ISql{
	SYSTEM_QUERYPERMISSIONNAMELIST("system.queryPermissionNameList"),
	SYSTEM_QUERYROLENAMELIST("system.queryRoleNameList")
	;

	private String value;
	
	private systemSql(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
