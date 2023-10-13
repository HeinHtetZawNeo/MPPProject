package controller;

import java.util.HashMap;

import dataAccess.LoginUserDao;
import exception.LoginException;
import model.LoginUser;

public class LoginController {
	public LoginUser verifyUsernamePassword (String username, char[]password) throws LoginException {
		LoginUserDao user = new LoginUserDao();
		HashMap<String,LoginUser> usermap =user.getAllLoginUsers();
		System.out.println(usermap.size());
		if(usermap.get(username)==null) {
			throw new LoginException("Invalid User");
		}
		LoginUser lguser=usermap.get(username);
		String passwrd = String.valueOf(password);
		if(!passwrd.equals(lguser.getPassword())) {
			throw new LoginException("Invalid User or Password");
		}
		return usermap.get(username);
	}
}
