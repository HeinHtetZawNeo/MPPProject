package dataAccess;

import java.util.HashMap;

import model.Author;

public class AuthorDao {
	//key -> FirstName + " " +LastName
	//Obj -> AuthorObject
	public HashMap<String, Author> getAllAuthor() {
		return (HashMap<String, Author>) DataAccess.readFromStorage(StorageType.AUTHOR);
	}

	public HashMap<String, Author> updateAllAuthorHashMap(HashMap<String, Author> authorList) {
		DataAccess.saveToStorage(StorageType.AUTHOR, authorList);
		return (HashMap<String, Author>) DataAccess.readFromStorage(StorageType.AUTHOR);
	}
}
