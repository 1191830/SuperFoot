/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbLiga;
import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class LigaFormController implements Initializable {

    @FXML
    private Label labelAnoLiga;
    @FXML
    private TextField fieldNomeLiga;
   
    @FXML
    private ComboBox<Integer> cbAnoLiga;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnCancelar;
    
    
    private Stage stageDialog;
    private Liga liga;
    
    boolean btnReturn;
    
    
       public Stage getStageDialog() {
        return stageDialog;
    }

    public void setStageDialog(Stage StageDialog) {
        this.stageDialog = StageDialog;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) throws SQLException {
        this.liga = liga;
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
    }    

    @FXML
    private void btnAplicar(ActionEvent event) throws SQLException {

        
        System.out.println(liga);
        if (liga.getAno() == 0) {
            setDadosLiga();
            if (liga.getAno() != 0){
                DbLiga.inserirLigaDB(liga);
            }
            
        } else {
            
            setDadosLiga();
            DbLiga.alterarLigaDb(liga);

        }
        setBtnReturn(true);
        stageDialog.close();

    }

    @FXML
    private void btnCancelar(ActionEvent event) {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void preencherCampos() throws SQLException {
        System.out.println(liga);
        if (liga.getAno() != 0) {

            fieldNomeLiga.setText(liga.getNome());

            cbAnoLiga.setVisible(false);
            labelAnoLiga.setVisible(false);
           

        } else {

            preenchercbAnoLiga();

        }
    }

    private void preenchercbAnoLiga() throws SQLException {
        ArrayList<Liga> ligas = new ArrayList<>();
        ArrayList<Integer> anos = new ArrayList<>();

        ligas = DbLiga.obterArrayLigas();

        for (Liga l : ligas) {

            anos.add(l.getAno());
        }

        for (Integer i = 2020; i < 2050; i++) {

            if (anos.contains(i)) {

                anos.remove(i);

            } else {

                anos.add(i);
            }

        }

        cbAnoLiga.getItems().addAll(anos);

    }
    

    
    
    public void setDadosLiga() throws SQLException {
        if (validarCampos()) {
                   
            if (liga.getAno() == 0) {
                
               liga.setNome(fieldNomeLiga.getText());
               liga.setAno(cbAnoLiga.getValue());
              
   
            } else {
                liga.setNome(fieldNomeLiga.getText());
            }

            System.out.println(liga);

        }
    }
     
        public boolean validarCampos() {
            

       if ((fieldNomeLiga.getText() == null || fieldNomeLiga.getText().isEmpty())
                && cbAnoLiga.getValue() == null) {
            return false;
       }
         
        return true;
    }
    
}
