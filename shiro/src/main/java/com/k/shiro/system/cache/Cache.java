package com.k.shiro.system.cache;

public enum Cache {

	PERMISSION_DATA_CACHE("permissionDataCache"),

	;

	private final String cacheName;

	private Cache(final String cacheName) {
		this.cacheName = cacheName;
	}

	public String getCacheName() {
		return cacheName;
	}
}