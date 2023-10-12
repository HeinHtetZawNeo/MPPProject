package controller;

import java.util.HashMap;

import dataAccess.LibraryMemberDao;
import model.LibraryMember;

public class AddMemberController {
	public void addMember(LibraryMember member) {
		LibraryMemberDao libMembDao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allmember = libMembDao.getAllLibraryMembers();
		allmember.put(member.getMemberNumber(), member);
		libMembDao.updateLibraryMembers(allmember);

	}

	public String generateMemberId() {
		LibraryMemberDao libMembDao = new LibraryMemberDao();
		HashMap<String, LibraryMember> allmember = libMembDao.getAllLibraryMembers();
		if(allmember==null) return "0001";
		return "000" + (allmember.size()+1);
	}

}
