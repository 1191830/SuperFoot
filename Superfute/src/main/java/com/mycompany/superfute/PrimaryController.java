package com.mycompany.superfute;

import com.mycompany.superfute.db.Dbconn;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class PrimaryController {

    @FXML
    private Button btnSQL;
    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void onActionbtnSQL(ActionEvent event) throws SQLException {
        
        
        Dbconn.getConn();
    }
}
