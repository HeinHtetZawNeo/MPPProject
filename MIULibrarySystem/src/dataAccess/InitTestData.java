package dataAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;

public class InitTestData {

	public static void main(String[] args) {
		InitTestData itd = new InitTestData();
		itd.addLoginUser();
		itd.addBooks();
		itd.addLibraryMember();
	}

	private void addLibraryMember() {
		
		HashMap<String, LibraryMember> memberHashMap = new HashMap<String, LibraryMember>();
		LibraryMember member1 = new LibraryMember("First Name123", "Last Name123", "123-123-123", new Address("Street", "City", "State", "Zip"), "10001");
		memberHashMap.put(member1.getMemberNumber(), member1);
		
		LibraryMemberDao libraryMemberDao = new LibraryMemberDao();
		libraryMemberDao.updateLibraryMembers(memberHashMap);
	}

	private void addLoginUser() {
		LoginUser user1 = new Admin("admin","admin");
		LoginUser user2 = new Admin("admin2","admin2");
		LoginUser user3 = new Librarian("lib1","lib1");
		LoginUser user4 = new Librarian("lib2","lib2");
		
		
	}

	private void addBooks() {
		List<Author> auList = new ArrayList<Author>();
		auList.add(new Author("A_FirstName", "A_LastName", "PhoneNumber", new Address("Street", "City", "State", "Zip"),
				false, "Short BIO"));
		
		HashMap<String, Book> bookHashMap = new HashMap<String, Book>();
		bookHashMap.put("isbn", new Book(LocalDate.now(), "Title", "isbn", auList, 3));
		bookHashMap.put("isbn1", new Book(LocalDate.now(), "Title", "isbn", auList, 3));
		bookHashMap.put("isbn2", new Book(LocalDate.now(), "Title", "isbn", auList, 3));
		bookHashMap.put("isbn3", new Book(LocalDate.now(), "Title", "isbn", auList, 3));

		
		
	}
}
