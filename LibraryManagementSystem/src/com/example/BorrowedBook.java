package com.example;

import javafx.beans.property.*;

public class BorrowedBook {
    private final StringProperty userName;
    private final StringProperty bookTitle;
    private final StringProperty borrowDate;
    private final StringProperty returnDate;

    public BorrowedBook(String userName, String bookTitle, String borrowDate, String returnDate) {
        this.userName = new SimpleStringProperty(userName);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.borrowDate = new SimpleStringProperty(borrowDate);
        this.returnDate = new SimpleStringProperty(returnDate);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public String getBorrowDate() {
        return borrowDate.get();
    }

    public StringProperty borrowDateProperty() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public StringProperty returnDateProperty() {
        return returnDate;
    }
}
