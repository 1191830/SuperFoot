/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEstadio;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pcoelho
 */
public class EstadioController implements Initializable {

    @FXML
    private TableView<Estadio> tblEstadio;
    @FXML
    private TableColumn<Estadio, String> colNome;
    @FXML
    private TableColumn<Estadio, String> colCidade;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnVoltar;
    
    private ObservableList<Estadio> observableList;
    private ArrayList<Estadio> arrEstadio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try{
             preencherTabelaEstadios();
        } catch (SQLException ex) {
            Logger.getLogger(EstadioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void btnInserir(ActionEvent event) throws IOException, SQLException {
        Estadio estadio = new Estadio();
        if(controllerEstadioForm(estadio)){
            System.out.println(estadio);
            preencherTabelaEstadios();
        }else{
         MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                 "Não foi possível inserir uma pessoa.", "Erro ao inserir");
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) throws IOException, SQLException {
        
        Estadio estadio =  tblEstadio.getSelectionModel().getSelectedItem();
        System.out.println(estadio);
        if(estadio != null){
            controllerEstadioForm(estadio);
            preencherTabelaEstadios();
        }else{
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, 
                    "Selecionar Cidade para editar.", "Erro ao editar");
        }
    }

    @FXML
    private void btnApagar(ActionEvent event) {
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.close();
    }

    
    public static boolean controllerEstadioForm(Estadio estadio) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EstadioFormController.class.getResource("fxml/estadioForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Estadio");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        EstadioFormController controller = loader.getController();
        controller.setStageDialog(dialogStage);
        controller.setEstadio(estadio);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnReturn();
    }

    private void preencherTabelaEstadios() throws SQLException {
         colNome.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        colCidade.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getCidade().getNome()));
        arrEstadio = DbEstadio.obterEstadios();
        observableList = FXCollections.observableArrayList(arrEstadio);
        tblEstadio.setItems(observableList);
        
        
    }

    
}
