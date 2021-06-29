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
 * @author pcoelho
 */
public class ExtrasController implements Initializable {

    @FXML
    private Button btnPais;
    @FXML
    private Button btnCidade;
    @FXML
    private Button btnEstadio;
    @FXML
    private Button btnVoltar;
    
     private Stage stageDialog;
    
    
     public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPais(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pais.fxml"));
        Scene scene = new Scene(root);
         Stage window = new Stage();
        window.setTitle("Paises");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private void btnCidade(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/cidade.fxml"));
        Scene scene = new Scene(root);
         Stage window = new Stage();
        window.setTitle("Cidades");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }
        
        
    

    @FXML
    private void btnEstadio(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/estadio.fxml"));
        Scene scene = new Scene(root);
         Stage window = new Stage();
        window.setTitle("Estadio");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }
    

    @FXML
    private void btnVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
          stage.close();
    }
    
}
