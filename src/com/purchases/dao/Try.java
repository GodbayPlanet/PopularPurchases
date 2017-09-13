package com.purchases.dao;

import java.io.IOException;
import java.util.List;

import com.purchases.ehcache.PopularPurchasesServiceEhCache;
import com.purchases.entyties.Product;
import com.purchases.entyties.Purchase;
import com.purchases.entyties.RecentPurchase;
import com.purchases.entyties.User;

public class Try {

	public static void main(String[] args) throws IOException {

		UserDAOImpl user = new UserDAOImpl();

		List<User> users = user.getAllUsers();

		users.forEach(i -> System.out.println(i));
		//
		// System.out.println("--------------------");
		// User u = user.getUserByName("Rene74");
		// System.out.println(u);

		ProductDAOImpl product = new ProductDAOImpl();

		List<Product> products = product.getAllProducts();

		products.forEach(i -> System.out.println(i));

		System.out.println("------------------");

		// Product p = product.getProductById(14244);
		// System.out.println(p);

		PurchaseDAOImpl purc = new PurchaseDAOImpl();
		String userName = "Laisha.Rohan";
		
		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else {
			List<Purchase> purchases = purc.getAllPurchases(userName);
			purchases.forEach(i -> System.out.println(i));
		}
		
		//
		// System.out.println("--------------------------------");
		//
		// ArrayList<Product> listOfProducts =
		// (ArrayList<Product>) purc.getListOfProductsThatUserPurchase("Dahlia_Grady46",
		// 5);
		//
		// listOfProducts.forEach(i -> System.out.println(i));

		PurchaseDAOImpl p = new PurchaseDAOImpl();

		List<RecentPurchase> list = p.getListOfRecentPurchase("Laisha.Rohan");

		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			list.forEach(i -> System.out.println(i));
		
		PopularPurchasesServiceEhCache pp = new PopularPurchasesServiceEhCache();
		
		List<RecentPurchase> rp = pp.getRecentPurchases("Laisha.Rohan");

		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			rp.forEach(i -> System.out.println(i));
		
		List<RecentPurchase> rp1 = pp.getRecentPurchases("Laisha.Rohan");
		
		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			rp1.forEach(i -> System.out.println(i));
		
	}
}
