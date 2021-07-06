/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbJornada;
import com.mycompany.superfute.models.Liga;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Rui
 */
public class NewJornadaController implements Initializable {


    @FXML
    private TextField txtNewJornada;
    @FXML
    private Button btnAplicar;
    @FXML
    private Button btnSair;
    
    private Liga selectedLiga;
    private int jornada;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initLiga(Liga liga){
        selectedLiga = liga;
    }
    
    @FXML
    private void OnActionNewJornada(ActionEvent event) throws SQLException, IOException {
        if(event.getSource() == btnSair){
            
            FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fxml/jornada.fxml"));
                Parent root = loader.load();
                JornadaController controller = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add("css/new.css");
            stage.show();
            
            controller.initLiga(selectedLiga);
            // get a handle to the stage
            Stage stage2 = (Stage) btnSair.getScene().getWindow();
            // close the scene
            stage2.close();
        }else if (event.getSource() == btnAplicar){
            jornada = Integer.parseInt(txtNewJornada.getText());
            
            DbJornada.saveJornada(jornada, selectedLiga.getAno() );
            txtNewJornada.setText(null);
           
            
        }
    }

}
