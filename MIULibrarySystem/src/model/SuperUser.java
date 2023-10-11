package model;

import java.io.Serializable;

public class SuperUser extends LoginUser implements Serializable {
	public SuperUser(String userName, String password) {
		super(userName, password);
	}

	private static final long serialVersionUID = 6110690276685962829L;

}
