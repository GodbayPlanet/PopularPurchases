package com.purchases.ehcache;

import java.io.IOException;
import java.util.List;

import com.purchases.dao.PurchaseDAOImpl;
import com.purchases.entyties.RecentPurchase;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class PopularPurchasesServiceEhCache {
	
	public List<RecentPurchase> getRecentPurchases(String userName) throws IOException {
		
		List<RecentPurchase> recentPurchases = null;
		Cache cache = EHCacheManager.getCache();
		Element elementOfCache = cache.get(userName);
		
		if (elementOfCache == null) {
			System.out.println("cache miss");
			PurchaseDAOImpl purchaseDao = new PurchaseDAOImpl();
			recentPurchases = purchaseDao.getListOfRecentPurchase(userName);
			cache.put(new Element(userName, recentPurchases));
		} else {
			System.out.println("cache hit");
			Object object = elementOfCache.getObjectValue();
			if (object instanceof List<?>)
				recentPurchases = (List<RecentPurchase>) object;
		}
		return recentPurchases;
	}
}
