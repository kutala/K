package com.k.shiro.common.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.k.shiro.common.message.DefaultMessage;
import com.k.shiro.common.message.Message;

public class Response extends ModelAndView {

	private final boolean success;
	private final String code;
	private final String desc;
	private final Object data;

	public Response() {
		this(DefaultMessage.SUCCESS);
	}

	public Response(final Message message) {
		this(message.getCode(), message.getDesc(), null);
		assert message != null;
	}

	private Response(final String code, final String desc, final Object data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
		success = DefaultMessage.SUCCESS.getCode().equals(code) || code.endsWith("success");
		setViewName("");
		addObject("response", toResponseMap());
	}

	public Response(final Message message, final Object data) {
		this(message.getCode(), message.getDesc(), data);
	}

	public Response(final Object data) {
		this(DefaultMessage.SUCCESS, data);
	}

	public boolean isSuccess() {
		return success;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public Object getData() {
		return data;
	}

	public Map<String, Object> toResponseMap() {
		final Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("success", isSuccess());
		responseMap.put("code", getCode());
		responseMap.put("desc", getDesc());
		responseMap.put("data", getData());
		return responseMap;
	}

}
