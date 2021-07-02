/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEvento;
import java.io.IOException;
import com.mycompany.superfute.db.DbArbitro;
import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.db.DbEstadio;
import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.db.DbJogador;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class JogoFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<Equipa> selecionarEquipaCasa;
    @FXML
    private ComboBox<Equipa> SelecionarEquipaVisitante;
    @FXML
    private DatePicker selecionarData;
    @FXML
    private ComboBox<String> selecionarHora;
    @FXML
    private ComboBox<String> selecionarMinuto;
    @FXML
    private ComboBox<Estadio> selecionarEstadio;
    @FXML
    private ComboBox<Pessoa> selecionarArbitro;
    @FXML
    private TableView<Evento> tabelaEventos;
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
    private Button btnEditarEvento;
    @FXML
    private Tab tabJogo;
    @FXML
    private Tab tabFormacao;
    @FXML
    private Tab tabEventos;
    @FXML
    private Button btnCriarEvento;
    @FXML
    private Button btnApagarEvento;
    
    private Jogo jogo;   
    private ObservableList<Evento> observableList;
    private ObservableList<Jogador> observableListPlantelCasa;
    private ObservableList<Jogador> observableListPlantelFora;
    private ObservableList<Jogador> observableListTitularesCasa;
    private ObservableList<Jogador> observableListTitularesFora;
    private ObservableList<Jogador> observableListSuplentesCasa;
    private ObservableList<Jogador> observableListSuplentesFora;   
    private Stage stageDialog;
    private boolean btnClicked;
    private ArrayList<Estadio> estadios;
    private ArrayList<Pessoa> arbitros;
    private ArrayList<Equipa> equipas;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Evento> listaEvento;
    private ArrayList<String> horas;
    private ArrayList<String> minutos;
    private ArrayList<Jogador> listaPlantelCasa;
    private ArrayList<Jogador> listaPlantelFora;
    private ArrayList<Jogador> listaTitularesCasa;
    private ArrayList<Jogador> listaTitularesFora;
    private ArrayList<Jogador> listaSuplentesCasa;
    private ArrayList<Jogador> listaSuplentesFora;
    private Jogo infoJogo;
    private Equipa equipaCasa;
    private Equipa equipaFora;
    @FXML
    private TableView<Jogador> tablePlantelCasa;
    @FXML
    private TableColumn<Jogador, String> columnPlantelCasa;
    @FXML
    private TableView<Jogador> tableTitularesCasa;
    @FXML
    private TableColumn<Jogador, String> columnTitularesCasa;
    @FXML
    private TableView<Jogador> tablePlantelFora;
    @FXML
    private TableColumn<Jogador, String> columnPlantelFora;
    @FXML
    private TableView<Jogador> tableTitularesFora;
    @FXML
    private TableColumn<Jogador, String> columnTitularesFora;
    @FXML
    private TableView<Jogador> tableSuplentesCasa;
    @FXML
    private TableColumn<Jogador, String> columnSuplentesCasa;
    @FXML
    private TableView<Jogador> tableSuplentesFora;
    @FXML
    private TableColumn<Jogador, String> columnSuplentesFora;
    @FXML
    private Button btnEntraTitularCasa;
    @FXML
    private Button btnEntraBancoCasa;
    @FXML
    private Button btnEntraBancoFora;
    @FXML
    private Button onActionSaidaFora;
    @FXML
    private Button btnSaidaCasa;
    @FXML
    private Button btnMudarEstadoCasa;
    @FXML
    private Button btnMudarEstadoFora;
    @FXML
    private Button btnSaidaFora;

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) throws SQLException {
        this.jogo = jogo;
        if(jogo.getJogo() != 0){
            System.out.println(jogo.getJogo());
            jogo.setEquipaCasa(DbEquipa.getEquipaJogo(jogo, 0));
            jogo.setEquipaFora(DbEquipa.getEquipaJogo(jogo, 1));
            infoJogo = DbJogo.getJogoById(jogo.getJogo());
            fillEventos();
            equipaCasa = jogo.getEquipaCasa();
            equipaFora = jogo.getEquipaFora();
            fillTablePlantel();
        }            
        try {

            preencherArrayList();
            inserirCampos();
            definirValorCamposObjeto();
         
        } catch (SQLException ex) {
            Logger.getLogger(JogoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

   
    public void horasMinutos() {
        horas = new ArrayList<>();
        minutos = new ArrayList<>();
        for (int i = 0; i < 24; i++) {

            horas.add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutos.add(String.format("%02d", i));
        }
    }

    public void preencherArrayList() throws SQLException {
        equipas = DbEquipa.getEquipasObject();
        arbitros = DbArbitro.obterArbitros();
        estadios = DbEstadio.obterEstadios();
        horasMinutos();
    }

    public void inserirCampos() {
        selecionarEquipaCasa.getItems().addAll(equipas);
        SelecionarEquipaVisitante.getItems().addAll(equipas);
        selecionarArbitro.getItems().addAll(arbitros);
        selecionarEstadio.getItems().addAll(estadios);
        selecionarHora.getItems().addAll(horas);
        selecionarMinuto.getItems().addAll(minutos);

    }

    public void definirValorCamposObjeto() {
        if (jogo != null) {
            if (jogo.getJogo() != 0){
               selecionarEquipaCasa.setValue(jogo.getEquipaCasa());
               SelecionarEquipaVisitante.setValue(jogo.getEquipaFora());
                selecionarArbitro.setValue(jogo.getArbitro());
                selecionarEstadio.setValue(jogo.getEstadio());
                selecionarArbitro.setValue(infoJogo.getArbitro());
                selecionarEstadio.setValue(infoJogo.getEstadio());
                tabFormacao.setDisable(false);
                tabEventos.setDisable(false);
            }
        }
    }

    public void setDadosJogo() {
        if (validarCampos()) {
            jogo.setEquipaCasa(selecionarEquipaCasa.getSelectionModel().getSelectedItem());
            jogo.setEquipaFora(SelecionarEquipaVisitante.getSelectionModel().getSelectedItem());
            jogo.setArbitro(selecionarArbitro.getSelectionModel().getSelectedItem());
        }
    }

    public boolean validarCampos() {

        if (selecionarEquipaCasa.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Equipa Casa", "Selecionar");
            return false;
        }
        if (SelecionarEquipaVisitante.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Equipa Visitante", "Selecionar");
            return false;
        }
        if (selecionarArbitro.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Arbitro", "Selecionar");
            return false;
        }
        if (selecionarEstadio.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar Estádio", "Selecionar");
            return false;
        }
        if (selecionarData.getValue() == null) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar data", "Selecionar");
            return false;
        }
        if (selecionarHora.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar hora", "Selecionar");
            return false;
        }
        if (selecionarMinuto.getSelectionModel().isEmpty()) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Selecionar minuto", "Selecionar");
            return false;
        }
        return true;
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
        setDadosJogo();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }


    private void btnAdicionarEvento(ActionEvent event) throws SQLException, IOException {
        
        Evento evento = new Evento();
       
        boolean flag = false;
        if (controllerJogoEventoForm(jogo, evento)) {
            flag = DbEvento.insertEvento(evento);            
            if (flag) {
                
                MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Evento inserido com sucesso!", "Inserir Evento");
            } else {
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "Não foi possível inserir o evento.", "Erro ao inserir");
            }
        }
    }

    @FXML
    private void btnEditarEvento(ActionEvent event) throws SQLException, IOException {
        
        boolean verificaEventoNull = false;
        Evento evento = tabelaEventos.getSelectionModel().getSelectedItem();
        if (verificaEventoAbreView(evento)) {
          verificaEventoNull = DbEvento.updateEvento(evento); //retorna true se o evento for editado com sucesso
          if(verificaEventoNull){
                fillEventos();
               MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Evento editado com sucesso!", "Editar Evento");
          }else{
              MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "Não foi possível editar o evento.", "Erro ao editar");
          }
        }
    }
    
    /**
     * Preenche a tabela com os eventos
     * @throws SQLException 
     */
    public void fillEventos() throws SQLException{
        
        colunaEvento.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getEvento()));
        colunaPessoa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getJogador().getNome()));       
        colunaEquipa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getEquipa().getNome()));
        colunaMinuto.setCellValueFactory(date -> new SimpleStringProperty(String.valueOf(date.getValue().getminuto())));
        colunaParte.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getParte()));
        
        listaEvento = DbEvento.getEventoByJogo(jogo);
        observableList = FXCollections.observableArrayList(listaEvento);
        tabelaEventos.setItems(observableList);
    }
    
    private boolean verificaEventoAbreView(Evento e) throws IOException {

        if (e != null) {
            if (controllerJogoEventoForm(jogo, e)) {
                return true;
            }
        } else {
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                    "Selecionar Evento para editar.", "Erro ao editar");
        }
        return false;
    }
    
    public static boolean controllerJogoEventoForm(Jogo jogo, Evento evento) throws IOException{
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EquipaFormController.class
                        .getResource("fxml/JogoEventoForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();

        dialogStage.setTitle(
                "Evento");
        Scene scene = new Scene(page);
        scene.getStylesheets().add("css/new.css");

        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        JogoEventoFormController controller = loader.getController();

        controller.setStageDialog(dialogStage);

        controller.initCampos(jogo, evento);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
            
        
        return controller.isBtnReturn();
    }

    public void inserirNacionalidade() throws SQLException {
        for (Estadio e : estadios) {
            //  selecionarNacionalidade.getItems().addAll(p.getNome());
        }
    }

    @FXML
    private void actionEquipaCasa(ActionEvent event) throws SQLException {

        equipaCasa = selecionarEquipaCasa.getSelectionModel().getSelectedItem();
        if(equipaCasa == SelecionarEquipaVisitante.getSelectionModel().getSelectedItem()){
            selecionarEquipaCasa.setValue(null);
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Nao Pode selecionar a mesma equipa", "Selecionar");
        }else if(SelecionarEquipaVisitante.getSelectionModel().getSelectedItem() != null){
            tabFormacao.setDisable(false);
            fillTablePlantel();

        }
    }

    @FXML
    private void actionEquipaVisitante(ActionEvent event) throws SQLException {

        equipaFora = SelecionarEquipaVisitante.getSelectionModel().getSelectedItem();
        if(equipaFora == selecionarEquipaCasa.getSelectionModel().getSelectedItem()){
            SelecionarEquipaVisitante.setValue(null);
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING, "Nao Pode selecionar a mesma equipa", "Selecionar");
        }else if(selecionarEquipaCasa.getSelectionModel().getSelectedItem() != null){
            tabFormacao.setDisable(false);
            fillTablePlantel();
            
            
        }
    }
    
    private void fillTablePlantel() throws SQLException{
        
        columnPlantelCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        listaPlantelCasa = DbJogador.obterJogadorNomePorEquipa(equipaCasa);
        observableListPlantelCasa = FXCollections.observableArrayList(listaPlantelCasa);
        tablePlantelCasa.setItems(observableListPlantelCasa);
        
        columnPlantelFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        listaPlantelFora = DbJogador.obterJogadorNomePorEquipa(equipaFora);
        observableListPlantelFora = FXCollections.observableArrayList(listaPlantelFora);
        tablePlantelFora.setItems(observableListPlantelFora);
        
        listaTitularesCasa = DbJogador.obterJogadorEquipaJogo(jogo, equipaCasa, 1);
        listaSuplentesCasa = DbJogador.obterJogadorEquipaJogo(jogo, equipaCasa, 2);
        listaTitularesFora = DbJogador.obterJogadorEquipaJogo(jogo, equipaFora, 1);
        listaSuplentesFora = DbJogador.obterJogadorEquipaJogo(jogo, equipaFora, 2);
        if(listaTitularesCasa != null || listaSuplentesCasa != null 
                || listaTitularesFora != null || listaSuplentesFora != null){
            fillTableFormacoes();
        }
        
    }
    
    private void fillTableFormacoes() throws SQLException{
        
        columnTitularesCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));       
        observableListTitularesCasa = FXCollections.observableArrayList(listaTitularesCasa);
        tableTitularesCasa.setItems(observableListTitularesCasa);
        
        columnSuplentesCasa.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));       
        observableListSuplentesCasa = FXCollections.observableArrayList(listaSuplentesCasa);
        tableSuplentesCasa.setItems(observableListSuplentesCasa);
        
        columnTitularesFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));
        observableListTitularesFora = FXCollections.observableArrayList(listaTitularesFora);
        tableTitularesFora.setItems(observableListTitularesFora);
        
        columnSuplentesFora.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getNome()));        
        observableListSuplentesFora = FXCollections.observableArrayList(listaSuplentesFora);
        tableSuplentesFora.setItems(observableListSuplentesFora);
        
    }


    @FXML
    private void btnCriarEvento(ActionEvent event) {
    }

    @FXML
    private void btnApagarEvento(ActionEvent event) {
    }

    @FXML
    private void onActionEntradaCasa(ActionEvent event) {
    }

    @FXML
    private void onActionSaidaCasa(ActionEvent event) {
    }

    @FXML
    private void onActionEntradaFora(ActionEvent event) {
    }

    @FXML
    private void onActionSaidaFora(ActionEvent event) {
    }

    @FXML
    private void onActionEstadoJogoCasa(ActionEvent event) {
    }

    @FXML
    private void onActionEstadoJogoFora(ActionEvent event) {
    }

}
