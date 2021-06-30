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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class TrocaEquipasTabelaController implements Initializable {

    @FXML
    private TableView<?> tabelaInicio;
    @FXML
    private TableColumn<?, ?> nomeInicio;
    @FXML
    private TableView<?> tabelaFim;
    @FXML
    private TableColumn<?, ?> nomeFim;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnConcluido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAdicionarJogador(ActionEvent event) {
    }

    @FXML
    private void btnRemoverJogador(ActionEvent event) {
    }

    @FXML
    private void btnConcluidoJogador(ActionEvent event) {
    }
    
}
