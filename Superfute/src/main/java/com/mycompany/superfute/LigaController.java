/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.Dbconn;
import com.mycompany.superfute.models.Liga;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class LigaController implements Initializable {

    @FXML
    private TableView<Liga> listaLigas;
    @FXML
    private TableColumn<Liga, String> colunaLiga;
    @FXML
    private TableColumn<Liga, Integer> colunaAno;
    @FXML
    private Button btnCriarLiga;
    @FXML
    private Button btnEditarLiga;
    @FXML
    private Button btnApagarLiga;
    @FXML
    private Button btnJornadas;
    @FXML
    private Button btnEquipas;
    @FXML
    private Button btnJogadores;
    @FXML
    private Button btnClassificacao;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private Label labelLigaAno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            mostrarLigas();
        } catch (SQLException ex) {
            Logger.getLogger(LigaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnCriarLiga(ActionEvent event) {
    }

    @FXML
    private void btnEditarLiga(ActionEvent event) {
    }

    @FXML
    private void btnApagarLiga(ActionEvent event) {
    }

    @FXML
    private void btnJornadas(ActionEvent event) {
    }

    @FXML
    private void btnEquipas(ActionEvent event) {
    }

    @FXML
    private void btnJogadores(ActionEvent event) {
    }

    @FXML
    private void btnClassificacao(ActionEvent event) {
    }

    @FXML
    private void btnEstatisticas(ActionEvent event) {
    }
    
    
    public ObservableList<Liga> obterLigas() throws SQLException{
    
        ObservableList<Liga> listaLiga = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String query = "SELECT * FROM  LIGAS";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Liga liga; 
            
            while(rs.next()){
            
                liga = new Liga(rs.getString("nome"),rs.getInt("ano"));
                listaLiga.add(liga);
            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return listaLiga;
   
    
    }
    
    public void mostrarLigas() throws SQLException{
        
        ObservableList<Liga> listaLiga = obterLigas();
        
        colunaLiga.setCellValueFactory(new PropertyValueFactory<Liga,String>("nome"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Liga,Integer>("ano"));
        

    
    }
    
    
    
    
}
