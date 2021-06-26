package com.mycompany.superfute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.superfute.db.DbPessoa;
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

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    private ArrayList<Pessoa> listaJogador;
    private ObservableList<Pessoa> observableList;

    @FXML
    private TableView<Pessoa> tableviewExpul;
    @FXML
    private TableColumn <Pessoa, String> jogador;
    @FXML
    private TableColumn <Pessoa, Integer >expulsao;

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
        jogador.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        expulsao.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(cellData.getValue().getNumExpulsoes()));


        listaJogador = DbPessoa.obterJogadorExpulsões();
        observableList = FXCollections.observableArrayList(listaJogador);
        tableviewExpul.setItems(observableList);

    }
}
