/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbPessoa;
import static com.mycompany.superfute.db.DbPessoa.updatePessoa;
import com.mycompany.superfute.models.Pais;
import com.mycompany.superfute.models.Pessoa;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class PessoaController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Button btnInserirPessoa;
    @FXML
    private Button btnEditarPessoa;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Pessoa> listaPessoas;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableColumn<Pessoa, String> colunaNacionalidade;
    @FXML
    private Button btnRemoverPessoa;

    private ObservableList<Pessoa> observableList;
    private ArrayList<Pessoa> arrPessoas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherTabelaPessoas();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnInserirPessoa(ActionEvent event) throws IOException, SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setPais(new Pais());
       
        boolean flag = false;
        if (controllerPessoaForm(pessoa)) {
            flag = DbPessoa.inserirPessoa(pessoa);
            if (flag) {
                preencherTabelaPessoas();
                MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Pessoa inserida com sucesso!", "Inserir Pessoa");
            } else {
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "Não foi possível inserir uma pessoa.", "Erro ao inserir");
            }
        }
    }

    @FXML
    private void btnEditarPessoa(ActionEvent event) throws IOException, SQLException {
        boolean flag = false;
        Pessoa pessoa = listaPessoas.getSelectionModel().getSelectedItem();
        if (verificaPessoaEAbriView(pessoa)) {
          flag = updatePessoa(pessoa);
          if(flag){
                preencherTabelaPessoas();
               MessageBoxes.ShowMessage(Alert.AlertType.INFORMATION,
                        "Pessoa inserida com sucesso!", "Inserir Pessoa");
          }else{
              MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                        "Não foi possível inserir uma pessoa.", "Erro ao inserir");
          }
        }

    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnRemoverPessoa(ActionEvent event) {
    }

    public static boolean controllerPessoaForm(Pessoa pessoa) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader
                .setLocation(PessoaFormController.class
                        .getResource("fxml/pessoaForm.fxml"));
        AnchorPane page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();

        dialogStage.setTitle(
                "Pessoa");
        Scene scene = new Scene(page);

        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        PessoaFormController controller = loader.getController();

        controller.setStageDialog(dialogStage);

        controller.setPessoa(pessoa);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnReturn();
    }

    private static boolean verificaPessoaEAbriView(Pessoa p) throws IOException {

        if (p != null) {
            if (controllerPessoaForm(p)) {
                return true;
            }
        } else {
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR,
                    "Selecionar Pessoa para editar.", "Erro ao editar");
        }
        return false;
    }

    public void preencherTabelaPessoas() throws SQLException {

        colunaNome.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .getnome()));
        colunaNacionalidade.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue()
                        .getPais().getNome()));
        arrPessoas = DbPessoa.obterPessoas();
        System.out.println(arrPessoas);
        observableList = FXCollections.observableArrayList(arrPessoas);
        listaPessoas.setItems(observableList);
    }
}
