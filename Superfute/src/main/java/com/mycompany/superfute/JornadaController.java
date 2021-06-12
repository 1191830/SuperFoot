/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJornada;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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
    private Button btnApagarJornada;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnVerJogos;
    @FXML
    private Button btnVoltar;
    
    private Liga liga;
    private Jornada jornadaSelecionada;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {      
            initTable();
            
            //o utilizador faz duplo clica na tabela e seleciona a jornada que pretende
        listaJornadas.setRowFactory(tr -> {
            TableRow<Jornada> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    listaJornadas.getSelectionModel().clearSelection();
                } else if (event.getClickCount() == 2) {
                    //jornada selecionada passa a ser a jornada selecionada na table
                    jornadaSelecionada = row.getItem();                                     
                }
            });
            return row;
        });
            
        } catch (SQLException ex) {
            Logger.getLogger(JornadaController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        }
    }
    
    private void initTable() throws SQLException {
      
        colunaJornada.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdJornada())));
        colunaLiga.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdLiga())));
        listaJornadas.setItems(DbJornada.getJornadaByLiga(2021));
    }

    private void btnCriarJornada(ActionEvent event) {
             
    }


    @FXML
    private void btnApagarJornada(ActionEvent event) throws SQLException {
        DbJornada.deleteJornada(jornadaSelecionada.getIdJornada(), jornadaSelecionada.getIdLiga());
        initTable();
    }

    @FXML
    private void OnActionButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == btnCriarJornada){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/newJornada.fxml"));
            Parent root = loader.load();
            NewJornadaController controller = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
                      
            controller.initLiga(2021);         
            
        }
    }

    @FXML
    private void btnVerJogos(ActionEvent event) throws IOException {
        Parent toLiga = FXMLLoader.load(getClass().getResource("fxml/jogo.fxml"));
            
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(toLiga));
        stage.show();
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException {
        Parent toLiga = FXMLLoader.load(getClass().getResource("fxml/liga.fxml"));
            
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(toLiga));
        stage.show();
    }


    
}
