package model;

import java.io.Serializable;

public class Admin extends LoginUser implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;
	
	public Admin(String userName, String password) {
		super(userName, password);
	}

	@Override
	public String toString() {
		return "Admin [UserName=" + getUserName() + ", Password=" + getPassword() + "]";
	}


	
}