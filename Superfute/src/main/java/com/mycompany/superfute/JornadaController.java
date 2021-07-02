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
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Button btnVerJogos;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label labelLiga;
    @FXML
    private Label labelJornada;
    
     private Liga liga;
    private Jornada jornadaSelecionada;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
            
            
            //o utilizador faz duplo clica na tabela e seleciona a jornada que pretende
        listaJornadas.setRowFactory(tr -> {
            TableRow<Jornada> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    listaJornadas.getSelectionModel().clearSelection();
                } else if (event.getClickCount() == 1) {
                    //jornada selecionada passa a ser a jornada selecionada na table
                    jornadaSelecionada = row.getItem();                                     
                }
            });
            return row;
        });
            
        
    }
    
    public void initLiga(Liga ligaSelecionada) throws SQLException{
        liga = ligaSelecionada;
        labelLiga.setText(String.valueOf(ligaSelecionada.getAno()));
        labelJornada.setText(String.valueOf(ligaSelecionada.getNome()));
        initTable();
    }
    
    private void initTable() throws SQLException {
      
        colunaJornada.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdJornada())));
        colunaLiga.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getIdLiga())));
        listaJornadas.setItems(DbJornada.getJornadaByLiga(liga.getAno()));
    }

    private void btnCriarJornada(ActionEvent event) {
             
    }


    @FXML
    private void btnApagarJornada(ActionEvent event) throws SQLException {
        if(jornadaSelecionada != null){
            DbJornada.deleteJornada(jornadaSelecionada.getIdJornada(), jornadaSelecionada.getIdLiga());
            initTable(); }
        else{
            Alert alertBox2 = new Alert(Alert.AlertType.ERROR);
            alertBox2.setHeaderText("Impossivel alterar");
            alertBox2.setContentText("Por favor selecione uma Liga");
            alertBox2.showAndWait();
        }
    }

    @FXML
    private void OnActionButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == btnCriarJornada){
            if(liga != null){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/newJornada.fxml"));
                Parent root = loader.load();
                NewJornadaController controller = loader.getController();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add("css/new.css");
                stage.show();

                controller.initLiga(liga);
            }
            
        }
    }

    @FXML
    private void btnVerJogos(ActionEvent event) throws IOException, SQLException {
       
        if(jornadaSelecionada != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/jogo.fxml"));
            Parent root = loader.load();
            
            JogoController controller = loader.getController();
            
             Stage stage = new Stage();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             scene.getStylesheets().add("css/new.css");
             stage.setTitle("Jornada " + jornadaSelecionada.getIdJornada() + " " + jornadaSelecionada.getIdLiga());
             stage.show();

            controller.initJornada(jornadaSelecionada);
                
            // get a handle to the stage
            Stage stage2 = (Stage) btnVerJogos.getScene().getWindow();
            // close the scene
            stage2.close();
        }
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
