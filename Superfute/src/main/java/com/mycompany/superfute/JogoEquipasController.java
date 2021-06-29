/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Rui
 */
public class JogoEquipasController implements Initializable{

    @FXML
    private Label labelCasa;
    @FXML
    private Label labelFora;
    @FXML
    private TableView<Pessoa> tableTitularesCasa;
    @FXML
    private TableColumn<Pessoa, String> columnTitularesCasa;
    @FXML
    private TableView<Pessoa> tableTitularesFora;
    @FXML
    private TableColumn<Pessoa, String> columnTitularesFora;
    @FXML
    private TableView<Pessoa> tableSuplentesFora;
    @FXML
    private TableColumn<Pessoa, String> columnSuplentesFora;
    @FXML
    private TableView<Pessoa> tableTecnicosCasa;
    @FXML
    private TableColumn<Pessoa, String> columnTecnicosCasa;
    @FXML
    private TableView<Pessoa> tableSuplentesCasa;
    @FXML
    private TableColumn<Pessoa, String> columnSuplentesCasa;
    @FXML
    private TableView<Pessoa> tableTecnicosFora;
    @FXML
    private TableColumn<Pessoa, String> columnTecnicosFora;
    
    private Jogo jogo;
    private ArrayList<Pessoa> listaTitularesCasa;
    private ArrayList<Pessoa> listaSuplentesCasa;
    private ArrayList<Pessoa> listaTecnicosCasa;
    private ArrayList<Pessoa> listaTitularesFora;
    private ArrayList<Pessoa> listaSuplenteFora;
    private ArrayList<Pessoa> listaTecnicosFora;
    
    private ObservableList<Pessoa> observableList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initJogo(Jogo jogoSelecionado) throws SQLException{
        jogo = DbJogo.getJogoById(jogoSelecionado.getJogo());
        
        initTables();
    }
    
    public void initTables() throws SQLException{
        labelCasa.setText(jogo.getEquipaCasa().getNome());
        labelFora.setText(jogo.getEquipaFora().getNome());
        
        columnTitularesCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaTitularesCasa = DbJogo.getPessoaJogo(jogo, jogo.getEquipaCasa(), 1);
        observableList = FXCollections.observableArrayList(listaTitularesCasa);
        tableTitularesCasa.setItems(observableList);
        
        columnSuplentesCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaSuplentesCasa = DbJogo.getPessoaJogo(jogo, jogo.getEquipaCasa(), 2);
        observableList = FXCollections.observableArrayList(listaSuplentesCasa);
        tableSuplentesCasa.setItems(observableList);
        
        columnTecnicosCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaTecnicosCasa = DbJogo.getPessoaJogo(jogo, jogo.getEquipaCasa(), 3);
        observableList = FXCollections.observableArrayList(listaTecnicosCasa);
        tableTecnicosCasa.setItems(observableList);
        
        columnTitularesFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaTitularesFora = DbJogo.getPessoaJogo(jogo, jogo.getEquipaFora(), 1);
        observableList = FXCollections.observableArrayList(listaTitularesFora);
        tableTitularesFora.setItems(observableList);
        
        columnSuplentesFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaSuplenteFora = DbJogo.getPessoaJogo(jogo, jogo.getEquipaFora(), 2);
        observableList = FXCollections.observableArrayList(listaSuplenteFora);
        tableSuplentesFora.setItems(observableList);
        
        columnTecnicosFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        
        listaTecnicosFora = DbJogo.getPessoaJogo(jogo, jogo.getEquipaFora(), 3);
        observableList = FXCollections.observableArrayList(listaTecnicosFora);
        tableTecnicosFora.setItems(observableList);
        
    }
    
}
