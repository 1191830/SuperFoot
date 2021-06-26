/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import static com.mycompany.superfute.PessoaController.controllerPessoaForm;
import com.mycompany.superfute.db.DbEquipa;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class EquipaController implements Initializable {

    @FXML
    private TableView<Equipa> listaEquipas;
    @FXML
    private TableColumn<Equipa, String> colunaEquipa;
    @FXML
    private Button btnCriarEquipa;
    @FXML
    private Button btnEditarEquipa;
    @FXML
    private Button btnApagarEquipa;
    @FXML
    private Button btnVerEquipa;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label labelNomeEquipa;
    private ObservableList<Equipa> observableList;
    private ArrayList<Equipa> equipas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherTabelaEquipas();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnCriarEquipa(ActionEvent event) throws IOException {
        Equipa equipa  = new Equipa();
        equipa.setEstadio(new Estadio());
        boolean btnCriarEquipa = controllerEquipaForm(equipa);
        if(btnCriarEquipa){
            System.out.println("cheguei dentro do inserir equipa");
        }
    }
    
    

    @FXML
    private void btnEditarEquipa(ActionEvent event) throws IOException, SQLException {
         boolean verificaEquipaNull = false;
        Equipa equipa = listaEquipas.getSelectionModel().getSelectedItem();
        if (verificaEquipaEAbriView(equipa)) {
          verificaEquipaNull = DbEquipa.updateEquipa(equipa.getId(),equipa.getNome(),
                  equipa.getEstadio().getId());
          if(verificaEquipaNull){
                preencherTabelaEquipas();
               MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Pessoa inserida com sucesso!", "Inserir Pessoa");
          }else{
              MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "Não foi possível inserir uma pessoa.", "Erro ao inserir");
          }
        }
    }

     @FXML
    private void btnVerEquipa(ActionEvent event) throws IOException {
        Equipa equipa = listaEquipas.getSelectionModel().getSelectedItem();
        if (equipa != null) {
            controllerVerEquipa(equipa);
        }
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
    }
    
    
    /**
     * Verifica se o objeto Equipa é diferente de nulo, e invoca o método controllerEquipaForm
     * passando o objeto Equipa como parametro
     * @param e
     * @return
     * @throws IOException 
     */
     private static boolean verificaEquipaEAbriView(Equipa e) throws IOException {

        if (e != null) {
            if (controllerEquipaForm(e)) {
                return true;
            }
        } else {
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                    "Selecionar Equipa para editar.", "Erro ao editar");
        }
        return false;
    }

    
    /**
     * Popula a tabela com todas as equipas existentes na Base de dados
     * @throws SQLException 
     */
    public void preencherTabelaEquipas() throws SQLException {
        colunaEquipa.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .getNome()));
        equipas = DbEquipa.obterEquipasEstadio();
        observableList = FXCollections.observableArrayList(equipas);
        listaEquipas.setItems(observableList);
    }
    
    
    /**
     * Método que faz o carregamento da scene e passa por controller a equipa
     * @param equipa
     * @return retorna verdadeiro ou falso 
     * @throws IOException 
     */
      public static boolean controllerEquipaForm(Equipa equipa) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EquipaFormController.class
                        .getResource("fxml/equipaForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();

        dialogStage.setTitle(
                "Equipa");
        Scene scene = new Scene(page);

        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        EquipaFormController controller = loader.getController();

        controller.setStageDialog(dialogStage);

        controller.setEquipa(equipa);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnReturn();
    }
      
    /**
     * Método que faz o carregamento da scene e passa por controller a equipa
     * @param equipa
     * @return retorna verdadeiro ou falso 
     * @throws IOException 
     */
      public static void controllerVerEquipa(Equipa equipa) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DetalheEquipaController.class
                        .getResource("fxml/detalheEquipa.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();

        dialogStage.setTitle(
                "Equipa");
        Scene scene = new Scene(page);

        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        DetalheEquipaController controller = loader.getController();

        controller.setStageDialog(dialogStage);

        controller.setEquipa(equipa);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
    }
    
}
