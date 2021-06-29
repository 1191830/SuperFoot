/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Jornada;
import Utils.MessageBoxes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Rui
 */
public class DbJornada {
    
    
    public static ObservableList<Jornada> getJornadas() throws SQLException{
        ObservableList<Jornada> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "SELECT * from Jornada";

                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
                    Jornada obj = new Jornada(
                            rs.getInt("liga"),
                            rs.getInt("jornada"));
                    lista.add(obj);
                }

                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return lista;
        }
    
    public static Jornada getJornadaByID(Integer jornada) throws SQLException{
        Jornada newJornada = new Jornada();

        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "SELECT * from Jornada where jornada = " + jornada;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
                    newJornada = new Jornada(
                            rs.getInt("jornada"),
                            rs.getInt("liga"));
                    
                }

                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erroooo: " + ex.getMessage());
            }
            return newJornada;
        }
    public static ObservableList<Jornada> getJornadaByLiga(Integer liga) throws SQLException{
        Jornada jornada = new Jornada();
        ObservableList<Jornada> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "SELECT * from Jornada where liga = ?";

                PreparedStatement st = conn.prepareStatement(cmd);
                st.setInt(1, liga);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    Jornada obj = new Jornada(
                            rs.getInt("jornada"),
                            rs.getInt("liga"));
                    lista.add(obj);
                }

                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erroooo: " + ex.getMessage());
            }
            return lista;
        }
    
    public static Jornada getJornadaByJogo(Integer jogo) throws SQLException{
        Jornada jornada = new Jornada();
        
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "select * from jornada as jor  join jogo as jog on jor.jornada = jog.jornada "
                        + "where jog.id = " + jogo;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
                    Jornada obj = new Jornada(
                            rs.getInt("jornada"),
                            rs.getInt("liga"));                    
                }

                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erroooo: " + ex.getMessage());
            }
            return jornada;
        }
    
    public static boolean existsJornada(Integer jornada, Integer liga) throws SQLException{
        boolean result = true;
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "SELECT TOP 1,1 from Jornada where jornada = ? and liga = ?";

                PreparedStatement st = conn.prepareStatement(cmd);
                st.setInt(1, jornada);
                st.setInt(2, liga);
                
                //Execute the update
                result = st.execute();

                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
                
        return result;
    }
    
    public static void saveJornada(Integer jornada, Integer liga) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";
        boolean exist = existsJornada(jornada,liga);

        try {

                cmd = "INSERT INTO jornada(jornada, liga) VALUES (?,?)";

            PreparedStatement st = conn.prepareStatement(cmd);
            st.setInt(1, jornada);
            st.setInt(2, liga);

            //Execute the update
            st.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
            st.close();
            conn.close();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            MessageBoxes.ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
        }
    }
    

    public static void deleteJornada(Integer jornada, Integer liga) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd1 = "";

        try {

            //1º eliminar atores do filme
            cmd1 += "DELETE FROM jornada WHERE jornada=? and liga = ?";

            PreparedStatement statement = conn.prepareStatement(cmd1);
            statement.setInt(1, jornada);
            statement.setInt(2, liga);

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

    }
    
}
