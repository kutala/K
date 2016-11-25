package com.k.shiro.common.sql;

public enum SqlTemplateSql implements ISql {

	SELECT_LAST_INSERT_ID("sqlTemplate.selectLastInsertId"),

	SELECT_FOUND_ROWS("sqlTemplate.selectFoundRows"),

	INSERT("sqlTemplate.insert"),

	INSERTList("sqlTemplate.insertList"),

	DELETE("sqlTemplate.delete"),

	UPDATE("sqlTemplate.update"),

	SELECT("sqlTemplate.select"),

	;
	private String value;

	private SqlTemplateSql(final String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
