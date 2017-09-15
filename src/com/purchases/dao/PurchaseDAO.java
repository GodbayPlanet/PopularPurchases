package com.purchases.dao;

import java.io.IOException;
import java.util.List;

import com.purchases.entyties.Product;
import com.purchases.entyties.Purchase;
import com.purchases.entyties.RecentPurchase;

public interface PurchaseDAO {

	/**
	 * Method returns List of all purchases data on the page:
	 * http://localhost:8000/api/purchases/by_user/username.
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Purchase> getAllPurchases(String userName) throws IOException;
	
	/**
	 * Returns list of products that user purchase, limit specifies max number of products
	 * that list can contain.
	 * @param userName
	 * @param limit
	 * @return
	 * @throws IOException
	 */
	public List<Product> getListOfProductsThatUserPurchase(String userName, int limit) throws IOException;
	
	/**
	 * Returns list of recent purchases that contain all information about products and list of all users 
	 * who recently also purchase same product.
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	public List<RecentPurchase> getListOfRecentPurchase(String userName, int limit) throws IOException;

}
