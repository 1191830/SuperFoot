/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogadorController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private TableView<Pessoa> listaJogadores;
    @FXML
    private TableColumn<Pessoa, String> colunaJogador;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnVerJogador;
    @FXML
    private TableColumn<Pessoa, String> colunaEquipa;

    private ArrayList<Pessoa> listaJogador;
    private ObservableList<Pessoa> observableList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tabelaJogadores();
        } catch (SQLException ex) {
            Logger.getLogger(JogadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnVerJogador(ActionEvent event) throws IOException {
        verInfoJogadores();
    }

    private void tabelaJogadores() throws SQLException {
        colunaJogador.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getnome()));
        colunaEquipa.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getNomeEquipa()));

        listaJogador = DbPessoa.obterJogadorEquipa();
        observableList = FXCollections.observableArrayList(listaJogador);
        listaJogadores.setItems(observableList);
    }

    public void verInfoJogadores() throws IOException {
        Pessoa pessoa = listaJogadores.getSelectionModel().getSelectedItem();
        System.out.println(pessoa + "aqui");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/detalheJogador.fxml"));
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setTitle("Detalhe jogador");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

}
