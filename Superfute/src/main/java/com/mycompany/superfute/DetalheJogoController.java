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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Label labelResultadoCasa;
    private Label labelResultadoVisitante;
    @FXML
    private Button btnVerFormacoesEquipas;
    @FXML
    private Label labelLiga;
    @FXML
    private Label labelJornada;
    @FXML
    private Label labelArbitro;
    private Label labelResultadoIntervalo;
    @FXML
    private Button btnIntervali;
    @FXML
    private Label labelResultado;
    
    @FXML
    private void btnCriarEvento(ActionEvent event) throws IOException, SQLException {
        
        Evento evento = new Evento();
       
        boolean flag = false;
        if (controllerJogoEventoForm(jogo, evento)) {
            flag = DbEvento.insertEvento(evento);            
            if (flag) {
                updateResultado(); //apos inserir evento faz update ao resultado 
                fillEventos(); //faz update ?? tabela de eventos
                MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Evento inserido com sucesso!", "Inserir Evento");
            } else {
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "N??o foi poss??vel inserir o evento.", "Erro ao inserir");
            }
        }

    }

    @FXML
    private void btnEditarEvento(ActionEvent event) throws IOException, SQLException {
        
        boolean verificaEventoNull = false;
        Evento evento = listaEventos.getSelectionModel().getSelectedItem();
        if (verificaEventoAbreView(evento)) {
          verificaEventoNull = DbEvento.updateEvento(evento); //retorna true se o evento for editado com sucesso
          if(verificaEventoNull){
                updateResultado();
                fillEventos();
               MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Evento editado com sucesso!", "Editar Evento");
          }else{
              MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "N??o foi poss??vel editar o evento.", "Erro ao editar");
          }
        }
    }

    @FXML
    private void btnApagarEvento(ActionEvent event) throws SQLException, IOException {
               
        boolean flag = false;
        Evento evento = listaEventos.getSelectionModel().getSelectedItem();
          flag = DbEvento.deleteEvento(evento.getId());
          if(flag){
               updateResultado();
               fillEventos();
               MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Evento apagado com sucesso!", "Apagar Evento");
          }else{
              MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "N??o foi poss??vel apagar evento.", "Erro ao apagar");
          }
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnVerFormacoesEquipas(ActionEvent event) throws IOException, SQLException {
        
        if(resultado != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/jogoEquipas.fxml"));
            Parent root = loader.load();
            JogoEquipasController controller = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add("css/new.css");
            stage.setTitle("Formacoes Iniciais");
            stage.show();

            controller.initJogo(resultado);
            }
    }
    
    private Jogo jogo;
    private Jogo resultado;
    private ArrayList<Evento> listaEvento;
    private ObservableList<Evento> observableList;
    private boolean resultadoIntervalo = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    /**
     * O jogo selecionado contem apenas o resultado e as equipas enquanto que o jogo contem as informa??oes relativas a
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
    
    /**
     * Preenche as labels do jogo e chama o metodo que preenche a tabela de eventos
     * @throws SQLException 
     */
    public void initTable() throws SQLException{
        
        labelLiga.setText(String.valueOf(jogo.getJornada().getIdLiga()));
        labelJornada.setText(String.valueOf(jogo.getJornada().getIdJornada()));
        
        labelEquipaCasa.setText(resultado.getNomeCasa());       
        labelEquipaVisitante.setText(resultado.getNomeFora());
        labelResultado.setText(resultado.getGolosCasa() + " x " + resultado.getGolosFora());
        
        labelEstadio.setText(jogo.getEstadio().getNome());
        labelLocal.setText(jogo.getEstadio().getCidade().getNome());
        labelArbitro.setText(jogo.getArbitro().getNome());
        
        fillEventos();
        
        
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
        listaEventos.setItems(observableList);
    }

    @FXML
    private void onActionIntervalo(ActionEvent event) throws SQLException {
        if (resultadoIntervalo == false){ //resultado final
            labelResultado.setText(DbJogo.getResultadoIntervalo(jogo));
            btnIntervali.setText("Final");
            btnIntervali.setLayoutX(265);
            resultadoIntervalo = true;
        } else{ //resultado ao intervalo
            labelResultado.setText(resultado.getGolosCasa() + " x " + resultado.getGolosFora());
            btnIntervali.setText("Intervalo");
            btnIntervali.setLayoutX(254);
            resultadoIntervalo = false;
        }
        
    }
    
    /**
     * Mudar para a janela de criar/editar evento
     * @param jogo
     * @param evento
     * @return
     * @throws IOException 
     */
    public static boolean controllerJogoEventoForm(Jogo jogo, Evento evento) throws IOException{
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EquipaFormController.class
                        .getResource("fxml/JogoEventoForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Est??gio de Di??logo (Stage Dialog)
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

        // Mostra o Dialog e espera at?? que o usu??rio o feche
        dialogStage.showAndWait();
            
        
        return controller.isBtnReturn();
    }
    /**
     * Update ao resultado
     * @throws IOException
     * @throws SQLException 
     */
    public void updateResultado() throws IOException, SQLException{
        
       resultado = DbJogo.getResultado(resultado); 
       labelResultado.setText(resultado.getGolosCasa() + " x " + resultado.getGolosFora()); // atualiza o header com o resultado
       
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
    
    

}
