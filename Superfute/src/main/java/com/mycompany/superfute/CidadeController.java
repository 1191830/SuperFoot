/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbCidade;
import com.mycompany.superfute.models.Cidade;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pcoelho
 */
public class CidadeController implements Initializable {

    @FXML
    private TableView<Cidade> tblCidade;
    @FXML
    private TableColumn<Cidade, String> colNome;
    @FXML
    private TableColumn<Cidade, String> colPais;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnVoltar;
    
    
    private ObservableList<Cidade> observableList;
    private ArrayList<Cidade> arrCidades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            preencherTabelaCidades();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }    

    @FXML
    private void ClicktblCidade(MouseEvent event) {
    }

    @FXML
    private void btnInserir(ActionEvent event) throws IOException {
        Cidade cidade = new Cidade();
        if(controllerCidadeForm(cidade)){
            System.out.println(cidade);
        }else{
         MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                 "Não foi possível inserir uma pessoa.", "Erro ao inserir");
        }
        
    }

    @FXML
    private void btnEditar(ActionEvent event) throws IOException {
        
        Cidade cidade =  tblCidade.getSelectionModel().getSelectedItem();
        System.out.println(cidade);
        if(cidade != null){
            controllerCidadeForm(cidade);
        }else{
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, 
                    "Selecionar Cidade para editar.", "Erro ao editar");
        }
    }

    @FXML
    private void btnApagar(ActionEvent event) {
    }

    private void preencherTabelaCidades() throws SQLException {
         colNome.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        colPais.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getPais().getNome()));
        arrCidades = DbCidade.obterCidades();
        observableList = FXCollections.observableArrayList(arrCidades);
        tblCidade.setItems(observableList);
        
        
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
         Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.close();
    }
    
    
    public static boolean controllerCidadeForm(Cidade cidade) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CidadeFormController.class.getResource("fxml/cidadeForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cidade");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        CidadeFormController controller = loader.getController();
        controller.setStageDialog(dialogStage);
        controller.setCidade(cidade);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnReturn();
    }
    
}
