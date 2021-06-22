/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import Utils.MessageBoxes;
import com.mycompany.superfute.db.DbCidade;
import com.mycompany.superfute.db.DbPais;
import com.mycompany.superfute.models.Cidade;
import com.mycompany.superfute.models.Pais;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
 * @author pcoelho
 */
public class CidadeFormController implements Initializable {

    @FXML
    private ComboBox<String> cbPais;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    private Stage stageDialog;
    private Cidade cidade;
   
   
    boolean btnReturn;
    
    @FXML
    private TextField txtNome;
    @FXML
    private Label labelPais;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        try {            inserirNacionalidade();
        } catch (SQLException ex) {
            MessageBoxes.ShowMessage(Alert.AlertType.WARNING,
                    "Não carregou os países", "Países");
        }
    }    

    @FXML
    private void btnAplicar(ActionEvent event) throws SQLException {
        setDadosCidade();
        System.out.println(cidade);
        if (cidade.getId() ==0){
            
            DbCidade.saveCidade(cidade.getNome(), cidade.getPais().getId());
        
        }else {
        
            DbCidade.updateCidade(cidade.getId(), cidade.getNome());
            
            
        
        }
        setBtnReturn(true);
        stageDialog.close();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        stageDialog.close();
    }
    
    public void preencherCampos() {
        
        System.out.println(cidade);

        if (cidade.getId() !=0) {
          
            txtNome.setText(cidade.getNome());
            labelPais.setVisible(false);
            cbPais.setVisible(false);
            
            
            //cbPais.setValue(cidade.getPais().getNome());
        } else{
        
            System.out.println("NOVO");
        }
    }
    
    public void inserirNacionalidade() throws SQLException {
        for (Pais p : DbPais.getTodosPaises()) {
            cbPais.getItems().addAll(p.getNome());
        }
    }
    
    public void setDadosCidade() throws SQLException {
        if (validarCampos()) {
         
            cidade.setNome(txtNome.getText());
            cidade.setPais(DbPais.getPaisbyNome(cbPais.getValue()));
            
            
         
            
            System.out.println(cidade);
           
        }
    }
    
     public boolean validarCampos() {

        if ((txtNome.getText() == null || txtNome.getText().isEmpty())
                && cbPais.getValue() == null) {
            return false;
        }
        return true;
    }

  
    
}
