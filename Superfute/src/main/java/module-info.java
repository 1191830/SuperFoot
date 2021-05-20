module com.mycompany.superfute {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.superfute to javafx.fxml;
    exports com.mycompany.superfute;
}
