module com.example.bai_tap_mahoa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.bai_tap_mahoa to javafx.fxml, com.google.gson;
    exports com.example.bai_tap_mahoa;
}