/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbPais;
import com.mycompany.superfute.models.Pais;
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
public class PaisController implements Initializable {

    @FXML
    private TableView<Pais> tblPaises;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableColumn<Pais, String> colNomePaises;
    
    
    private ObservableList<Pais> observableList;
    private ArrayList<Pais> arrPais;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            preencherTabelaPaises();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ClicktblPaises(MouseEvent event) {
    }

    @FXML
    private void btnInserir(ActionEvent event) throws IOException, SQLException {
        Pais pais = new Pais();
        if(controllerPaisesForm(pais)){
            System.out.println(pais);
            preencherTabelaPaises();
        }else{
         MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                 "Não foi possível inserir uma pessoa.", "Erro ao inserir");
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) throws IOException, SQLException {
         Pais pais =  tblPaises.getSelectionModel().getSelectedItem();
        System.out.println(pais);
        if(pais != null){
            controllerPaisesForm(pais);
            preencherTabelaPaises();
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
    
    private void preencherTabelaPaises() throws SQLException {
         colNomePaises.setCellValueFactory(cellData ->
                new SimpleObjectProperty<String>(cellData.getValue().getNome()));
        arrPais = DbPais.getTodosPaises();
        observableList = FXCollections.observableArrayList(arrPais);
        tblPaises.setItems(observableList);
        
        
    }
    
     public static boolean controllerPaisesForm(Pais pais) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PaisFormController.class.getResource("fxml/paisForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Paises");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        PaisFormController controller = loader.getController();
        controller.setStageDialog(dialogStage);
        controller.setPais(pais);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnReturn();
    }
     
     
    
}
