module com.example.filecompare {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filecompare to javafx.fxml;
    exports com.example.filecompare;
}