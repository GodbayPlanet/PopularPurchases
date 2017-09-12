package com.purchases.entyties;

import java.io.Serializable;

public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int productId;
	private String username;
	private String date;

	public Purchase() {
	}

	public Purchase(int id, int productId, String userName, String date) {
		this.id = id;
		this.productId = productId;
		this.username = userName;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", productId=" + productId + ", username=" + username + ", date=" + date + "]";
	}

}
