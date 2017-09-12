package com.purchases.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purchases.entyties.Product;
import com.purchases.entyties.ProductWrapper;
import com.purchases.util.Utill;

public class ProductDAOImpl implements ProductDAO {
	
	private static final String URL = "http://localhost:8000/api/products";

	/**
	 * Returns all products from JSON file on specified URL address.
	 */
	@Override
	public List<Product> getAllProducts() throws IOException {
		ProductWrapper productWrapper = getProductWrapper();
		ArrayList<Product> listOfProducts = new ArrayList<>();
		
		Product[] products = productWrapper.getProducts();
		for(Product product : products) {
			listOfProducts.add(product);
		}
		return listOfProducts;
	}

	/**
	 * Returns Product object for specified productId.
	 */
	@Override
	public Product getProductById(int productId) throws IOException {
		List<Product> listOfProducts = getAllProducts();
		Product product = new Product();
		
		listOfProducts.forEach(item -> {
			if (item.getId() == productId) {
				product.setId(productId);
				product.setFace(item.getFace());
				product.setPrice(item.getPrice());
				product.setSize(item.getSize());
			}
		});
		
		if (product.getId() == 0 && product.getFace() == null && product.getPrice() == 0 && product.getSize() == 0)
			return null;
		else
			return product;
	}

	/**
	 * Returns ProductWrapper object that holds array of products objects.
	 * @return
	 * @throws IOException
	 */
	public ProductWrapper getProductWrapper() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ProductWrapper productWrapper = mapper.readValue(Utill.getData(Utill.getUrl(URL)), ProductWrapper.class);
		return productWrapper;
	}
}
