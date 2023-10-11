package model;

import java.io.Serializable;

public class Author extends Person implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;

	private boolean credentials;
	private String shortBio;

	public Author(String firstName, String lastName, String phoneNumber, Address address, boolean credentials,
			String shortBio) {
		super(firstName, lastName, phoneNumber, address);
		this.credentials = credentials;
		this.shortBio = shortBio;
	}

	public boolean isCredentials() {
		return credentials;
	}

	public String getShortBio() {
		return shortBio;
	}

	public void setCredentials(boolean credentials) {
		this.credentials = credentials;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}

	@Override
	public String toString() {
		return "Author [credentials=" + credentials + ", shortBio=" + shortBio + ", FirstName=" + getFirstName()
				+ ", LastName=" + getLastName() + ", PhoneNumber=" + getPhoneNumber() + ", Address=" + getAddress()
				+ "]";
	}

}