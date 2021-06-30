/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.models.Estatistica;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class DetalheJogadorController {

    @FXML
    private Label labelNacionalidade;
    @FXML
    private Label labelEquipa;
    @FXML
    private Label labelNomeJog;
    @FXML
    private TableView<Estatistica> listaEstatisticaJogador;
    @FXML
    private TableColumn<Estatistica, Integer> colunaLiga;
    @FXML
    private TableColumn<Estatistica, Integer> colunaJogos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaGolos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaAmerelo;
    @FXML
    private TableColumn<Estatistica, Integer> colunaDuplosAmarelos;
    @FXML
    private TableColumn<Estatistica, Integer> colunaVermelhos;
    @FXML
    private Button btnVoltar;

    private ObservableList<Estatistica> observableList;

    public ArrayList<Estatistica> listaJogadorEst;
    Pessoa pessoa = new Pessoa();

    private Stage stageDialog;

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) throws SQLException {
        this.pessoa = pessoa;
        detalhes();
        tabela();
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    private void detalhes() throws SQLException {

        System.out.println(pessoa.getId() + "eu");
        listaJogadorEst = DbJogador.obterJogadorEstatisticas(pessoa);
        System.out.println(listaJogadorEst.toString() + "view" + pessoa);
        labelNomeJog.setText(pessoa.getNome());
        labelEquipa.setText(pessoa.getNomeEquipa());
        labelNacionalidade.setText(pessoa.getNacionalidade());

    }

    private void tabela() {
        colunaLiga.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getAnoLiga()));
        
        colunaJogos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getQuantJogos()));
        
        colunaGolos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getGol()));

        colunaAmerelo.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getAmarelo()));

        colunaDuplosAmarelos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getAmareloDuplo()));

        colunaVermelhos.setCellValueFactory(cellData
                -> new SimpleObjectProperty<Integer>(cellData.getValue().getVermelho()));
        
        observableList = FXCollections.observableArrayList(listaJogadorEst);
        listaEstatisticaJogador.setItems(observableList);
    }
}
