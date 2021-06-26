/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbEstadio;
import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class EquipaFormController implements Initializable {

    @FXML
    private Label labelLigaAno;
    @FXML
    private TextField nomeEquipa;
    @FXML
    private ComboBox<String> selecionarEstadio;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    private Stage stageDialog;
    private Equipa equipa;
    boolean btnReturn;
    private ArrayList<Estadio> estadios;

    public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage stageDialog) {
        this.stageDialog = stageDialog;
    }

    public Equipa getEquipa() {
        return equipa;
        
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
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
            estadios = DbEstadio.obterEstadios();
            inserirEstadio();
        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou os estádios", "Estádios");
        }
    }

    @FXML
    private void btnAplicar(ActionEvent event) {
        setDadosEquipa();
        setBtnReturn(true);
        stageDialog.close();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        stageDialog.close();
    }
    
    /**
     * Preenche todos os atributos do controller
     */
     public void preencherCampos() {
        if (equipa != null) {
            nomeEquipa.setText(equipa.getnome());
        }
        if (equipa.getestadio()!= null) {
            selecionarEstadio.setValue(equipa.getestadio().getNome());
        }
    }
    
     /**
      * Popula o select com todos os estádios
      * @throws SQLException 
      */
     public void inserirEstadio() throws SQLException {
        for (Estadio e : estadios) {
            selecionarEstadio.getItems().addAll(e.getNome());
        }
    }

     /**
      * Método insere todos os dados nos campos de Equipa
      */
    public void setDadosEquipa() {
        if (validarCampos()) {
            equipa.setnome(nomeEquipa.getText());
            equipa.setestadio(estadios.get(selecionarEstadio
                    .getSelectionModel().getSelectedIndex()));
        }
    }

    /**
     * Validação de campos
     * @return 
     */
    public boolean validarCampos() {

        if ((nomeEquipa.getText() == null || nomeEquipa.getText().isEmpty())
                && selecionarEstadio.getValue().isEmpty()) {
            return false;
        }
        return true;
    }

}
