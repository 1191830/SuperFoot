/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Cidade;
import com.mycompany.superfute.models.Pais;
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


public class DbCidade {

    public static ArrayList<Cidade> obterCidades() throws SQLException {
       
        ArrayList<Cidade> arrCidades = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from vCidadePais";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("pais_nome"));

                int cidade_id = rs.getInt("cidade_id");
                String cidade_nome = rs.getString("cidade_nome");

                Cidade obj = new Cidade(pais, cidade_id, cidade_nome);

                arrCidades.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return arrCidades;
    }
    
    //getCidadebyNome
      public static Cidade getCidadebyNome(String nome) throws SQLException {
        Cidade cidade = new Cidade();
        Connection conn = Dbconn.getConn();
        String cmd = "";
        
        try {
            cmd = "SELECT * from vCidadePais where cidade_nome = '" + nome + "'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                    
                    Pais pais = new Pais(
                        rs.getInt("pais_id"),
                        rs.getString("pais_nome"));

                int cidade_id = rs.getInt("cidade_id");
                String cidade_nome = rs.getString("cidade_nome");

                cidade = new Cidade(pais, cidade_id, cidade_nome);
               
                    
                    
                
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return cidade;
    }      
            
    
    
    
    
    
    

    public static void saveCidade(String nome, int pais_id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //Novo Pais
            cmd = "INSERT INTO cidade(nome,pais) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            statement.setInt(2, pais_id);

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

    public static void updateCidade(int id,String nome) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

           
            cmd = "UPDATE cidade SET "
                    + " nome=? "               
                    + "WHERE id='" + id + "'";;

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            ;

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            if (ex.getErrorCode() == 547) {
                ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser actualização pois é usado noutra tabela", "Falhou a eliminação do registo!");
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a actualização do registo!");
            }
        }

    }

    public static void deleteCidade(int id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            cmd = "DELETE FROM Cidade "
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

    public static void ShowMessage(Alert.AlertType type, String msg, String header) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
