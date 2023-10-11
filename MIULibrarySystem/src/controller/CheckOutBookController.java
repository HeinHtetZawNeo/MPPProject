package controller;

import java.util.HashMap;

import dataAccess.LibraryMemberDao;
import exception.LibrarySystemException;
import model.LibraryMember;

public class CheckOutBookController {

	public LibraryMember checkMember(String text) throws LibrarySystemException {
		if(text=="") throw new LibrarySystemException("Enter valid memberID");
		LibraryMemberDao libraryMemberDao = new LibraryMemberDao();
		HashMap<String, LibraryMember> libraryMemberHashMap = libraryMemberDao.getAllLibraryMembers();
		
		if(libraryMemberHashMap.get(text) == null) {
			throw new LibrarySystemException("Enter valid memberID");
		}
		return libraryMemberHashMap.get(text);
	}

}
