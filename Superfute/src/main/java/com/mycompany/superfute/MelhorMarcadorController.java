/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.db.DbLiga;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rui
 */
public class MelhorMarcadorController implements Initializable {
    
    @FXML
    private TableView<Jogador> tableMarcadores;
    @FXML
    private TableColumn<Jogador, String> columnJogador;
    @FXML
    private TableColumn<Jogador, String> columnGolos;
    @FXML
    private Label labelLiga;   
    @FXML
    private ComboBox<Liga> selecionarLiga;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnLimpar;
    
    private Liga ligaSelecionada;
    private ArrayList<Jogador> listaJogador;
    private ObservableList<Jogador> observableList; 
    private ArrayList<Liga> ligas;
    private ObservableList<Liga> observableListLigas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ligas = DbLiga.obterArrayLigas();
            inserirLigas();
        } catch (SQLException ex) {
            Logger.getLogger(MelhorMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fillTable(ligaSelecionada);
        
    }
    
    public void fillTable(Liga liga){
        columnJogador.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getNome())));
        columnGolos.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosMarcados())));
               
        try {
            
            if(liga != null){
                listaJogador = DbJogador.obterMelhorMarcadorLiga(ligaSelecionada); }
            else{
                listaJogador = DbJogador.obterMelhorMarcadorGeral(); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(MelhorMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        observableList = FXCollections.observableArrayList(listaJogador);
        tableMarcadores.setItems(observableList);
    }

    @FXML
    private void onActionSelecionarLiga(ActionEvent event) throws SQLException {
        ligaSelecionada = new Liga();
        if (selecionarLiga.getValue() != null){
            ligaSelecionada = DbLiga.obterLigaID(selecionarLiga.getValue().getAno());
            fillTable(ligaSelecionada);}
                    
    }

    @FXML
    private void onActionBtnVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    public void inserirLigas() throws SQLException {
        observableListLigas = FXCollections.observableArrayList(ligas);
        selecionarLiga.setItems(observableListLigas);
        
    }

    @FXML
    private void btnLimparLiga(ActionEvent event) {
        ligaSelecionada = null;       
        fillTable(ligaSelecionada);
        selecionarLiga.setValue(null);
        
    }
    
}
