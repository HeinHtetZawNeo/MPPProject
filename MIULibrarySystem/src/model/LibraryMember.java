package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.CheckoutRecord;

public class LibraryMember extends Person implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;

	private int memberNumber;
	private List<CheckoutRecord> checkoutRecord;

	public LibraryMember(String firstName, String lastName, String phoneNumber, Address address, int memberNumber) {
		super(firstName, lastName, phoneNumber, address);
		this.memberNumber = memberNumber;
		checkoutRecord = new ArrayList<CheckoutRecord>();
	}

	public void addCheckoutRecord(CheckoutRecord cr) {
		this.checkoutRecord.add(cr);
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public List<CheckoutRecord> getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public void setCheckoutRecord(List<CheckoutRecord> checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}

	@Override
	public String toString() {
		return "LibraryMember [memberNumber=" + memberNumber + ", checkoutRecord=" + checkoutRecord
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getAddress()=" + getAddress() + "]";
	}

}