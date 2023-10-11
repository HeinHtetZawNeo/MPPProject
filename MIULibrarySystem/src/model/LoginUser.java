package model;

import java.io.Serializable;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
    private String userName;
    private String password;

    public LoginUser(String userName,String password) {
    	this.userName = userName;
    	this.password = password;
    }

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginUser [userName=" + userName + ", password=" + password + "]";
	}
	
}