/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import Utils.MessageBoxes;
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
public class DbPais {

    public static ArrayList<Pais> getTodosPaises() throws SQLException {
        ArrayList<Pais> paises = new ArrayList();
        Connection conn = Dbconn.getConn();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from Pais");
            while (rs.next()) {
                paises.add(
                        new Pais(
                                rs.getInt("id"),
                                rs.getString("nome")
                        )
                );
            }
            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return paises;
    }

    public static ObservableList<Pais> getPaises() throws SQLException {
        ObservableList<Pais> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from Pais";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Pais obj = new Pais(
                        rs.getInt("id"),
                        rs.getString("nome"));
                lista.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

    public static void savePais( String nome) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //Novo Pais
            cmd = "INSERT INTO pais( nome) VALUES ( ?)";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }

    public static void updatePais(int id,String nome) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //2º eliminar pais
            cmd = "UPDATE Pais SET "
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
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser actualização pois é usado noutra tabela", "Falhou a eliminação do registo!");
            } else {
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a actualização do registo!");
            }
        }

    }

    public static void deletePais(int id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            cmd = "DELETE FROM Pais "
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
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser eliminado pois é usado noutra tabela", "Falhou a eliminação do registo!");
            } else {
                MessageBoxes.ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a eliminação do registo!");
            }
        }

    }
    
     public static Pais getPaisbyNome(String nome) throws SQLException {
        Pais pais = new Pais();
        Connection conn = Dbconn.getConn();
        String cmd = "";
        
        try {
            cmd = "SELECT * from Pais where nome like '" + nome + "'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                    
                    pais.setId(rs.getInt("id"));
                    pais.setNome(rs.getString("nome"));
                    
                    
                
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return pais;
    }

    public static int getIdPaisPorNome(String nome) {
            int id=0 ;
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id from Pais where nome = " + nome);
            if(rs.next()) {
               id = rs.getInt("id");
            }
            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return id;
    }

}
