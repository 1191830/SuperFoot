/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class DetalheJogadorController implements Initializable {

    @FXML
    private Label labelNomeJogador;
    @FXML
    private Label labelNacionalidade;
    @FXML
    private Label labelNomeEquipa;
    @FXML
    private TableView<?> listaEstatisticaJogador;
    @FXML
    private TableColumn<?, ?> colunaLiga;
    @FXML
    private TableColumn<?, ?> colunaJogos;
    @FXML
    private TableColumn<?, ?> colunaGolos;
    @FXML
    private TableColumn<?, ?> colunaAmerelo;
    @FXML
    private TableColumn<?, ?> colunaDuplosAmarelos;
    @FXML
    private TableColumn<?, ?> colunaVermelhos;
    @FXML
    private Button btnVoltar;
    Pessoa jogador = new Pessoa ();

    public ArrayList<Pessoa> listaJogador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detalhes();
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    private void detalhes(){
        labelNomeJogador.setText(jogador.getnome());
        labelNomeEquipa.setText(jogador.getNomeEquipa());
    }
}
