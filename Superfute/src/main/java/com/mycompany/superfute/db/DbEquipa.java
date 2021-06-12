/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Estadio;
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
 * @author pcoelho
 */
public class DbEquipa {
    
    
     public static ObservableList<Equipa> getEquipas() throws SQLException {
        ObservableList<Equipa> lista = FXCollections.observableArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from vEquipaEstadio";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                
                int id_equipa = rs.getInt("id_equipa");
                String nome_cidade = rs.getString("nome_cidade");
                Estadio estadio = new Estadio(
                        rs.getInt("id_estadio"),
                        rs.getString("nome_estadio"),
                        rs.getInt("cidade"));
                        

                Equipa obj = new Equipa(id_equipa, nome_cidade, estadio);

                lista.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

    public static void saveEquipa(String nome, int id_estadio ) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            //Novo Pais
            cmd = "INSERT INTO Equipa(id,nome,estadio) VALUES (NEWID(), ?, ?)";

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            statement.setInt(2, id_estadio);

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

    public static void updateEquipa(int id,String nome, int id_estadio) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

           
            cmd = "UPDATE cidade SET "
                    + " nome=? "
                    + " estadio=? "
                    + "WHERE id='" + id + "'";;

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            statement.setInt(2, id_estadio);

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

    public static void deleteEquipa(int id) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {

            cmd = "DELETE FROM equipa "
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
