package model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private BookCopy checkedOutbook;
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public BookCopy getCheckedOutbook() {
		return checkedOutbook;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public void setCheckedOutbook(BookCopy checkedOutbook) {
		this.checkedOutbook = checkedOutbook;
	}
	@Override
	public String toString() {
		return "CheckoutEntry [checkOutDate=" + checkOutDate + ", dueDate=" + dueDate + ", checkedOutbook="
				+ checkedOutbook + "]";
	}
    
    
}