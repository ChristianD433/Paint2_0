module com.example.paint1_0 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.paint1_0 to javafx.fxml;
    exports com.example.paint1_0;
}