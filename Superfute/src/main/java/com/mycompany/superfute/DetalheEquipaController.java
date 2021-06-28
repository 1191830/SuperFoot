/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class DetalheEquipaController implements Initializable {

    @FXML
    private TableView<Pessoa> listaPlantel;
    @FXML
    private TableColumn<Pessoa, String> colunaPessoa;
    @FXML
    private TableView<Jogo> listaJogos;
    @FXML
    private TableColumn<Jogo, String> colunaJogo;
    @FXML
    private TableColumn<Jogo, String> colunaResultado;
    @FXML
    private Button btnVerEstatistica;
    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Label labelEstadio;
    @FXML
    private Button btnVoltar;

    private Stage stageDialog;
    private Equipa equipa;
    private ObservableList<Pessoa> observableListEquipa;
    private ObservableList<Jogo> observableListJogo;
    private ArrayList<Pessoa> plantel;
    private ArrayList<Jogo> jogos;
    @FXML
    private TableView<Pessoa> listaEquipaTecnica;
    @FXML
    private TableColumn<Pessoa, String> colunaPessoaTecnica;
    @FXML
    private TableColumn<Jogo, Integer> colunaLiga;
    @FXML
    private TableColumn<Jogo, Integer> colunaJornada;

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
        try {
            preencherTabelaPlantel();
            preencherTabelaEquipaTecnica();
            preencherTabelaJogos();
            labelEstadio.setText(equipa.getEstadio().getNome());
        } catch (SQLException ex) {
            Logger.getLogger(DetalheEquipaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void btnVerEstatistica(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        getStageDialog().close();
    }
    /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaPlantel() throws SQLException {
        
      colunaPessoa.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        plantel = DbEquipa.obterPlantelEquipa(equipa.getId(),1);
        observableListEquipa = FXCollections.observableArrayList(plantel);
        listaPlantel.setItems(observableListEquipa);
    }
    /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaEquipaTecnica() throws SQLException {
        
      colunaPessoaTecnica.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        plantel = DbEquipa.obterPlantelEquipa(equipa.getId(),2);
        observableListEquipa = FXCollections.observableArrayList(plantel);
        listaEquipaTecnica.setItems(observableListEquipa);
    }
    /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaJogos() throws SQLException {
        
      colunaJogo.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .retornaStringJogoVersusFormatada()));
       colunaResultado.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .retornaStringJogoResultadoFormatada()));
       colunaJornada.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getJornada().getIdJornada()));
       colunaLiga.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue()
                        .getJornada().getIdLiga()));
        jogos = DbJogo.getResultadosJogosEquipa(equipa.getId());
        observableListJogo = FXCollections.observableArrayList(jogos);
        listaJogos.setItems(observableListJogo);
    }
}
