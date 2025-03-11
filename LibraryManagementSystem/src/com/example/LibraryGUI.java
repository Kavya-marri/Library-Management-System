package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class LibraryGUI extends Application {
    private TableView<Book> bookTable = new TableView<>();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());

        TableColumn<Book, Boolean> availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(data -> data.getValue().availableProperty().asObject());

        bookTable.getColumns().addAll(titleColumn, authorColumn, availableColumn);

        Button borrowButton = new Button("Borrow Book");
        borrowButton.setOnAction(e -> borrowBook());

        Button returnButton = new Button("Return Book");
        returnButton.setOnAction(e -> returnBook());

        loadBooks();

        VBox vbox = new VBox(10, bookTable, borrowButton, returnButton);
        primaryStage.setScene(new Scene(vbox, 600, 400));
        primaryStage.show();
    }

    private void loadBooks() {
        bookList.clear();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                bookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }
            bookTable.setItems(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void borrowBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null && selectedBook.isAvailable()) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE books SET available = FALSE WHERE title = ?")) {
                stmt.setString(1, selectedBook.getTitle());
                stmt.executeUpdate();
                selectedBook.setAvailable(false);
                bookTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void returnBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null && !selectedBook.isAvailable()) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE books SET available = TRUE WHERE title = ?")) {
                stmt.setString(1, selectedBook.getTitle());
                stmt.executeUpdate();
                selectedBook.setAvailable(true);
                bookTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
