/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
    
    
    private ArrayList<Jogador> listaJogador;
    private ObservableList<Jogador> observableList; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnJogador.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getnome())));
        columnGolos.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosMarcados())));
        
        try {
            listaJogador = DbJogador.obterMelhorMarcadorGeral();
        } catch (SQLException ex) {
            Logger.getLogger(MelhorMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        observableList = FXCollections.observableArrayList(listaJogador);
        tableMarcadores.setItems(observableList);
    }
    
}
