package com.purchases.dao;

import java.io.IOException;
import java.util.List;

import com.purchases.entyties.Product;

public interface ProductDAO {
	
	/**
	 * Method returns List of all products data on the page: http://localhost:8000/api/products.
	 * @return
	 * @throws IOException
	 */
	public List<Product> getAllProducts() throws IOException;
	
	public Product getProductById(int productId) throws IOException;
}
