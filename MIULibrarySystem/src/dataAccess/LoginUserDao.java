package dataAccess;

import java.util.HashMap;

import model.LibraryMember;
import model.LoginUser;

//key->username
//value->LoginUser
public class LoginUserDao {
	public HashMap<String, LoginUser> getAllLoginUsers() {
		return (HashMap<String, LoginUser>) DataAccess.readFromStorage(StorageType.LOGINUSER);
	}

	public HashMap<String, LoginUser> updateLoginUsers(HashMap<String, LoginUser> loginUsers) {
		DataAccess.saveToStorage(StorageType.LOGINUSER, loginUsers);
		return (HashMap<String, LoginUser>) DataAccess.readFromStorage(StorageType.LOGINUSER);
	}
}
