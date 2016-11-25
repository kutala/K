package com.k.shiro.system.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.ehcache.Element;

@Component
public class ShiroCacheManager {

	@Autowired(required = false)
	private EhCacheManager ehcacheManager;

	private final Map<String, Map<Object, Object>> caches = new HashMap<>();

	public Object get(final Cache cache, final Object key) {
		final String cacheName = cache.getCacheName();
		if (ehcacheManager == null) {
			final Map<Object, Object> cacheMap = caches.get(cacheName);
			return cacheMap == null ? null : cacheMap.get(key);
		} else {
			final Element element = ehcacheManager.getCacheManager().getCache(cacheName).get(key);
			return element == null ? null : element.getObjectValue();
		}
	}

	public void put(final Cache cache, final Object key, final Object value) {
		final String cacheName = cache.getCacheName();
		if (ehcacheManager == null) {
			Map<Object, Object> cacheMap = null;
			if (caches.containsKey(cacheName)) {
				cacheMap = caches.get(cacheName);
			} else {
				cacheMap = new HashMap<>();
			}
			cacheMap.put(key, value);
			caches.put(cacheName, cacheMap);
		} else {
			ehcacheManager.getCacheManager().getCache(cacheName).put(new Element(key, value));
		}
	}

}
