/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.models.Pais;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pcoelho
 */
public class PaisFormController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private Stage stageDialog;
    private Pais pais;
    boolean btnReturn;
    
     public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
        // TODO
        
    }    

    @FXML
    private void btnAplicar(ActionEvent event) throws SQLException {
        setDadosPais();
        System.out.println(pais);
        setBtnReturn(true);
        stageDialog.close();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        stageDialog.close();
    }
    
    
    public void preencherCampos() {
        if (pais != null) {
            txtNome.setText(pais.getNome());
            
        }
    }
    

    
    public void setDadosPais() throws SQLException {
        if (validarCampos()) {
         
            pais.setNome(txtNome.getText());
            System.out.println(pais);
           
        }
    }
    
     public boolean validarCampos() {

        if (txtNome.getText() == null || txtNome.getText().isEmpty())
                 {
            return false;
        }
        return true;
    }
    
    
}
