package com.k.springmvc.system.sql;

public enum TableInfo {

	OSS_ACCESSPROFILE("oss_accessProfile"),

	;

	private String tableName;

	private TableInfo(final String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

}
