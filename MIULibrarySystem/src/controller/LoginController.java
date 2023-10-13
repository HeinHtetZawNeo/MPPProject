package controller;

import java.util.HashMap;

import dataAccess.LoginUserDao;
import exception.LoginException;
import model.LoginUser;

public class LoginController {
	public LoginUser verifyUsernamePassword(String username, String password) throws LoginException {
		LoginUserDao user = new LoginUserDao();
		HashMap<String, LoginUser> usermap = user.getAllLoginUsers();
		if (usermap.get(username) == null)
			throw new LoginException("Invalid User");
		LoginUser usr = usermap.get(username);
		if (usr.getPassword().equals(password)) {
			return usr;
		}

		return null;
	}
}
