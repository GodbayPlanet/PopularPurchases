package com.purchases.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purchases.entyties.User;
import com.purchases.entyties.UserWrapper;
import com.purchases.util.Utill;

public class UserDAOImpl implements UserDAO {
	
	private static final String URL = "http://localhost:8000/api/users";

	/**
	 * Returns list of users from JSON file on specified URL address. 
	 */
	@Override
	public List<User> getAllUsers() throws IOException {
		UserWrapper userWrapper = getWrapper();
		ArrayList<User> listOfUsers = new ArrayList<>();
		User[] users = userWrapper.getUsers();
		
		for(User user : users) {
			listOfUsers.add(user);
		}
		return listOfUsers;
	}
	
	/**
	 * Return User object for specified userName.
	 */
	@Override
	public User getUserByName(String userName) throws IOException {
		List<User> listOfUsers = getAllUsers();
		User user = new User();
		
		listOfUsers.forEach(item -> {
			if (item.getUsername().equals(userName)) {
				user.setEmail(item.getEmail());
				user.setUsername(item.getUsername());
			}
		});
		return user;
	}
	
	/**
	 * Returns UserWrapper object that holds array of User objects.
	 * @return
	 * @throws IOException
	 */
	public UserWrapper getWrapper() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserWrapper userWrapper = mapper.readValue(Utill.getData(Utill.getUrl(URL)), UserWrapper.class);
		return userWrapper;
	}
	
	/**
	 * Method checks if user with specified user name exists.
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	public boolean isUserRegistred(String userName) throws IOException {
		User user = getUserByName(userName);
		if (user.getUsername().equals("") && user.getEmail().equals(""))
			return false;
		else 
			return true;
	}

}
