/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEvento;
import com.mycompany.superfute.db.DbJogo;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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
    private ComboBox<?> selecionarEquipaCasa;
    @FXML
    private ComboBox<?> SelecionarEquipaVisitante;
    @FXML
    private DatePicker selecionarData;
    @FXML
    private ComboBox<?> selecionarHora;
    @FXML
    private ComboBox<?> selecionarMinuto;
    @FXML
    private ComboBox<?> selecionarEstadio;
    @FXML
    private ComboBox<?> selecionarArbitro;
    @FXML
    private TableView<?> tabelaFormacao;
    @FXML
    private TableColumn<?, ?> colunaEquipaCasa;
    @FXML
    private TableColumn<?, ?> colunaTitularCasa;
    @FXML
    private TableColumn<?, ?> colunaSuplentesCasa;
    @FXML
    private TableColumn<?, ?> colunaEquipaVisitante;
    @FXML
    private TableColumn<?, ?> colunaTitularVisitante;
    @FXML
    private TableColumn<?, ?> colunaSuplentesVisitante;
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
    private Button btnAdicionarFormação;
    @FXML
    private Button btnEditarFormação;
    @FXML
    private Button btnRemoverFormação;
    @FXML
    private Button btnAdicionarEvento;
    @FXML
    private Button btnEditarEvento;
    @FXML
    private Button btnRemoverEvento;
    @FXML
    private RadioButton radioEquipaCasa;
    @FXML
    private ToggleGroup equipaFormacao;
    @FXML
    private RadioButton radioEquipaVisitante;
    
    private Jogo jogo;
    private ArrayList<Evento> listaEvento;
    private ObservableList<Evento> observableList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initJogo(Jogo jogoSelecionado) throws SQLException{
        //procura pelo jogo selecionado atraves do id
        jogo = DbJogo.getJogoById(jogoSelecionado.getJogo());
        fillEventos();            
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarFormação(ActionEvent event) {
    }

    @FXML
    private void btnEditarFormação(ActionEvent event) {
    }

    @FXML
    private void btnRemoverFormação(ActionEvent event) {
    }

    @FXML
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

        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        JogoEventoFormController controller = loader.getController();

        controller.setStageDialog(dialogStage);

        controller.initCampos(jogo, evento);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
            
        
        return controller.isBtnReturn();
    }
    
}
