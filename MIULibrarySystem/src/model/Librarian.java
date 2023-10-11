package model;

import java.io.Serializable;

public class Librarian extends LoginUser implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
	
	public Librarian(String userName, String password) {
		super(userName, password);
	}

	@Override
	public String toString() {
		return "Librarian [UserName=" + getUserName() + ", Password=" + getPassword() + "]";
	}
	
}