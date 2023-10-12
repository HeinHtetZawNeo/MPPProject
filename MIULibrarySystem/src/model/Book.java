package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;

	private int borrowDay;
	private String title;
	private String isbnNumber;
	private List<Author> authorList;
	private List<BookCopy> bookCopyList;

	public Book(int borrowDay, String title, String isbnNumber, List<Author> authorList, int quantity) {
		this.borrowDay = borrowDay;
		this.title = title;
		this.isbnNumber = isbnNumber;
		this.authorList = authorList;
		bookCopyList = new ArrayList<BookCopy>();
		for (int i = 1; i <= quantity; i++) {
			bookCopyList.add(new BookCopy(i, this));
		}
	}

	public void addbookcopy(int qty)// quantity
	{
		for (int i = 1; i <= qty; i++) {
			bookCopyList.add(new BookCopy(this.bookCopyList.size() + 1, this));
		}
	}

	public int getBorrowDay() {
		return borrowDay;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public List<BookCopy> getBookCopyList() {
		return bookCopyList;
	}

	public void setBorrowDay(int borrowDay) {
		this.borrowDay = borrowDay;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public void setBookCopyList(List<BookCopy> bookCopyList) {
		this.bookCopyList = bookCopyList;
	}

	@Override
	public String toString() {
		return "Book [borrowDay=" + borrowDay + "Quantity= " + bookCopyList.size() + ", title=" + title
				+ ", isbnNumber=" + isbnNumber + "]";
	}

}