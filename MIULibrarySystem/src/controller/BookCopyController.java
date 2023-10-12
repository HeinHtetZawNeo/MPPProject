package controller;

import java.util.HashMap;

import dataAccess.BookDao;
import exception.LibrarySystemException;
import model.Book;

public class BookCopyController {

	public void addbookcopy(String isbn, int qty) throws LibrarySystemException {
//		throw new LibrarySystemException("I am sorry, the book is already available in the collection");
		
		BookDao bookdao = new BookDao();
		HashMap<String, Book> allbooks = bookdao.getAllBook();
		System.out.println(allbooks.size());
		Book book1=	allbooks.get(isbn);

		if (book1 == null) throw new LibrarySystemException("Book is not existed");
		
		book1.addbookcopy(qty);
		
		bookdao.updateBookHashMap(allbooks);
		
	}
}