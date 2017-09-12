package com.purchases.entyties;

public class ProductWrapper {
	
	private Product[] products;

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ClassPojo [products = " + products + "]";
	}
}
