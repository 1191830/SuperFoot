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
public class PessoaController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Button btnInserirPessoa;
    @FXML
    private Button btnEditarPessoa;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<?> listaPessoas;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaNacionalidade;
    @FXML
    private Button btnRemoverPessoa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnInserirPessoa(ActionEvent event) {
    }

    @FXML
    private void btnEditarPessoa(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }

    @FXML
    private void btnRemoverPessoa(ActionEvent event) {
    }
    
}
