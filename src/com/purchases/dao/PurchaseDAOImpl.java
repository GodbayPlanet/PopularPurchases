package com.purchases.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purchases.entyties.Product;
import com.purchases.entyties.Purchase;
import com.purchases.entyties.PurchaseWrapper;
import com.purchases.entyties.RecentPurchase;
import com.purchases.entyties.User;
import com.purchases.util.Utill;

public class PurchaseDAOImpl implements PurchaseDAO {

	private static final String URL = "http://localhost:8000/api/purchases/by_user/";

	@Override
	public List<Purchase> getAllPurchases(String userName) throws IOException {
		
		PurchaseWrapper purchaseWrapper = getPurchaseWrapper(userName);
		ArrayList<Purchase> listOfPurchases = new ArrayList<>();

		Purchase[] purchases = purchaseWrapper.getPurchases();

		for (Purchase purchase : purchases) {
			listOfPurchases.add(purchase);
		}
		return listOfPurchases;
	}
	
	@Override
	public List<Product> getListOfProductsThatUserPurchase(String userName, int limit) throws IOException {
		List<Purchase> listOfPurchases = getAllPurchases(userName);
		if (listOfPurchases == null)
			return null;
		ArrayList<Product> listOfProducts = new ArrayList<>();
		ProductDAOImpl productDAO = new ProductDAOImpl();
		int count = 0;
		for (Purchase purchase : listOfPurchases) {

			Product product = null;
			try {
				product = productDAO.getProductById(purchase.getProductId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (product == null) {
				continue;
			} else {
				listOfProducts.add(product);
				count++;
			}

			if (count == limit)
				break;
		}
		return listOfProducts;
	}

	@Override
	public List<RecentPurchase> getListOfRecentPurchase(String userName, int limit) throws IOException {
		List<Product> listOfProducts = getListOfProductsThatUserPurchase(userName, limit);
		Map<User, List<Product>> usersProducts = getallUsersWithTheirPurchases(userName);
		List<RecentPurchase> listOfRecentPurchases = new ArrayList<>();

		for (Product product : listOfProducts) {
			List<String> userNames = new ArrayList<>();
			for (Map.Entry<User, List<Product>> entry : usersProducts.entrySet()) {

				for (Product p : entry.getValue()) {
					if (product.getId() == p.getId()) {
						userNames.add(entry.getKey().getUsername());
						break;
					}
				}
			}
			listOfRecentPurchases.add(new RecentPurchase(product, userNames));
		}
		listOfRecentPurchases = getSortedListOfRecentPurchases(listOfRecentPurchases);
		return listOfRecentPurchases;
	}

	/**
	 * Returns list of sorted recently purchases objects in way that product with the highest number
	 * of recent purchases is first.
	 * @param listOfRecentPurchases
	 * @return
	 */
	public List<RecentPurchase> getSortedListOfRecentPurchases(List<RecentPurchase> listOfRecentPurchases) {

		Collections.sort(listOfRecentPurchases, new Comparator<RecentPurchase>() {

			@Override
			public int compare(RecentPurchase recentPurchase1, RecentPurchase recentPurchase2) {
				if (recentPurchase1.getUserNames().size() > recentPurchase2.getUserNames().size())
					return -1;
				else if (recentPurchase1.getUserNames().size() < recentPurchase2.getUserNames().size())
					return 1;
				else
					return 0;
			}
		});
		return listOfRecentPurchases;
	}

	/**
	 * Returns map object that holds all users and their recently purchases. 
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	public Map<User, List<Product>> getallUsersWithTheirPurchases(String userName) throws IOException {
		Map<User, List<Product>> usersProducts = new HashMap<>();
		UserDAOImpl userDao = new UserDAOImpl();
		List<User> listOfUsers = userDao.getAllUsers();
		List<Product> listOfProducts = null;

		for (User user : listOfUsers) {
			if (user.getUsername().equals(userName)) {
				continue;
			} else {
				listOfProducts = getListOfProductsThatUserPurchase(user.getUsername(), 10);
				usersProducts.put(user, listOfProducts);
			}
		}
		return usersProducts;
	}

	/**
	 * Returns PurchaseWrapper object that holds array of purchase objects.
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	public PurchaseWrapper getPurchaseWrapper(String userName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		PurchaseWrapper purchaseWrapper = mapper.readValue(Utill.getData(Utill.getUrl(URL + userName)),
				PurchaseWrapper.class);

		return purchaseWrapper;
	}
	
}
