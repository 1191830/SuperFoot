/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.db.DbEvento;
import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoEventoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private ComboBox<String> selecionarTipoEvento;
    @FXML
    private ComboBox<Pessoa> selecionarPessoa;
    @FXML
    private ComboBox<String> selecionarParte;
    @FXML
    private ComboBox<Equipa> selecionarEquipa;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private ArrayList<ArrayList<String>> tiposEvento;
    private String[][] partes;
    private ArrayList<Equipa> equipas;
    private ArrayList<Pessoa> pessoas;
    private Equipa equipaSelecionada;
    private ObservableList<Equipa> observableListEquipas;
    private ObservableList<Pessoa> observableListPessoas;
    
    private Jogo jogo;
    private Equipa equipa;
    @FXML
    private TextField txtMinuto;

    public void initCampos(Jogo jogo) {
        try {
            this.jogo = jogo;
            equipas = DbEquipa.getEquipasJogo(jogo);
            partes = DbEvento.getPartes();           
            inserirPartes();
            inserirEquipas();

        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou ", "Erro");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void inserirEquipas() throws SQLException {
        observableListEquipas = FXCollections.observableArrayList(equipas);
        selecionarEquipa.setItems(observableListEquipas);
        
    }

    public void inserirPartes(){
        for (int i = 0; i < partes.length ; i++){
            selecionarParte.getItems().addAll(partes[i][1]);
        }
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionPessoaSelected(ActionEvent event) {
    }

    @FXML
    private void onActionEquipaSelected(ActionEvent event) throws SQLException {
        
        equipaSelecionada = DbEquipa.getEquipaById(selecionarEquipa.getValue().getId());
        pessoas = DbPessoa.getPessoasJogoEquipa(jogo, equipaSelecionada);
        observableListPessoas = FXCollections.observableArrayList(pessoas);
        System.out.println(pessoas.get(0).getId());
        selecionarPessoa.setItems(observableListPessoas);
        selecionarPessoa.setDisable(false);
    }
    
}
