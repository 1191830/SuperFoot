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
public class JornadaController implements Initializable {

    @FXML
    private TableView<?> listaJornadas;
    @FXML
    private TableColumn<?, ?> colunaJornada;
    @FXML
    private Button btnCriarJornada;
    @FXML
    private Button btnEditarJornada;
    @FXML
    private Button btnApagarJornada;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnVerJogos;
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
    private void btnCriarJornada(ActionEvent event) {
    }

    @FXML
    private void btnEditarJornada(ActionEvent event) {
    }

    @FXML
    private void btnApagarJornada(ActionEvent event) {
    }

    @FXML
    private void btnVerJogos(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
