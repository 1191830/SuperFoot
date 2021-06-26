/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.models.Equipa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    private TableView<String[]> listaPlantel;
    @FXML
    private TableColumn<String[], String> colunaPessoa;
    @FXML
    private TableColumn<String[], String> colunaPosicao;
    @FXML
    private TableView<?> listaJogos;
    @FXML
    private TableColumn<?, ?> colunaJogo;
    @FXML
    private TableColumn<?, ?> colunaResultado;
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
    private ObservableList<String[]> observableList;
    private ArrayList<String[]> plantelFuncoes;

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
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnVerEstatistica(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaEquipas() throws SQLException {
      colunaPessoa.setCellValueFactory((p)->{
        String[] x = p.getValue();
        return new SimpleStringProperty(x != null && x.length>0 ? x[0] : "<no name>");
        });
        plantelFuncoes = DbEquipa.obterPlantelEquipa(equipa.getId());
        observableList = FXCollections.observableArrayList(plantelFuncoes);
        listaPlantel.setItems(observableList);
    }
}
