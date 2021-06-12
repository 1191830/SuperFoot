/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbLiga;
import com.mycompany.superfute.db.Dbconn;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class LigaController implements Initializable {

    @FXML
    private TableView<Liga> listaLigas;
    @FXML
    private TableColumn<Liga, String> colunaLiga;
    @FXML
    private TableColumn<Liga, String> colunaAno;
    @FXML
    private Button btnCriarLiga;
    @FXML
    private Button btnEditarLiga;
    @FXML
    private Button btnApagarLiga;
    @FXML
    private Button btnJornadas;
    @FXML
    private Button btnEquipas;
    @FXML
    private Button btnJogadores;
    @FXML
    private Button btnClassificacao;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnArbitros;
    @FXML
    private Button btnVoltar;
    
    private Liga liga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            mostrarLigas();
            
            listaLigas.setRowFactory(tr -> {
            TableRow<Liga> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    listaLigas.getSelectionModel().clearSelection();
                } else if (event.getClickCount() == 2) {
                    //jornada selecionada passa a ser a jornada selecionada na table
                    liga = row.getItem();                                     
                }
            });
            return row; });
        } catch (SQLException ex) {
            Logger.getLogger(LigaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnCriarLiga(ActionEvent event) {
    }

    @FXML
    private void btnEditarLiga(ActionEvent event) {
    }

    @FXML
    private void btnApagarLiga(ActionEvent event) {
    }

    @FXML
    private void btnJornadas(ActionEvent event) throws IOException, SQLException {
        if(liga != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/jornada.fxml"));
            Parent root = loader.load();
            JornadaController controller = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            controller.initLiga(liga);
            
            // get a handle to the stage
            Stage stage2 = (Stage) btnJornadas.getScene().getWindow();
            // close the scene
            stage2.close();
        } else{
            Alert alertBox2 = new Alert(Alert.AlertType.ERROR);
            alertBox2.setHeaderText("Impossivel alterar");
            alertBox2.setContentText("Por favor selecione uma Liga");
            alertBox2.showAndWait();
        }
    }

    @FXML
    private void btnEquipas(ActionEvent event) {
    }

    @FXML
    private void btnJogadores(ActionEvent event) {
    }

    @FXML
    private void btnClassificacao(ActionEvent event) {
    }

    @FXML
    private void btnEstatisticas(ActionEvent event) {
    }
    
    public void mostrarLigas() throws SQLException{
        
        ObservableList<Liga> listaLiga = DbLiga.obterLigas();
        
        colunaLiga.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getNome())));
        colunaAno.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getAno())));
        listaLigas.setItems(listaLiga);
        

    
    }

    @FXML
    private void btnArbitros(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
    
    
    
}
