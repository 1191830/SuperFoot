/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import static Utils.MessageBoxes.ShowMessage;
import com.mycompany.superfute.models.Cidade;
import com.mycompany.superfute.models.Estadio;
import com.mycompany.superfute.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author pcoelho
 */
public class DbArbitro {
    

    
     public static ObservableList<Pessoa> getArbitros() throws SQLException {
        ObservableList<Pessoa> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT arbitro.idPessoa, Pessoa.nome from arbitro inner join pessoa on arbitro.IdPessoa = pessoa.id";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("idPessoa"));
                p.setnome(rs.getString("nome"));
                lista.add(p);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
     
     
     
     public static ArrayList<Pessoa> obterArbitros() throws SQLException {
        ArrayList<Pessoa> arrArbitros = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT arbitro.id, Pessoa.nome from arbitro inner join pessoa on arbitro.IdPessoa = pessoa.id";


            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setnome(rs.getString("nome"));
                arrArbitros.add(p);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        
        
        return arrArbitros;
     
     }
     
    public static void saveArbitro( int idPessoa) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //Novo Pais
            cmd = "INSERT INTO arbitro(idPessoa) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, idPessoa);
           
            

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
    
    
    public static void deleteArbitro( int id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAa");

        try {

            cmd = "DELETE FROM arbitro "
                    + "WHERE id=?";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, id);

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            if (ex.getErrorCode() == 547) {
                ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser eliminado pois é usado noutra tabela", "Falhou a eliminação do registo!");
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a eliminação do registo!");
            }
        }

    }

    

    

    


    
    


    
}