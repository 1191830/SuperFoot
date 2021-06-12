/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJornada;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JornadaController implements Initializable {

    @FXML
    private TableView<Jornada> listaJornadas;
    @FXML
    private TableColumn<Jornada, String> colunaJornada;
    @FXML
    private TableColumn<Jornada, String> colunaLiga;
    @FXML
    private Button btnCriarJornada;
    @FXML
    private Button btnEditarJornada;
    @FXML
    private Button btnApagarJornada;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnVerJogos;
    @FXML
    private Button btnVoltar;
    
    private Liga liga;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {      
            initTable();
            
        } catch (SQLException ex) {
            Logger.getLogger(JornadaController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        }
    }
    
    private void initTable() throws SQLException {
      
        colunaJornada.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdJornada())));
        colunaLiga.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdLiga())));
        listaJornadas.setItems(DbJornada.getJornadaByLiga(liga.getAno()));
    }

    @FXML
    private void btnCriarJornada(ActionEvent event) {
    }

    @FXML
    private void btnEditarJornada(ActionEvent event) {
    }

    @FXML
    private void btnApagarJornada(ActionEvent event) {
    }

    @FXML
    private void btnVerJogos(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
}
