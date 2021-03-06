package com.purchases.entyties;

import java.util.List;

public class RecentPurchase {

	private Product product;
	private List<String> userNames;

	public RecentPurchase() {

	}

	public RecentPurchase(Product product, List<String> userNames) {
		this.product = product;
		this.userNames = userNames;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<String> getUserNames() {
		return userNames;
	}

	public void setUserNames(List<String> userNames) {
		this.userNames = userNames;
	}

	@Override
	public String toString() {
		if (userNames == null || userNames.isEmpty()) {
			userNames.add("[There is no one who "
					+ "recently purchase this product" + "]");
			return userNames.toString();
		}
		else
			return "[" + product.toString() + ", userNames=" + userNames.toString() + "]";
	}

}
