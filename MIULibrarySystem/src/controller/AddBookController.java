package controller;

import java.util.HashMap;

import dataAccess.BookDao;
import exception.LibrarySystemException;
import model.Book;

public class AddBookController {

	public void addbook(Book book1) throws LibrarySystemException {

		BookDao bookdao = new BookDao();
		HashMap<String, Book> allbooks = bookdao.getAllBook();
		System.out.println(allbooks.size());
		if (allbooks.get(book1.getIsbnNumber()) != null)
			throw new LibrarySystemException("I am sorry, the book is already available in the collection");
		else
		{	
			allbooks.put(book1.getIsbnNumber(), book1);
			bookdao.updateBookHashMap(allbooks);
		
		}
		
	}

}
