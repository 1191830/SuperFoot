/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnLigas;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnExtras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnLigas(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/liga.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/new.css");
        Stage window = new Stage();
        window.setTitle("Ligas");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private void btnSair(ActionEvent event) {
          Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnExtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/extras.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/new.css");
        Stage window = new Stage();
        window.setTitle("Extras");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        
        
    }
    
}
