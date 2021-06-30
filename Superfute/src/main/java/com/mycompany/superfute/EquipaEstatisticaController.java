/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estatistica;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class EquipaEstatisticaController{

    @FXML
    private Label labelLiga;
    @FXML
    private TableView<Estatistica> tabelaEstatistica;
    @FXML
    private TableColumn<Estatistica, Integer> colunaLiga;
    @FXML
    private TableColumn<Estatistica, Integer> colunaJornada;
    @FXML
    private TableColumn<Estatistica, Integer> colunaGolos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaSofridos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaAmarelos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaDuplos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaVermelhos;
    @FXML
    private TableColumn<Estatistica, String> colunaResultados;
    @FXML
    private TableColumn<Estatistica, Integer> colunaPontos;
    @FXML
    private TableColumn<Estatistica, String> colunaCasaFora;
    @FXML
    private Button btnVoltar;
    private Stage stageDialog;
    private Equipa equipa;
    private ArrayList<Estatistica> estatisticas;
      private ObservableList<Estatistica> observableList;
    @FXML
    private Label nomeEquipa;
    

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) throws SQLException {
        this.equipa = equipa;
        nomeEquipa.setText(equipa.getNome());
        preencherTabelaEstatistica();
    }
    

    @FXML
    private void btnVoltar(ActionEvent event) {
        getStageDialog().close();
    }
     /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaEstatistica() throws SQLException {
        
      colunaLiga.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getAnoLiga()));
      colunaJornada.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getNumJornada()));
      colunaGolos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getGol()));
      colunaSofridos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getGolSofrido()));
      colunaAmarelos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getAmarelo()));
      colunaDuplos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getAmareloDuplo()));
      colunaVermelhos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getVermelho()));
      colunaResultados.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .getResultado()));
      colunaPontos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getPonto()));
      colunaCasaFora.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .getCasaForaTexto()));
      
        estatisticas = DbEquipa.getEstatisticaEquipa(equipa.getId());
        observableList = FXCollections.observableArrayList(estatisticas);
        tabelaEstatistica.setItems(observableList);
    }
}
