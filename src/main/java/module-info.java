module com.example.lectordocumentos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lectordocumentos to javafx.fxml;
    exports com.example.lectordocumentos;
}