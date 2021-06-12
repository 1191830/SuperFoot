/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogadorFuncaoController implements Initializable {

    @FXML
    private DatePicker dataInicio;
    @FXML
    private ComboBox<?> selecionarFuncao;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
