package com.example;

import javafx.beans.property.*;

public class Book {
    private final StringProperty title;
    private final StringProperty author;
    private final BooleanProperty available;

    public Book(String title, String author, boolean available) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.available = new SimpleBooleanProperty(available);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }
}
