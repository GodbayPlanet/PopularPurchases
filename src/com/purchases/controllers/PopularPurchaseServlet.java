package com.purchases.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.purchases.dao.UserDAOImpl;
import com.purchases.ehcache.PopularPurchasesServiceEhCache;
import com.purchases.entyties.RecentPurchase;
import com.purchases.entyties.User;

/**
 * Servlet implementation class PopularPurchaseServlet
 */
public class PopularPurchaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String action = request.getParameter("action");
		String url = "/popularPurchases.jsp";
		String message = "";
		
		if (action == null) {
			action = "recent_purchases"; // default action
		}

		if (action.equals("recent_purchases")) {
			String userName = request.getParameter("username");
			int limit = Integer.parseInt(request.getParameter("limit"));
			String prettyJSONObject = null;
			
			if (!isUserNameExists(userName)) {
				message = "User with usernme of " + userName + " was not found.";
			} else {
				message = "User recently purchased products, and names of other users who recently purchases them.";
				JSONObject recentPurchasesJSON = getJsonObject(getRecentPurchases(userName, limit));
				prettyJSONObject = getPrettyJSONORecentPurchasebject(recentPurchasesJSON);
			}
			request.setAttribute("prettyJSONObject", prettyJSONObject);
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} 
	}
	
	/**
	 * Method return String representation of the JSON object for recent purchases in a pretty format.
	 * @param recentPurchasesJSON
	 * @return
	 */
	public String getPrettyJSONORecentPurchasebject(JSONObject recentPurchasesJSON) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(recentPurchasesJSON.toString());
		String pretty = gson.toJson(jsonElement);
		return pretty;
	}
	
	/**
	 * Method return String representation of the JSON object for users in a pretty format.
	 * @param usersJSON
	 * @return
	 * @throws IOException 
	 * @throws JsonSyntaxException 
	 */
	public String getPrettyJSONOUsersObject() throws JsonSyntaxException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(getUsersJsonObject().toString());
		String prettyJSON = gson.toJson(jsonElement);
		return prettyJSON;
	}
	/**
	 * Method returns JSON object that contain all products that are purchased by some user, and
	 * the names of other users who recently purchased them.
	 * @param recentPurchases
	 * @return
	 */
	public JSONObject getJsonObject(List<RecentPurchase> recentPurchases) {
		JSONObject rootJsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		recentPurchases.forEach(recentPurchase -> {
			JSONObject newObject = new JSONObject();
			newObject.put("product", recentPurchase.getProduct()).put("recent", recentPurchase.getUserNames());
			jsonArray.put(newObject);
		});
		
		rootJsonObject.put("purchases", jsonArray);
		return rootJsonObject;
	}
	
	/**
	 * Return JSONObject object with all users.
	 * @param users
	 * @return
	 * @throws IOException 
	 */
	public JSONObject getUsersJsonObject() throws IOException {
		UserDAOImpl userDao = new UserDAOImpl();
		List<User> listOfUsers = userDao.getAllUsers();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		listOfUsers.forEach(user -> {
			JSONObject newObject = new JSONObject();
			newObject.put("user", user);
			jsonArray.put(newObject);
		});
		jsonObject.put("users", jsonArray);
		return jsonObject;
	}

	/**
	 * Method returns list of recent purchases of specified userName, and limit stand for max number of
	 * purchased products that will be displayed.
	 * @param userName
	 * @param limit
	 * @return
	 * @throws IOException
	 */
	public List<RecentPurchase> getRecentPurchases(String userName, int limit) throws IOException {
		PopularPurchasesServiceEhCache popularPurchasesService = new PopularPurchasesServiceEhCache();
		List<RecentPurchase> recentPurchases = popularPurchasesService.getRecentPurchases(userName, limit);
		return recentPurchases;
	}

	/**
	 * Method checks if userName exists in the list if all users.
	 * 
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	public boolean isUserNameExists(String userName) throws IOException {
		UserDAOImpl userDao = new UserDAOImpl();
		return userDao.isUserRegistred(userName) ? true : false;
	}

}
