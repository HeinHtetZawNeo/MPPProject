package model;

import java.io.Serializable;

public class BookCopy implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;

    private boolean availability;
    private int uniqueCopyNumber;
    private Book book;
    
    BookCopy(int uniqueCopyNumber,Book book){
    	this.availability = true;
    	this.uniqueCopyNumber = uniqueCopyNumber;
    	this.book = book;
    }

	public boolean isAvailability() {
		return availability;
	}

	public int getUniqueCopyNumber() {
		return uniqueCopyNumber;
	}

	public Book getBook() {
		return book;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setUniqueCopyNumber(int uniqueCopyNumber) {
		this.uniqueCopyNumber = uniqueCopyNumber;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookCopy [availability=" + availability + ", uniqueCopyNumber=" + uniqueCopyNumber + ", book=" + book
				+ "]";
	}
    
}