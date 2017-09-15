package com.purchases.dao;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.purchases.controllers.PopularPurchaseServlet;
import com.purchases.ehcache.PopularPurchasesServiceEhCache;
import com.purchases.entyties.Purchase;
import com.purchases.entyties.RecentPurchase;
import com.purchases.entyties.User;

public class Try {

	public static void main(String[] args) throws IOException {

		UserDAOImpl user = new UserDAOImpl();

		List<User> users = user.getAllUsers();

		users.forEach(i -> System.out.println(i));
		
		 System.out.println("--------------------");
//		 User u = user.getUserByName("Rene74");
//		 System.out.println(u);

//		ProductDAOImpl product = new ProductDAOImpl();
//
//		List<Product> products = product.getAllProducts();
//
//		products.forEach(i -> System.out.println(i));
//
//		System.out.println("------------------");

		// Product p = product.getProductById(14244);
		// System.out.println(p);

		PurchaseDAOImpl purc = new PurchaseDAOImpl();
		String userName = "Hadley.Dooley46";
		
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

		List<RecentPurchase> list = p.getListOfRecentPurchase(userName, 5);

		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			list.forEach(i -> System.out.println(i));
		
		PopularPurchasesServiceEhCache pp = new PopularPurchasesServiceEhCache();
		
		List<RecentPurchase> rp = pp.getRecentPurchases(userName, 5);

		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			rp.forEach(i -> System.out.println(i));
		
		List<RecentPurchase> rp1 = pp.getRecentPurchases(userName, 5);
		
		if (!user.isUserRegistred(userName))
			System.out.println("User with userName " + userName + " does not exists.");
		else
			rp1.forEach(i -> System.out.println(i));
		
		int limit = 5;
		PopularPurchaseServlet pppp = new PopularPurchaseServlet();
		
		JSONObject recentPurchasesJSON = pppp.getJsonObject(pppp.getRecentPurchases(userName, limit));
		
		String prettyJson = pppp.getPrettyJsonObject(recentPurchasesJSON);
		
		System.out.println(prettyJson);
		
//		try (FileWriter file = new FileWriter("d:\\test.json")) {
//			file.write(json.toString());
//			file.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//http://localhost:8000/api/purchases/by_user/Hadley.Dooley46
	}
}
