package com.mycompany.superfute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class ExpulsoesJogadorController implements Initializable {

    private Stage stageDialog;
    @FXML
    private TableColumn<Jogador, Integer> qtdJogos;
    @FXML
    private TableColumn<Jogador, Integer> autogolos;
    @FXML
    private TableColumn<Jogador, Integer> qtdAmarelos;
    @FXML
    private ComboBox<Liga> selecionarLiga;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Jogador> tableviewExpul;
    @FXML
    private TableColumn<Jogador, String> jogador;
    @FXML
    private TableColumn<Jogador, Integer> expulsao;

    private Liga ligaSelecionada;
    private ArrayList<Liga> ligas;
    private ObservableList<Liga> observableListLigas;
    private ArrayList<Jogador> listaJogador;
    private ObservableList<Jogador> observableList;
    
    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ligas = DbLiga.obterArrayLigas();
            inserirLigas();
            tableViewExpulsoes(ligaSelecionada);
        } catch (SQLException ex) {
            Logger.getLogger(ExpulsoesJogadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Preencher a comboBox com as ligas
     * @throws SQLException 
     */
    public void inserirLigas() throws SQLException {
        observableListLigas = FXCollections.observableArrayList(ligas);
        selecionarLiga.setItems(observableListLigas);
        
    }

    /**
     * Preenche a tabela das estatisticas
     * @param liga
     * @throws SQLException 
     */
    public void tableViewExpulsoes(Liga liga) throws SQLException {
        jogador.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        qtdJogos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getQtdJogos()));
        qtdAmarelos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getAmarelos()));
        expulsao.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getVemelho()));
        autogolos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getGolosAnulados()));
        
        // Se a liga estiver vazia apresenta estatisticas gerais senao 
        if(liga != null){
            listaJogador = DbJogador.obterJogadorExpulsõesLiga(liga);
        }else {
            listaJogador = DbJogador.obterJogadorExpulsões();
        }
        
        observableList = FXCollections.observableArrayList(listaJogador);
        tableviewExpul.setItems(observableList);

    }

    @FXML
    private void onActionSelecionarLiga(ActionEvent event) throws SQLException {
        
        ligaSelecionada = new Liga();
        if (selecionarLiga.getValue() != null){
            ligaSelecionada = DbLiga.obterLigaID(selecionarLiga.getValue().getAno());
            tableViewExpulsoes(ligaSelecionada);}
    }

    @FXML
    private void btnLimparLiga(ActionEvent event) throws SQLException {
        ligaSelecionada = null;       
        tableViewExpulsoes(ligaSelecionada);
        selecionarLiga.setValue(null);
        
    }

    @FXML
    private void onActionBtnVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}
