package com.k.shiro.common.message;

public enum DefaultMessage implements Message {

	SUCCESS("success", "成功"),

	FAIL("fail", "失败");

	private String code;
	private String desc;

	private DefaultMessage(final String code, final String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

}
