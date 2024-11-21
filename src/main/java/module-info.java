module com.example.tecexpocw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tecexpocw to javafx.fxml;
    exports com.example.tecexpocw;
}