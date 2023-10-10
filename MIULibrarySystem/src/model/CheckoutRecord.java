package model;

import java.time.LocalDate;
import java.util.List;

public class CheckoutRecord {
    private double totalFine;
    private LocalDate datePaid;
    private List<CheckoutEntry> checkoutEntries;
    {
    	totalFine = 0.0;
    	datePaid = null;
    }
}