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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class EquipaController implements Initializable {

    @FXML
    private TableView<?> listaEquipas;
    @FXML
    private TableColumn<?, ?> colunaEquipa;
    @FXML
    private Button btnCriarEquipa;
    @FXML
    private Button btnEditarEquipa;
    @FXML
    private Button btnApagarEquipa;
    @FXML
    private Button btnVerEquipa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label labelNomeEquipa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCriarEquipa(ActionEvent event) {
    }

    @FXML
    private void btnEditarEquipa(ActionEvent event) {
    }

    @FXML
    private void btnApagarEquipa(ActionEvent event) {
    }

    @FXML
    private void btnVerEquipa(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
