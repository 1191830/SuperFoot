/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute;

import com.mycompany.superfute.db.DbArbitro;
import com.mycompany.superfute.db.DbPessoa;
import com.mycompany.superfute.models.Pessoa;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nelso
 */
public class ArbitrosController implements Initializable {

    @FXML
    private Label labelNomeEquipa;
    @FXML
    private Button btnInserirArbitro;
    @FXML
    private Button btnRemoverArbitro;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableView<Pessoa> tabelaArbitros;
    @FXML
    private TableColumn<Pessoa, String> colunaNomeArbitro;
    @FXML
    private Button btnGuardar;

    private ObservableList<Pessoa> observableList;
    private ArrayList<Pessoa> arrArbitros;
    private ArrayList<Pessoa> arrPessoasSemFuncao;

    private ArrayList<Pessoa> arrArbitrosInicial;
    private ArrayList<Pessoa> arrPessoasSemFuncaoInicial;

    private Pessoa pessoa;
    private Boolean flag = false; // não voltar a carregar o array da base de dados mas da memória esse é o original

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            preencherArbitros();
            preencherPessoasSemFuncao();
        } catch (SQLException ex) {
            Logger.getLogger(ArbitrosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnInserirArbitro(ActionEvent event) throws SQLException {

        pessoa = tabelaPessoa.getSelectionModel().getSelectedItem();

        if (pessoa == null) {

            System.out.println("PESSOAL NULO");

        } else {
            arrArbitros.add(pessoa);
            arrPessoasSemFuncao.remove(pessoa);
            flag = true;

            preencherArbitros();
            preencherPessoasSemFuncao();

        }
    }

    @FXML
    private void btnRemoverArbitro(ActionEvent event) throws SQLException {

        pessoa = tabelaArbitros.getSelectionModel().getSelectedItem();

        if (pessoa == null) {

            System.out.println("PESSOAL NULO");

        } else {
            arrPessoasSemFuncao.add(pessoa);
            arrArbitros.remove(pessoa);
            flag = true;

            preencherArbitros();
            preencherPessoasSemFuncao();
        }
        
        
        

    }

    @FXML
    private void btnCancelar(ActionEvent event) {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnGuardar(ActionEvent event) throws SQLException {
        
        for (Pessoa p : arrPessoasSemFuncao ){
        
                // se a pessoa não estiver no array inicial, é adicionado da base de dados do arbitros
                if(!arrPessoasSemFuncaoInicial.contains(p)){
                
                    //aapagar a base de dados dos arbitos
                   //  DbArbitro.saveArbitro(p.getId());
                  DbArbitro.deleteArbitro(p.getId());
                   
                     System.out.println(p);
                     System.out.println("AQUI");
                    
                    
                    
    
        }
        }
        
        for(Pessoa p: arrArbitros){
            // Se a arbito não estiver no array inical , é apagado da base de dados dos arbitos
            
             if(!arrArbitrosInicial.contains(p)){
            
                 
                 //Guardaa base de dados dos arbitos
                    System.out.println("Adicionar");
                    System.out.println(p);
                    DbArbitro.saveArbitro(p.getId());
                   
                
             }
        }
        
        flag = false;
        preencherArbitros();
        preencherPessoasSemFuncao();
        
        
        
        

    }

    public void preencherArbitros() throws SQLException {

        colunaNomeArbitro.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getnome()));

        if (flag == false) {
            arrArbitros = DbArbitro.obterArbitros();
            arrArbitrosInicial = new ArrayList<>(arrArbitros);
            
          

        }

        observableList = FXCollections.observableArrayList(arrArbitros);
        tabelaArbitros.setItems(observableList);

    }

    public void preencherPessoasSemFuncao() throws SQLException {

        colunaNome.setCellValueFactory(cellData
                -> new SimpleObjectProperty<String>(cellData.getValue().getnome()));

        if (flag == false) {
            arrPessoasSemFuncao = DbPessoa.obterPessoasSemFuncao();
            arrPessoasSemFuncaoInicial = new ArrayList<>(arrPessoasSemFuncao);

        }

        
        observableList = FXCollections.observableArrayList(arrPessoasSemFuncao);
        tabelaPessoa.setItems(observableList);

    }

}
