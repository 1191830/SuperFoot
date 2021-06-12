/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Rui
 */
public class NewJornadaController implements Initializable {


    @FXML
    private TextField txtNewJornada;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnSair;
    
    private Liga selectedLiga;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initLiga(int liga){
        txtNewJornada.setText(String.valueOf(liga));
    }
    
    @FXML
    private void OnActionNewJornada(ActionEvent event) {
        if(event.getSource() == btnSair){
            // get a handle to the stage
            Stage stage = (Stage) btnSair.getScene().getWindow();
            // close the scene
            stage.close();
        }else if (event.getSource() == btnAplicar){
            
        }
    }

}
