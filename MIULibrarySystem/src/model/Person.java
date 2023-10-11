package model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
	public Person(String firstName, String lastName, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + "]";
	}
    
    
}