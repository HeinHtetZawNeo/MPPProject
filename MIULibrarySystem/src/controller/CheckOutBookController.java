package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataAccess.BookDao;
import dataAccess.LibraryMemberDao;
import exception.LibrarySystemException;
import model.Book;
import model.BookCopy;
import model.CheckoutEntry;
import model.CheckoutRecord;
import model.LibraryMember;

public class CheckOutBookController {

	public LibraryMember checkMember(String text) throws LibrarySystemException {
		if (text == "")
			throw new LibrarySystemException("Enter valid memberID");
		LibraryMemberDao libraryMemberDao = new LibraryMemberDao();
		HashMap<String, LibraryMember> libraryMemberHashMap = libraryMemberDao.getAllLibraryMembers();

		if (libraryMemberHashMap.get(text) == null) {
			throw new LibrarySystemException("Enter valid memberID");
		}
		return libraryMemberHashMap.get(text);
	}

	public Book searchBookByIsbn(String isbn) throws LibrarySystemException {
		BookDao bookDao = new BookDao();
		HashMap<String, Book> bookHashMap = bookDao.getAllBook();
		if (bookHashMap.get(isbn) == null)
			throw new LibrarySystemException("ISBN not found");
		return bookHashMap.get(isbn);
	}

	public Book searchBookByTitle(String title) throws LibrarySystemException {
		BookDao bookDao = new BookDao();
		HashMap<String, Book> bookHashMap = bookDao.getAllBook();
		Book result;
		for (Map.Entry<String, Book> entry : bookHashMap.entrySet()) {
			Book b = entry.getValue();
			if (b.getTitle().equals(title))
				return b;
		}
		throw new LibrarySystemException("Title not found");
	}

	public LibraryMember checkOut(LibraryMember member, List<BookCopy> bookCopyList) throws LibrarySystemException {
		LibraryMemberDao libraryMemberDao = new LibraryMemberDao();
		BookDao bookDao = new BookDao();

		HashMap<String, LibraryMember> libraryMemberHashMap = libraryMemberDao.getAllLibraryMembers();
		HashMap<String, Book> bookHashMap = bookDao.getAllBook();

		CheckoutRecord checkoutRecord = new CheckoutRecord();
		for (BookCopy bc : bookCopyList) {
			Book b = bookHashMap.get(bc.getBook().getIsbnNumber());

			for (BookCopy dbBC : b.getBookCopyList()) {
				if (dbBC.getUniqueCopyNumber() == bc.getUniqueCopyNumber()) {
					if (!dbBC.isAvailability()) {
						throw new LibrarySystemException(dbBC.getBook().getTitle() + " Copy is not available");
					} else {
						dbBC.setAvailability(false);

						CheckoutEntry ce = new CheckoutEntry(LocalDate.now(),
								LocalDateTime.now().plusDays(bc.getBook().getBorrowDay()).toLocalDate(), bc);
						checkoutRecord.addCheckOutEntry(ce);

						bookHashMap.put(dbBC.getBook().getIsbnNumber(), dbBC.getBook());
					}
				}
			}
		}
		member.addCheckoutRecord(checkoutRecord);
		libraryMemberHashMap.put(member.getMemberNumber(), member);
		libraryMemberDao.updateLibraryMembers(libraryMemberHashMap);
		bookDao.updateBookHashMap(bookHashMap);
		return member;
	}

	public BookCopy isBookAvailable(Book b) {
		BookDao bookDao = new BookDao();
		HashMap<String, Book> bookHashMap = bookDao.getAllBook();
		b = bookHashMap.get(b.getIsbnNumber());

		for (BookCopy bc : b.getBookCopyList()) {
			if (bc.isAvailability()) {
				return bc;
			}
		}
		return null;
	}

}
