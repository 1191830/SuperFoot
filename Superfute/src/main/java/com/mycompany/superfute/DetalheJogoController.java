/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbEvento;
import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<Evento> listaEventos;
    @FXML
    private TableColumn<Evento, String> colunaEvento;
    @FXML
    private TableColumn<Evento, String> colunaPessoa;
    @FXML
    private TableColumn<Evento, String> colunaEquipa;
    @FXML
    private TableColumn<Evento, String> colunaMinuto;
    @FXML
    private TableColumn<Evento, String> colunaParte;
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
    private Jogo resultado;
    private ArrayList<Evento> listaEvento;
    private ObservableList<Evento> observableList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    /**
     * O jogo selecionado contem apenas o resultado e as equipas enquanto que o jogo contem as informaçoes relativas a
     * estadio, arbitro, local etc
     * @param jogoSelecionado
     * @throws SQLException 
     */
    public void initJogo(Jogo jogoSelecionado) throws SQLException{
        //procura pelo jogo selecionado atraves do id
        jogo = DbJogo.getJogoById(jogoSelecionado.getJogo());
        resultado = jogoSelecionado;
        initTable();
        
        
              
    }
    
    public void initTable() throws SQLException{
        
        labelLiga.setText(String.valueOf(jogo.getJornada().getIdLiga()));
        labelJornada.setText(String.valueOf(jogo.getJornada().getIdJornada()));
        
        labelEquipaCasa.setText(resultado.getNomeCasa());
        labelResultadoCasa.setText(String.valueOf(resultado.getGolosCasa()));
        
        labelEquipaVisitante.setText(resultado.getNomeFora());
        labelResultadoVisitante.setText(String.valueOf(resultado.getGolosFora()));
        
        labelEstadio.setText(jogo.getEstadio().getNome());
        labelLocal.setText(jogo.getEstadio().getCidade().getNome());
        labelArbitro.setText(jogo.getArbitro().getNome());
        
        colunaEvento.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getEvento()));
        colunaPessoa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getJogador().getNome()));       
        colunaEquipa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getEquipa().getnome()));
        colunaMinuto.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getminuto())));
        colunaParte.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getParte()));
        
        listaEvento = DbEvento.getEventoByJogo(jogo);
        observableList = FXCollections.observableArrayList(listaEvento);
        listaEventos.setItems(observableList);
        
    }
    
    

}
