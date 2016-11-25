package com.k.shiro.system.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.k.shiro.common.util.JSONUtils;


@Component("logInterceptor")
public class LogInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);
	
	private static final String RESPONSE = "response";

	@Override
	public void afterCompletion(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object method,
			final Exception exception) throws Exception {
		if (exception != null) {
			LOG.error("", exception);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object method,
			final ModelAndView modelAndView) throws Exception {
		if (method instanceof HandlerMethod) {
			final HandlerMethod handlerMethod = (HandlerMethod) method;
			final Logger log = LoggerFactory.getLogger(handlerMethod.getBean().getClass());
			log.debug("**************** Exit " + handlerMethod.getMethod().getName() + " **************** ");
		}
		if (modelAndView != null) {
			final Map<String, Object> model = modelAndView.getModel();
			if (model != null && !model.isEmpty() && model.containsKey(RESPONSE)) {
				final Map<String, Object> respMap = (Map<String, Object>) model.get(RESPONSE);
				respMap.put("desc", respMap.get("code").toString());
				final String responseJson = JSONUtils.toJSON(respMap);
				LOG.debug("response json : " + responseJson);
				modelAndView.addObject(RESPONSE, responseJson);
				if (modelAndView.getViewName() == null || "".equals(modelAndView.getViewName().trim())) {
					modelAndView.setViewName("ajaxData");
				}
			} else {
				LOG.debug("response model : " + model);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object method)
			throws Exception {
		if (method instanceof HandlerMethod) {
			final HandlerMethod handlerMethod = (HandlerMethod) method;
			final Logger log = LoggerFactory.getLogger(handlerMethod.getBean().getClass());
			log.debug("**************** Enter " + handlerMethod.getMethod().getName() + " **************** ");
			log.debug("**************** request parameter start **************** ");
			final Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				final String parameterName = parameterNames.nextElement();
				log.debug(parameterName + " : " + request.getParameter(parameterName));
			}
			log.debug("**************** request parameter end **************** ");
		}
		return true;
	}

}
