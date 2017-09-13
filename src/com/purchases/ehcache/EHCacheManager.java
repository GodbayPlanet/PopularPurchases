package com.purchases.ehcache;

import java.net.URL;

import net.sf.ehcache.*;

public class EHCacheManager {

	private static Cache recentPurchasesCache;

	private static EHCacheManager manager = new EHCacheManager();

	private EHCacheManager() {
		URL url = getClass().getResource("/config/ehcache.xml");
		CacheManager manager = new CacheManager(url);
		EHCacheManager.recentPurchasesCache = manager.getCache("recentPurchasesCache");
	}

	public static Cache getCache() {
		return EHCacheManager.recentPurchasesCache;
	}
}
