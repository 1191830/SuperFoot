/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Jornada;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author nelso
 */
public class DetalheJogoController implements Initializable {


    @FXML
    private Label labelEstadio;
    @FXML
    private Label labelHoras;
    @FXML
    private Label labelLocal;
    @FXML
    private TableView<?> listaEventos;
    @FXML
    private TableColumn<?, ?> colunaEvento;
    @FXML
    private TableColumn<?, ?> colunaPessoa;
    @FXML
    private TableColumn<?, ?> colunaEquipa;
    @FXML
    private TableColumn<?, ?> colunaMinuto;
    @FXML
    private TableColumn<?, ?> colunaParte;
    @FXML
    private Button btnCriarEvento;
    @FXML
    private Button btnEditarEvento;
    @FXML
    private Button btnApagarEvento;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label labelEquipaCasa;
    @FXML
    private Label labelEquipaVisitante;
    @FXML
    private Label labelResultadoCasa;
    @FXML
    private Label labelResultadoVisitante;
    @FXML
    private Button btnVerFormacoesEquipas;
    @FXML
    private Label labelLiga;
    @FXML
    private Label labelJornada;
    @FXML
    private Label labelArbitro;
    @FXML
    private void btnCriarEvento(ActionEvent event) {
    }

    @FXML
    private void btnEditarEvento(ActionEvent event) {
    }

    @FXML
    private void btnApagarEvento(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }

    @FXML
    private void btnVerFormacoesEquipas(ActionEvent event) {
    }
    
    private Jogo jogo;
    private Jornada jornada;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initJogo(Jogo jogoSelecionado) throws SQLException{
        jogo = DbJogo.getJogoById(jogoSelecionado.getJogo());
        
        labelLiga.setText(String.valueOf(jogo.getJornada().getIdLiga()));
        labelJornada.setText(String.valueOf(jogo.getJornada().getIdJornada()));
        
        labelEquipaCasa.setText(jogoSelecionado.getNomeCasa());
        labelResultadoCasa.setText(String.valueOf(jogoSelecionado.getGolosCasa()));
        
        labelEquipaVisitante.setText(jogoSelecionado.getNomeFora());
        labelResultadoVisitante.setText(String.valueOf(jogoSelecionado.getGolosFora()));
        
        labelEstadio.setText(jogo.getEstadio().getNome());
        labelLocal.setText(jogo.getEstadio().getCidade().getNome());
        labelArbitro.setText(jogo.getArbitro().getnome());
        
        

        
        
        
        
        
    }
    
    

}
