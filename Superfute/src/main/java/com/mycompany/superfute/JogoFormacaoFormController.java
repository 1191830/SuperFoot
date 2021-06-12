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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoFormacaoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnAddTitular;
    @FXML
    private Button btnRemoverTitutal;
    @FXML
    private Button btnAddSuplente;
    @FXML
    private Button btnRemoverSuplente;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<?> tabelaJogadores;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableView<?> tabelaJogadoresTitulares;
    @FXML
    private TableColumn<?, ?> colunaNomeTitulares;
    @FXML
    private TableView<?> tabelaJogadoresSuplentes;
    @FXML
    private TableColumn<?, ?> colunaNomeSuplentes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddTitular(ActionEvent event) {
    }

    @FXML
    private void btnRemoverTitutal(ActionEvent event) {
    }

    @FXML
    private void btnAddSuplente(ActionEvent event) {
    }

    @FXML
    private void btnRemoverSuplente(ActionEvent event) {
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
