package com.k.shiro.system.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.k.shiro.common.model.Credential;
import com.k.shiro.system.cache.Cache;
import com.k.shiro.system.cache.ShiroCacheManager;
import com.k.shiro.system.service.CredentialService;
import com.k.shiro.system.service.PermissionService;

/**
 * Servlet Filter implementation class AuthorizationRealm
 */
@Service("authorizationRealm")
public class AuthorizationRealm extends AuthorizingRealm implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationRealm.class);

	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = "not ";

	@Resource(name = "shiroCredentialsMatcher")
	private CredentialsMatcher credentialsMatcher;// NOPMD

	@Resource
	private CredentialService credentialService;

	@Resource
	private ShiroCacheManager shiroCacheManager;

	@Resource
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principal) {
		if (principal == null) {
			LOGGER.debug("The principal is valid.");
			throw new AuthorizationException("The principal is valid.");
		}
		final Integer credentailId = (Integer) getAvailablePrincipal(principal);
		SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) shiroCacheManager.get(Cache.PERMISSION_DATA_CACHE, credentailId);
		if (info == null) {
			info = permissionService.updatePermissionCacheByCredentialsId(credentailId);
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken aToken)
			throws AuthenticationException {

		LOGGER.debug("Enter AuthorizationRealm.doGetAuthenticationInfo()");
		final UsernamePasswordToken token = (UsernamePasswordToken) aToken;
		final Integer credentailId = Integer.valueOf(token.getUsername());
		final Credential credential = credentialService.queryCredentialDetailByCredentailId(credentailId);

		if (credential == null) {
			LOGGER.debug("The user is not existing.");
			throw new AuthorizationException("The user is not existing.");
		} else {
			LOGGER.debug("Exit AuthorizationRealm.doGetAuthenticationInfo()");
			return new SimpleAuthenticationInfo(credentailId, credential.getPassword(), getName());
		}

	}

	/**
	 * @see AuthorizingRealm#AuthorizingRealm()
	 */
	public AuthorizationRealm() {
		super();
	}

	/**
	 * @see AuthorizingRealm#AuthorizingRealm(CacheManager)
	 */
	public AuthorizationRealm(final CacheManager cacheManager) {
		super(cacheManager);
	}

	/**
	 * @see AuthorizingRealm#AuthorizingRealm(CredentialsMatcher)
	 */
	public AuthorizationRealm(final CredentialsMatcher matcher) {
		super(matcher);
	}

	/**
	 * @see AuthorizingRealm#AuthorizingRealm(CacheManager, CredentialsMatcher)
	 */
	public AuthorizationRealm(final CacheManager cacheManager, final CredentialsMatcher matcher) {
		super(cacheManager, matcher);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
	}

	@Override
	public boolean isPermitted(final PrincipalCollection principals, final String permission) {
		if (permission.contains(OR_OPERATOR)) {
			final String[] permissions = permission.split(OR_OPERATOR);
			for (final String orPermission : permissions) {
				if (isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if (permission.contains(AND_OPERATOR)) {
			final String[] permissions = permission.split(AND_OPERATOR);
			for (final String orPermission : permissions) {
				if (!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(final PrincipalCollection principals, final String permission) {
		if (permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

}
