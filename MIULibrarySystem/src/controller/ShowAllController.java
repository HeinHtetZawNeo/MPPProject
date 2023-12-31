package controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataAccess.BookDao;
import dataAccess.LibraryMemberDao;
import helper.Helper;
import model.Book;
import model.BookCopy;
import model.CheckoutEntry;
import model.CheckoutRecord;
import model.LibraryMember;

public class ShowAllController {

	public String[][] getAllBookForTable() {
		BookDao bookdao = new BookDao();
		HashMap<String, Book> allBookMap = bookdao.getAllBook();

		String[][] result = new String[allBookMap.size()][5];
		int i = 0;
		for (Map.Entry<String, Book> e : allBookMap.entrySet()) {
			result[i][0] = e.getValue().getIsbnNumber();
			result[i][1] = e.getValue().getTitle();
			result[i][2] = e.getValue().getBookCopyList().size() + "";
			result[i][3] = isAvailable(e.getValue(), "Available");
			result[i][4] = isAvailable(e.getValue(), "NotAvailable");
			i++;
		}

		return result;
	}

	public String isAvailable(Book book, String type) {
		int result = 0;
		for (BookCopy bc : book.getBookCopyList()) {
			if (bc.isAvailability() && type.equals("Available"))
				result++;
			else if ((!bc.isAvailability()) && type.equals("NotAvailable"))
				result++;
		}

		return result + "";
	}

	public String[][] getAllshowMemberDataForTable() {
		LibraryMemberDao lmdao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allMember = lmdao.getAllLibraryMembers();

		String[][] result = new String[allMember.size()][8];
		int i = 0;
		for (Map.Entry<String, LibraryMember> e : allMember.entrySet()) {
			result[i][0] = e.getValue().getMemberNumber();
			result[i][1] = e.getValue().getFirstName() + " " + e.getValue().getLastName();
			result[i][2] = e.getValue().getPhoneNumber();
			result[i][3] = e.getValue().getAddress().getStreet();
			result[i][4] = e.getValue().getAddress().getCity();
			result[i][5] = e.getValue().getAddress().getState();
			result[i][6] = e.getValue().getAddress().getZip();
			i++;
		}

		return result;
	}

	public LibraryMember getMemberDetails(String memberID) {
		LibraryMemberDao lmdao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allMember = lmdao.getAllLibraryMembers();
		return allMember.get(memberID);
	}

	public String[][] getMemberDetailForTable(String memberID) {
		LibraryMemberDao lmdao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allMember = lmdao.getAllLibraryMembers();

		LibraryMember member = allMember.get(memberID);
		String[][] result = new String[member.getCheckoutRecord().size()][5];
		int i = 0;
		for (CheckoutRecord cr : member.getCheckoutRecord()) {
			result[i][0] = cr.getCheckoutEntries().get(0).getCheckOutDate().toString();
			result[i][1] = cr.getTotalFine() + "";
			if (cr.getDatePaid() == null)
				result[i][2] = "N/A";
			else
				result[i][2] = cr.getDatePaid() + "";
			result[i][3] = cr.getCheckoutEntries().size() + "";
			result[i][4] = countOverDue(cr.getCheckoutEntries()) + "";
			i++;
		}

		return result;
	}

	private int countOverDue(List<CheckoutEntry> checkoutEntries) {
		int result = 0;
		for (CheckoutEntry ce : checkoutEntries) {
			if (Helper.dateDifferent(LocalDate.now(), ce.getDueDate()) < 0) {
				result++;
			}
		}
		return result;
	}

	public Book getBookByISBN(String isbn) {
		BookDao bookdao = new BookDao();

		return bookdao.getAllBook().get(isbn);
	}

	public Book getBookByTitle(String title) {
		BookDao bookdao = new BookDao();
		HashMap<String, Book> allBooks = bookdao.getAllBook();
		for (Map.Entry<String, Book> e : allBooks.entrySet()) {
			if (e.getValue().getTitle().equals(title))
				return e.getValue();
		}
		return null;
	}

	public HashMap<String, LibraryMember> getAllMember() {
		LibraryMemberDao lmdao = new LibraryMemberDao();
		return lmdao.getAllLibraryMembers();
	}

}
