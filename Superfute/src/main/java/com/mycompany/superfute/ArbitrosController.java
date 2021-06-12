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
public class ArbitrosController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Button btnInserirArbitro;
    @FXML
    private Button btnRemoverArbitro;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<?> tabelaPessoa;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableView<?> tabelaArbitros;
    @FXML
    private TableColumn<?, ?> colunaNomeArbitro;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnInserirArbitro(ActionEvent event) {
    }

    @FXML
    private void btnRemoverArbitro(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }
    
}
