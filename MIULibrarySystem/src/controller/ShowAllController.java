package controller;

import java.util.HashMap;
import java.util.Map;

import dataAccess.BookDao;
import dataAccess.LibraryMemberDao;
import model.Book;
import model.BookCopy;
import model.LibraryMember;

public class ShowAllController {

	public String[][] getAllBookForTable() {
		BookDao bookdao = new BookDao();
		HashMap<String, Book> allBookMap = bookdao.getAllBook();

		String[][] result = new String[allBookMap.size()][4];
		int i = 0;
		for (Map.Entry<String, Book> e : allBookMap.entrySet()) {
			result[i][0] = e.getValue().getIsbnNumber();
			result[i][1] = e.getValue().getTitle();
			result[i][2] = e.getValue().getBookCopyList().size() + "";
			result[i][3] = isAvailable(e.getValue());
			i++;
		}

		return result;
	}

	private String isAvailable(Book book) {
		int avilableCount = 0;
		int notAvilableCount = 0;
		for (BookCopy bc : book.getBookCopyList()) {
			if (bc.isAvailability())
				avilableCount++;
			else
				notAvilableCount++;
		}

		return avilableCount + "|" + notAvilableCount;
	}

	public String[][] getAllshowMemberDataForTable() {
		LibraryMemberDao lmdao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allMember = lmdao.getAllLibraryMembers();

		String[][] result = new String[allMember.size()][7];
		int i = 0;
		for (Map.Entry<String, LibraryMember> e : allMember.entrySet()) {
			result[i][0] = e.getValue().getMemberNumber();
			result[i][1] = e.getValue().getFirstName() +" "+ e.getValue().getLastName();
			result[i][2] = e.getValue().getPhoneNumber();
			result[i][3] = e.getValue().getAddress().getStreet();
			result[i][4] = e.getValue().getAddress().getCity();
			result[i][5] = e.getValue().getAddress().getState();
			result[i][6] = e.getValue().getAddress().getZip();
			i++;
		}

		return result;
	}

}
