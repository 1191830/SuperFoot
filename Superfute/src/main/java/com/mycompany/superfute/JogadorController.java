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
public class JogadorController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private TableView<?> listaJogadores;
    @FXML
    private TableColumn<?, ?> colunaJogador;
    @FXML
    private Button btnCriarJogador;
    @FXML
    private Button btnEditarJogador;
    @FXML
    private Button btnApagarJogador;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnVerJogador;
    @FXML
    private TableColumn<?, ?> colunaEquipa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCriarJogador(ActionEvent event) {
    }

    @FXML
    private void btnEditarJogador(ActionEvent event) {
    }

    @FXML
    private void btnApagarJogador(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }

    @FXML
    private void btnVerJogador(ActionEvent event) {
    }
    
}
