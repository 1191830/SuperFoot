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
public class JogoController implements Initializable {

    @FXML
    private TableView<?> listaJogos;
    @FXML
    private TableColumn<?, ?> colunaJornada;
    @FXML
    private TableColumn<?, ?> colunaJogo;
    @FXML
    private Button btnCriarJogo;
    @FXML
    private Button btnEditarJogo;
    @FXML
    private Button btnApagarJogo;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnVerJogo;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCriarJogo(ActionEvent event) {
    }

    @FXML
    private void btnEditarJogo(ActionEvent event) {
    }

    @FXML
    private void btnApagarJogo(ActionEvent event) {
    }

    @FXML
    private void btnVerJogo(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
