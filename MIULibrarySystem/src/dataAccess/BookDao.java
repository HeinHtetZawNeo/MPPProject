package dataAccess;

import java.util.HashMap;

import model.Book;

public class BookDao {
	//key->isbn
	//value book
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> getAllBook() {
		return (HashMap<String, Book>) DataAccess.readFromStorage(StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Book> updateBookHashMap(HashMap<String, Book> booklist) {
		DataAccess.saveToStorage(StorageType.BOOKS, booklist);
		return (HashMap<String, Book>) DataAccess.readFromStorage(StorageType.BOOKS);
	}
}
