package com.k.shiro.common.sql;

public enum TableInfo {

	OSS_ACCESSPROFILE("oss_accessProfile"),
	OSS_CREDENTIALSACCESSPROFILEXREF("oss_credentialsAccessProfileXref"),
	OSS_CREDENTIALS("oss_credentialstest"),

	;

	private String tableName;

	private TableInfo(final String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

}
