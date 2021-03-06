/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Cidade;
import com.mycompany.superfute.models.Estadio;
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
 * 
 * 
 */
public class DbEstadio {



    
     public static ObservableList<Estadio> getEstadios() throws SQLException {
        ObservableList<Estadio> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from estadio";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Cidade c = new Cidade();
                c.setId(rs.getInt("idCidade"));
                c.setNome(rs.getString("nomeCidade"));
                
                Estadio obj = new Estadio(
                        rs.getInt("idEstadio"),
                        rs.getString("nomeEstadio"),
                        c);
                
                lista.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
     
     public static Estadio getEstadiosByID(int id) throws SQLException {
        Estadio estadio = new Estadio();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from estadio where id = " + id;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                
                estadio = new Estadio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        DbCidade.getCidadebyID(rs.getInt("cidade")));
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return estadio;
    }
     
     
     
     public static ArrayList<Estadio> obterEstadios() throws SQLException {
        ArrayList<Estadio> arrEstadio = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "select * from estadio";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                             
                Estadio obj = new Estadio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        DbCidade.getCidadebyID(rs.getInt("cidade")));
                
               arrEstadio.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        
        
        return arrEstadio;
     
     }
     
    public static void saveEstadio(String nome, int cidade) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //Novo Pais
            cmd = "INSERT INTO estadio(nome, cidade) VALUES ( ?, ?)";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            statement.setInt(2, cidade);
            

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

    public static void updateEstadio(int id, String nome) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //2?? eliminar filme
            cmd = "UPDATE estadio SET "
                    + " nome=? "
                    + "WHERE id='" + id + "'";;

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
 

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            if (ex.getErrorCode() == 547) {
                ShowMessage(Alert.AlertType.ERROR, "Registo n??o pode ser actualiza????o pois ?? usado noutra tabela", "Falhou a elimina????o do registo!");
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a actualiza????o do registo!");
            }
        }

    }

    public static void deleteEstadio(int id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            cmd = "DELETE FROM estadio "
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
                ShowMessage(Alert.AlertType.ERROR, "Registo n??o pode ser eliminado pois ?? usado noutra tabela", "Falhou a elimina????o do registo!");
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a elimina????o do registo!");
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
