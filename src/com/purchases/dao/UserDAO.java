package com.purchases.dao;

import java.io.IOException;
import java.util.List;

import com.purchases.entyties.User;

public interface UserDAO {
	
	/**
	 * Method returns List of all users data on the page: http://localhost:8000/api/users.
	 * @return
	 * @throws IOException
	 */
	public List<User> getAllUsers() throws IOException;
	
	public User getUserByName(String userName) throws IOException;
}
