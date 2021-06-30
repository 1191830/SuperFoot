package com.mycompany.superfute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Liga;
import com.mycompany.superfute.models.Pessoa;
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
import com.mycompany.superfute.models.Pessoa;
import javafx.beans.property.SimpleObjectProperty;

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

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    private ArrayList<Jogador> listaJogador;
    private ObservableList<Jogador> observableList;

    @FXML
    private TableView<Jogador> tableviewExpul;
    @FXML
    private TableColumn<Jogador, String> jogador;
    @FXML
    private TableColumn<Jogador, Integer> expulsao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tableViewExpulsoes();
        } catch (SQLException ex) {
            Logger.getLogger(ExpulsoesJogadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tableViewExpulsoes() throws SQLException {
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
        listaJogador = DbJogador.obterJogadorExpulsões();
        observableList = FXCollections.observableArrayList(listaJogador);
        tableviewExpul.setItems(observableList);

    }
}
