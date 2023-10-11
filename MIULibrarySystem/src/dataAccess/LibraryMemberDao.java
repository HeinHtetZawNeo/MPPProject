package dataAccess;

import java.util.HashMap;

import model.LibraryMember;

public class LibraryMemberDao {
	public HashMap<String, LibraryMember> getAllLibraryMembers() {
		return (HashMap<String, LibraryMember>) DataAccess.readFromStorage(StorageType.MEMBER);
	}

	public HashMap<String, LibraryMember> updateLibraryMembers(HashMap<String, LibraryMember> authorList) {
		DataAccess.saveToStorage(StorageType.MEMBER, authorList);
		return (HashMap<String, LibraryMember>) DataAccess.readFromStorage(StorageType.MEMBER);
	}
}
