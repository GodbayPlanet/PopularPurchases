package com.purchases.entyties;

public class UserWrapper {
	
	private User[] users;

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "ClassPojo [users = " + users + "]";
	}
}
