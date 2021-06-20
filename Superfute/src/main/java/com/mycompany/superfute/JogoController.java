/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbClassificacao;
import com.mycompany.superfute.models.Classificacao;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class JogoController implements Initializable {

    @FXML
    private Button btnCriarJogo;
    @FXML
    private Button btnEditarJogo;
    @FXML
    private Button btnApagarJogo;
    private Label labelLigaAno;
    @FXML
    private Button btnVerJogo;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Classificacao> tableCasa;
    @FXML
    private TableView<Classificacao> tableFora;
    @FXML
    private TableColumn<Classificacao, String> columnResultadoCasa;
    @FXML
    private TableColumn<Classificacao, String> columnFora;
    @FXML
    private TableColumn<Classificacao, String> columnCasa;
    @FXML
    private TableColumn<Classificacao, String> columnResultadoFora;   
    @FXML
    private Label labelLiga;
    @FXML
    private Label LabelJornada;
    
    private Jornada jornada;
    private ArrayList<Classificacao> listaJogos;
    private ObservableList<Classificacao> observableList;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initTable() throws SQLException{
        
        labelLiga.setText(String.valueOf(jornada.getIdLiga()));
        LabelJornada.setText(String.valueOf(jornada.getIdJornada()));
        
        columnCasa.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getEquipa())));
        columnResultadoCasa.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosMarcados())));
        
        columnFora.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getEquipa())));
        columnResultadoFora.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosMarcados())));
        
        listaJogos = DbClassificacao.obterJogosLigaJornada(jornada, 0);
        observableList = FXCollections.observableArrayList(listaJogos);
        tableCasa.setItems(observableList);
        
        listaJogos = DbClassificacao.obterJogosLigaJornada(jornada, 1);
        observableList = FXCollections.observableArrayList(listaJogos);
        tableFora.setItems(observableList);
        
    }

    public void initJornada(Jornada jornadaSelecionada) throws SQLException{
        jornada = jornadaSelecionada;
        System.out.println(jornada.getIdLiga());
        initTable();
    }

    @FXML
    private void btnCriarJogo(ActionEvent event) {
    }

    @FXML
    private void btnEditarJogo(ActionEvent event) {
    }

    @FXML
    private void btnApagarJogo(ActionEvent event) {
    }

    @FXML
    private void btnVerJogo(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
