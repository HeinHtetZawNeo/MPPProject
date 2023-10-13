package controller;

import java.util.HashMap;

import dataAccess.AuthorDao;
import dataAccess.BookDao;
import exception.LibrarySystemException;
import model.Author;
import model.Book;

public class AddBookController {

	public void addbook(Book book) throws LibrarySystemException {

		BookDao bookdao = new BookDao();
		HashMap<String, Book> allbooks = bookdao.getAllBook();
		AuthorDao authordao = new AuthorDao();
		HashMap<String, Author> allAuthor = authordao.getAllAuthor();

		if (allbooks.get(book.getIsbnNumber()) != null)
			throw new LibrarySystemException("Book is already Existed");

		allbooks.put(book.getIsbnNumber(), book);
		bookdao.updateBookHashMap(allbooks);

		for(Author a:book.getAuthorList()) {
			allAuthor.put(a.getFirstName()+" "+a.getLastName(), a);
		}
		authordao.updateBookHashMap(allAuthor);
	}

}
