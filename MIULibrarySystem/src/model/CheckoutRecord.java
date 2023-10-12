package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;
    private double totalFine;
    private LocalDate datePaid;
    private List<CheckoutEntry> checkoutEntries;
    {
    	totalFine = 0.0;
    	datePaid = null;
    }
	public CheckoutRecord() {
		super();
		this.checkoutEntries = new ArrayList<CheckoutEntry>();
	}
	public void addCheckOutEntry(CheckoutEntry ce) {
		checkoutEntries.add(ce);
	}
	public double getTotalFine() {
		return totalFine;
	}
	public LocalDate getDatePaid() {
		return datePaid;
	}
	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}
	public void setTotalFine(double totalFine) {
		this.totalFine = totalFine;
	}
	public void setDatePaid(LocalDate datePaid) {
		this.datePaid = datePaid;
	}
	@Override
	public String toString() {
		return "CheckoutRecord [totalFine=" + totalFine + ", datePaid=" + datePaid + ", checkoutEntries="
				+ checkoutEntries + "]";
	}
}