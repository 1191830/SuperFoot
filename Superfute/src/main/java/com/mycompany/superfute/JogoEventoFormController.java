/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.db.DbEvento;
import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoEventoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private ComboBox<String> selecionarTipoEvento;
    @FXML
    private ComboBox<Pessoa> selecionarPessoa;
    @FXML
    private ComboBox<String> selecionarParte;
    @FXML
    private ComboBox<Equipa> selecionarEquipa;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private String[][] partes;
    private String[][] tiposEvento;
    private ArrayList<Equipa> equipas;
    private ArrayList<Pessoa> pessoas;
    private Equipa equipaSelecionada;
    private Pessoa pessoaSelecionada;
    private ObservableList<Equipa> observableListEquipas;
    private ObservableList<Pessoa> observableListPessoas;
    
    private Jogo jogo;
    private Equipa equipa;
    private Stage stageDialog;
    private Evento evento;
    boolean btnReturn;
    @FXML
    private TextField txtMinuto;
    
    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public Evento getEvento() {
        return evento;
        
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean isBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(boolean btnReturn) {
        this.btnReturn = btnReturn;
    }

    public void initCampos(Jogo jogo, Evento evento) {
        try {
            this.jogo = jogo;
            this.evento = evento;
            equipas = DbEquipa.getEquipasJogo(jogo);
            partes = DbEvento.getPartes();
            tiposEvento = DbEvento.getTipoEvento();
            
            inserirEquipas();
            inserirPartes();
            inserirTiposEvento();
            preencherCampos();

        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou ", "Erro");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void inserirEquipas() throws SQLException {
        observableListEquipas = FXCollections.observableArrayList(equipas);
        selecionarEquipa.setItems(observableListEquipas);
        
    }

    public void inserirPartes(){
        for (int i = 0; i < partes.length ; i++){
            selecionarParte.getItems().addAll(partes[i][1]);
        }
    }
    
    public void inserirTiposEvento(){
        for (int i = 0; i < partes.length ; i++){
            selecionarTipoEvento.getItems().addAll(tiposEvento[i][1]);
        }
    }
    
    

    @FXML
    private void btnAplicar(ActionEvent event) throws SQLException {
        setDadosEvento();
        setBtnReturn(true);
        stageDialog.close();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionPessoaSelected(ActionEvent event) throws SQLException {
        
        pessoaSelecionada = DbPessoa.getPessoaById(selecionarPessoa.getValue().getId());
    }

    @FXML
    private void onActionEquipaSelected(ActionEvent event) throws SQLException {
        selectEquipa();      
    }
    
    /**
     * Seleciona a equipa escolhida e vai buscar os jogadores dessa equipa à base de dados
     * @throws SQLException 
     */
    public void selectEquipa() throws SQLException{
        equipaSelecionada = DbEquipa.getEquipaById(selecionarEquipa.getValue().getId()); 
        pessoas = DbPessoa.getPessoasJogoEquipa(jogo, equipaSelecionada); // procura os jogadores da equipa escolhida
        observableListPessoas = FXCollections.observableArrayList(pessoas); 
        selecionarPessoa.setItems(observableListPessoas); // preenche a combobox de jogadores
        selecionarPessoa.setDisable(false); // habilita o botao para selecionar pessoa
    }
    
    /**
     * Se o evento tiver completo, atribui ao evento os atributos escolhidos
     * @throws SQLException 
     */
    public void setDadosEvento() throws SQLException {
        if (validarCampos()) {
            
            evento.setidJogo(jogo.getJogo());
            evento.setEquipa(DbEquipa.getEquipaById(selecionarEquipa.getValue().getId()));
            evento.setJogador(DbPessoa.getPessoaById(selecionarPessoa.getValue().getId()));
            evento.setminuto(Integer.parseInt(txtMinuto.getText()));
            evento.setIdParte(DbEvento.getIdParteByNome(selecionarParte.getValue()));
            evento.settipoEvento(DbEvento.getIdTipoEventoByNome(selecionarTipoEvento.getValue()));
        }
    }
    
    /**
     * Quando o evento não e null pré preenche os campos para edicao
     * @throws SQLException 
     */
    public void preencherCampos() throws SQLException {
        if (evento != null) {

            selecionarEquipa.setValue(evento.getEquipa());
            if(evento.getEquipa() != null){
                selectEquipa();
                selecionarPessoa.setValue(evento.getJogador());
                selecionarParte.setValue(evento.getParte());               
                selecionarTipoEvento.setValue(evento.getEvento());
                txtMinuto.setText(String.valueOf(evento.getminuto()));
            }
            
        }
    }
    
    /**
     * Valida se todos os campos foram preenchidos
     * @return 
     */
    public boolean validarCampos() {

        if (selecionarEquipa.getValue()== null || selecionarParte.getValue() == null
                 || selecionarPessoa.getValue() == null || selecionarTipoEvento.getValue() == null
                || txtMinuto.getText() == null){
            return false;
        }
        return true;
    }
    
}
