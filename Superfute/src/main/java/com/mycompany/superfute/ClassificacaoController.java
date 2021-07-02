/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbCidade;
import com.mycompany.superfute.db.DbClassificacao;
import com.mycompany.superfute.db.DbLiga;
import com.mycompany.superfute.models.Classificacao;
import com.mycompany.superfute.models.Liga;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author Rui
 */
public class ClassificacaoController implements Initializable {

    @FXML
    private TableView<Classificacao> tableLiga;
    @FXML
    private TableColumn<Classificacao, String> columnEquipa;
    @FXML
    private TableColumn<Classificacao, String> columnPontos;
    @FXML
    private TableColumn<Classificacao, String> columnGMarcados;
    @FXML
    private TableColumn<Classificacao, String> columnGSofridos;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Label labelLigaNome;
    
    private Liga liga;
    private ArrayList<Classificacao> listaLiga;
    private ObservableList<Classificacao> observableList;
    @FXML
    private TableColumn<Classificacao, String> columnJogos;
    @FXML
    private TableColumn<Classificacao, String> columnVitorias;
    @FXML
    private TableColumn<Classificacao, String> columnEmpates;
    @FXML
    private TableColumn<Classificacao, String> columnDerrotas;
    @FXML
    private Button btnVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    public void initTable() throws SQLException{
        
        labelLigaAno.setText(String.valueOf(liga.getAno()));
        labelLigaNome.setText(String.valueOf(liga.getNome()));
        
        columnEquipa.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getEquipa())));
        columnJogos.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getJogos())));
        columnPontos.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getPontos())));
        columnGMarcados.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosMarcados())));
        columnGSofridos.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosSofridos())));
        columnVitorias.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getVitorias())));
        columnEmpates.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getEmpates())));
        columnDerrotas.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getDerrotas())));
        
        listaLiga = DbClassificacao.obterClassificacaoLiga(liga);
        observableList = FXCollections.observableArrayList(listaLiga);
        tableLiga.setItems(observableList);
        
    }
    
    public void initLiga(Liga ligaSelecionada) throws SQLException{
        liga = ligaSelecionada;
        initTable();
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        Parent toLiga = FXMLLoader.load(getClass().getResource("fxml/liga.fxml"));
            
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(toLiga);
        stage.setScene(scene);
        scene.getStylesheets().add("css/new.css");
        stage.show();
    }
    
}
