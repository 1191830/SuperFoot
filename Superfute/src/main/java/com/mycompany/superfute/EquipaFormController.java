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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class EquipaFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private TextField nomeEquipa;
    @FXML
    private ComboBox<?> selecionarEstadio;
    @FXML
    private TableView<?> tabelaPessoas;
    @FXML
    private TableColumn<?, ?> colunaPessoa;
    @FXML
    private TextField campoPesquisa;
    @FXML
    private TableView<?> tabelaPessoasEquipa;
    @FXML
    private TableColumn<?, ?> colunaPessoaEquipa;
    @FXML
    private TableColumn<?, ?> colunaFuncaoEquipa;
    @FXML
    private Button btnAddPessoa;
    @FXML
    private Button btnRemovePessoa;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddPessoa(ActionEvent event) {
    }

    @FXML
    private void btnRemovePessoa(ActionEvent event) {
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
