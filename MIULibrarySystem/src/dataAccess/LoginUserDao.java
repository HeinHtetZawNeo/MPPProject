package dataAccess;

import java.util.HashMap;

import model.LibraryMember;
import model.LoginUser;

//key->username
//value->LoginUser
public class LoginUserDao {
	public HashMap<String, LoginUser> getAllLibraryMembers() {
		return (HashMap<String, LoginUser>) DataAccess.readFromStorage(StorageType.LOGINUSER);
	}

	public HashMap<String, LoginUser> updateLibraryMembers(HashMap<String, LoginUser> loginUsers) {
		DataAccess.saveToStorage(StorageType.LOGINUSER, loginUsers);
		return (HashMap<String, LoginUser>) DataAccess.readFromStorage(StorageType.LOGINUSER);
	}
}
