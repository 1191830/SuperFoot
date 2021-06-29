/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.models.Jogador;
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
    private TableView<?> listaEstatisticaJogador;
    @FXML
    private TableColumn<Pessoa, String> colunaLiga;
    @FXML
    private TableColumn<Pessoa, String> colunaJogos;
    @FXML
    private TableColumn<Pessoa, String> colunaGolos;
    @FXML
    private TableColumn<Pessoa, String> colunaAmerelo;
    @FXML
    private TableColumn<Pessoa, String> colunaDuplosAmarelos;
    @FXML
    private TableColumn<Pessoa, String> colunaVermelhos;
    @FXML
    private Button btnVoltar;

    public ArrayList<Pessoa> listaJogador;
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

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        detalhes();

    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    private void detalhes() {
        System.out.println(listaJogador + "view" + pessoa);
        labelNomeJog.setText(pessoa.getNome());
        labelEquipa.setText(pessoa.getNomeEquipa());
        labelNacionalidade.setText(pessoa.getNacionalidade());
    }

}
