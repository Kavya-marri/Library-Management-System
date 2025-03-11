module LibraryManagementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    opens com.example to javafx.fxml;
    exports com.example;
}
