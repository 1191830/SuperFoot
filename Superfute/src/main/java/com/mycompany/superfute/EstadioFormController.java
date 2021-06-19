/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbCidade;
import com.mycompany.superfute.models.Cidade;
import com.mycompany.superfute.models.Estadio;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pcoelho
 */
public class EstadioFormController implements Initializable {

    @FXML
    private ComboBox<String> cbCidade;
    @FXML
    private TextField txtNome;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private Stage stageDialog;
    private Estadio estadio;
    boolean btnReturn;
    
     public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
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
        // TODO
        try {
            inserirCidades();
        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou os países", "Países");
        }
    }    

    @FXML
    private void btnAplicar(ActionEvent event) throws SQLException {
        setDadosEstadio();
        System.out.println(estadio);
        setBtnReturn(true);
        stageDialog.close();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        stageDialog.close();
    }
    
    
    
     public void preencherCampos() {
        if (estadio != null) {
            txtNome.setText(estadio.getNome());
            cbCidade.setValue(estadio.getCidade().getNome());
        }
    }
    
    public void inserirCidades() throws SQLException {
        for (Cidade c : DbCidade.obterCidades()) {
            cbCidade.getItems().addAll(c.getNome());
        }
    }
    
    public void setDadosEstadio() throws SQLException {
        if (validarCampos()) {
         
            estadio.setNome(txtNome.getText());
            estadio.setCidade(DbCidade.getCidadebyNome(cbCidade.getValue()));
            
            
         
            
            System.out.println(estadio);
           
        }
    }
    
     public boolean validarCampos() {

        if ((txtNome.getText() == null || txtNome.getText().isEmpty())
                && cbCidade.getValue() == null) {
            return false;
        }
        return true;
    }
    
}
