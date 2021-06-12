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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<?> selecionarEquipaCasa;
    @FXML
    private ComboBox<?> SelecionarEquipaVisitante;
    @FXML
    private DatePicker selecionarData;
    @FXML
    private ComboBox<?> selecionarHora;
    @FXML
    private ComboBox<?> selecionarMinuto;
    @FXML
    private ComboBox<?> selecionarEstadio;
    @FXML
    private ComboBox<?> selecionarArbitro;
    @FXML
    private TableView<?> tabelaFormacao;
    @FXML
    private TableColumn<?, ?> colunaEquipaCasa;
    @FXML
    private TableColumn<?, ?> colunaTitularCasa;
    @FXML
    private TableColumn<?, ?> colunaSuplentesCasa;
    @FXML
    private TableColumn<?, ?> colunaEquipaVisitante;
    @FXML
    private TableColumn<?, ?> colunaTitularVisitante;
    @FXML
    private TableColumn<?, ?> colunaSuplentesVisitante;
    @FXML
    private ScrollPane tabelaEventos;
    @FXML
    private TableColumn<?, ?> colunaEvento;
    @FXML
    private TableColumn<?, ?> colunaPessoa;
    @FXML
    private TableColumn<?, ?> colunaEquipa;
    @FXML
    private TableColumn<?, ?> colunaMinuto;
    @FXML
    private TableColumn<?, ?> colunaParte;
    @FXML
    private Button btnAdicionarFormação;
    @FXML
    private Button btnEditarFormação;
    @FXML
    private Button btnRemoverFormação;
    @FXML
    private Button btnAdicionarEvento;
    @FXML
    private Button btnEditarEvento;
    @FXML
    private Button btnRemoverEvento;
    @FXML
    private RadioButton radioEquipaCasa;
    @FXML
    private ToggleGroup equipaFormacao;
    @FXML
    private RadioButton radioEquipaVisitante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarFormação(ActionEvent event) {
    }

    @FXML
    private void btnEditarFormação(ActionEvent event) {
    }

    @FXML
    private void btnRemoverFormação(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarEvento(ActionEvent event) {
    }

    @FXML
    private void btnEditarEvento(ActionEvent event) {
    }
    
}
