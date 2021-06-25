/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbPais;
import com.mycompany.superfute.models.Pais;
import com.mycompany.superfute.models.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PessoaFormController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private TextField nomePessoa;
    @FXML
    private ComboBox<String> selecionarNacionalidade;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;

    private Stage stageDialog;
    private Pessoa pessoa;
    boolean btnReturn;
    private ArrayList<Pais> paises;

    /**
     * Getters e Setters
     *
     * @return
     */
    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        preencherCampos();
    }

    public boolean isBtnReturn() {
        return btnReturn;
    }

    public void setBtnReturn(boolean btnReturn) {
        this.btnReturn = btnReturn;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            paises = DbPais.getTodosPaises();
            inserirNacionalidade();
        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou os países", "Países");
        }
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
        setDadosPessoa();
        setBtnReturn(true);
        stageDialog.close();

    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        stageDialog.close();
    }

    public void preencherCampos() {
        if (pessoa != null) {
            nomePessoa.setText(pessoa.getnome());
        }
        if (pessoa.getPais() != null) {
            selecionarNacionalidade.setValue(pessoa.getPais().getNome());
        }
    }

    public void inserirNacionalidade() throws SQLException {
        for (Pais p : paises) {
            selecionarNacionalidade.getItems().addAll(p.getNome());
        }
    }

    public void setDadosPessoa() {
        if (validarCampos()) {
            pessoa.setnome(nomePessoa.getText());
            System.out.println(pessoa);
            pessoa.setPais(paises.get(selecionarNacionalidade
                    .getSelectionModel().getSelectedIndex()));
        }
    }

    public boolean validarCampos() {

        if ((nomePessoa.getText() == null || nomePessoa.getText().isEmpty())
                && selecionarNacionalidade.getValue().isEmpty()) {
            return false;
        }
        return true;
    }

}
