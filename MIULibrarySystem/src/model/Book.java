package model;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private LocalDate borrowDay;
    private String title;
    private String isbnNumber;
    private List<Author> authorList;
    private List<BookCopy> bookCopyList;
}