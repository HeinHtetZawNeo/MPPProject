package dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.*;

public class InitTestData {

	public static void main(String[] args) {
		InitTestData itd = new InitTestData();
		itd.createFiles();
		itd.addLoginUser();
//		itd.addBooks();
//		itd.addLibraryMember();
//		itd.addAuthor();
		
		//testdata
//		LibraryMemberDao lmd = new LibraryMemberDao();
//		HashMap<String, LibraryMember> allMember = lmd.getAllLibraryMembers();
//		LibraryMember member = allMember.get("0001");
//		CheckoutRecord cr = member.getCheckoutRecord().get(0);
//		cr.getCheckoutEntries().get(0).setDueDate(cr.getCheckoutEntries().get(0).getDueDate().minusDays(30));
//		System.out.println(cr.getCheckoutEntries().get(0).getDueDate());
//		member.getCheckoutRecord().clear();
//		member.getCheckoutRecord().add(cr);
//		allMember.put(member.getMemberNumber(), member);
//		
//		System.out.println(allMember.get("0001").getCheckoutRecord().get(0).getCheckoutEntries().get(0).getDueDate());
//		lmd.updateLibraryMembers(allMember);
	}

	private void createFiles() {
		AuthorDao adao = new AuthorDao();
		HashMap<String, Author> authMap = new HashMap<String, Author>();
		adao.updateAllAuthorHashMap(authMap);
		
		BookDao bdao = new BookDao();
		HashMap<String, Book> bookMap = new HashMap<String, Book>();
		bdao.updateBookHashMap(bookMap);
		
		LibraryMemberDao mdao = new LibraryMemberDao();
		HashMap<String, LibraryMember> mMap = new HashMap<String, LibraryMember>();
		mdao.updateLibraryMembers(mMap);
	}

	private void addAuthor() {
		HashMap<String, Author> authMap = new HashMap<String, Author>();
		Author a1 = new Author("A_FirstName", "A_LastName", "PhoneNumber",
				new Address("Street", "City", "State", "Zip"), false, "Short BIO");
		Author a2 = new Author("A_FirstName1", "A_LastName1", "PhoneNumber",
				new Address("Street", "City", "State", "Zip"), false, "Short BIO");

		authMap.put(a1.getFirstName() + " " + a1.getLastName(), a1);
		authMap.put(a2.getFirstName() + " " + a2.getLastName(), a2);
		
		AuthorDao authorDao = new AuthorDao();
		authorDao.updateAllAuthorHashMap(authMap);
	}

	private void addLibraryMember() {

		HashMap<String, LibraryMember> memberHashMap = new HashMap<String, LibraryMember>();
		LibraryMember member1 = new LibraryMember("First Name123", "Last Name123", "123-123-123",
				new Address("Street", "City", "State", "Zip"), "10001");
		memberHashMap.put(member1.getMemberNumber(), member1);

		LibraryMemberDao libraryMemberDao = new LibraryMemberDao();
		libraryMemberDao.updateLibraryMembers(memberHashMap);
	}

	private void addLoginUser() {
		LoginUser user1 = new Admin("admin", "admin");
		LoginUser user2 = new Admin("admin2", "admin2");
		LoginUser user3 = new Librarian("lib1", "lib1");
		LoginUser user4 = new Librarian("lib2", "lib2");
		LoginUser user5 = new SuperUser("super", "super");

		HashMap<String, LoginUser> tempHashMap = new HashMap<String, LoginUser>();
		tempHashMap.put(user1.getUserName(), user1);
		tempHashMap.put(user2.getUserName(), user2);
		tempHashMap.put(user3.getUserName(), user3);
		tempHashMap.put(user4.getUserName(), user4);
		tempHashMap.put(user5.getUserName(), user5);

		LoginUserDao mydao = new LoginUserDao();
		mydao.updateLoginUsers(tempHashMap);
	}

	private void addBooks() {
		List<Author> auList = new ArrayList<Author>();
		auList.add(new Author("A_FirstName", "A_LastName", "PhoneNumber", new Address("Street", "City", "State", "Zip"),
				false, "Short BIO"));

		auList.add(new Author("A_FirstName2", "A_LastName2", "PhoneNumber",
				new Address("Street", "City", "State", "Zip"), false, "Short BIO"));

		HashMap<String, Book> bookHashMap = new HashMap<String, Book>();
		bookHashMap.put("isbn", new Book(7, "Title", "isbn", auList, 1));
		bookHashMap.put("isbn1", new Book(7, "Title", "isbn1", auList, 3));
		bookHashMap.put("isbn2", new Book(21, "Title", "isbn2", auList, 3));
		bookHashMap.put("isbn3", new Book(21, "Title", "isbn3", auList, 3));

		BookDao mydao = new BookDao();
		mydao.updateBookHashMap(bookHashMap);

	}
}
