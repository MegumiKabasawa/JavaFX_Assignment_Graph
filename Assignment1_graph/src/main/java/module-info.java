module com.example.assignment1_extra {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assignment1_extra to javafx.fxml;
    exports com.example.assignment1_extra;
}