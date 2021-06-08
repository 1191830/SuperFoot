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
public class LigaController implements Initializable {

    @FXML
    private TableView<?> listaLigas;
    @FXML
    private TableColumn<?, ?> colunaLiga;
    @FXML
    private TableColumn<?, ?> colunaAno;
    @FXML
    private Button btnCriarLiga;
    @FXML
    private Button btnEditarLiga;
    @FXML
    private Button btnApagarLiga;
    @FXML
    private Button btnJornadas;
    @FXML
    private Button btnEquipas;
    @FXML
    private Button btnJogadores;
    @FXML
    private Button btnClassificacao;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private Label labelLigaAno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCriarLiga(ActionEvent event) {
    }

    @FXML
    private void btnEditarLiga(ActionEvent event) {
    }

    @FXML
    private void btnApagarLiga(ActionEvent event) {
    }

    @FXML
    private void btnJornadas(ActionEvent event) {
    }

    @FXML
    private void btnEquipas(ActionEvent event) {
    }

    @FXML
    private void btnJogadores(ActionEvent event) {
    }

    @FXML
    private void btnClassificacao(ActionEvent event) {
    }

    @FXML
    private void btnEstatisticas(ActionEvent event) {
    }
    
}
