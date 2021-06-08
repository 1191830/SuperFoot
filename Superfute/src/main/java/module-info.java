module com.mycompany.superfute {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.superfute to javafx.fxml;
    exports com.mycompany.superfute;
}
