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
public class DetalheEquipaController implements Initializable {

    @FXML
    private TableView<?> listaPlantel;
    @FXML
    private TableColumn<?, ?> colunaPessoa;
    @FXML
    private TableColumn<?, ?> colunaPosicao;
    @FXML
    private TableView<?> listaJogos;
    @FXML
    private TableColumn<?, ?> colunaJogo;
    @FXML
    private TableColumn<?, ?> colunaResultado;
    @FXML
    private Button btnVerEstatistica;
    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Label labelEstadio;
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
    private void btnVerEstatistica(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
