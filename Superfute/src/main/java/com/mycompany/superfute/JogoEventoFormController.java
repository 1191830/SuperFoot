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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    private ComboBox<String> selecionarPessoa;
    @FXML
    private ComboBox<String> selecionarParte;
    @FXML
    private ComboBox<Integer> selecionarMinuto;
    @FXML
    private ComboBox<String> selecionarEquipa;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private ArrayList<ArrayList<String>> tiposEvento;
    private ArrayList<ArrayList<String>> partes;
    private ArrayList<Equipa> equipas;
    private ArrayList<Pessoa> pessoas;
    
    private Jogo jogo;
    private Equipa equipa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            equipas = DbEquipa.getEquipasJogo(jogo);
            pessoas = DbPessoa.getPessoasJogoEquipa(jogo, equipa);
            partes = DbEvento.getPartes();

        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou ", "Erro");
        }
    }    

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
