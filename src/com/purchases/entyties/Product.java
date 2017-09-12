package com.purchases.entyties;

public class Product {

	private int id;
	private String face;
	private int price;
	private int size;

	public Product() {
	}

	public Product(int id, String face, int price, int size) {
		this.id = id;
		this.face = face;
		this.price = price;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", face=" + face + ", price=" + price + ", size=" + size + "]";
	}
	
}
