/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.db.DbLiga;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Jornada;
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
    private TableView<Jogo> tableCasa;
    @FXML
    private TableColumn<Jogo, String> columnEquipaCasa;
    @FXML
    private TableColumn<Jogo, String> columnResultadoCasa;
    @FXML
    private TableColumn<Jogo, String> columnResultadoFora;
    @FXML
    private TableColumn<Jogo, String> columnFora; 
    @FXML
    private Label labelLiga;
    @FXML
    private Label LabelJornada;
    
    private Jornada jornada;
    private Liga liga;
    private ArrayList<Jogo> listaJogos;
    private ObservableList<Jogo> observableList;
    private Jogo jogoSelecionado;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //o utilizador faz duplo clica na tabela e seleciona a jornada que pretende
        tableCasa.setRowFactory(tr -> {
            TableRow<Jogo> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    tableCasa.getSelectionModel().clearSelection();
                } else if (event.getClickCount() == 1) {
                    //jornada selecionada passa a ser a jornada selecionada na table
                    jogoSelecionado = row.getItem();                                     
                }
            });
            return row;
        });
    }
    
    public void initTable() throws SQLException{
        
        labelLiga.setText(String.valueOf(jornada.getIdLiga()));
        LabelJornada.setText(String.valueOf(jornada.getIdJornada()));
        
        columnEquipaCasa.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getNomeCasa())));
        columnResultadoCasa.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosCasa())));
        
        columnFora.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getNomeFora())));
        columnResultadoFora.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getGolosFora())));
        
        listaJogos = DbJogo.obterJogosLigaJornada(jornada);
        observableList = FXCollections.observableArrayList(listaJogos);
        tableCasa.setItems(observableList);
        
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
    private void btnVerJogo(ActionEvent event) throws IOException, SQLException {
        if(jogoSelecionado != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/detalheJogo.fxml"));
            Parent root = loader.load();
            DetalheJogoController controller = loader.getController();
            
            controller.initJogo(jogoSelecionado);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));           
            stage.showAndWait();
            initTable();

            
            }
            
    }

    @FXML
    private void btnVoltar(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/jornada.fxml"));
        Parent root = loader.load();
            
        JornadaController controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        liga = DbLiga.obterLigaID(jornada.getIdLiga());
        controller.initLiga(liga);
                
        // get a handle to the stage
        Stage stage2 = (Stage) btnVoltar.getScene().getWindow();
        // close the scene
        stage2.close();
        
    }
    
}
