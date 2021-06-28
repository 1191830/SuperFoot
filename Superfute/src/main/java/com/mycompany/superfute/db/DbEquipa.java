/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Jogo;
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
//                Estadio estadio = new Estadio(
//                        rs.getInt("id_estadio"),
//                        rs.getString("nome_estadio"),
//                        rs.getInt("cidade"));
//                        

                //            Equipa obj = new Equipa(id_equipa, nome_cidade, estadio);
                //        lista.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
     
     public static Equipa getEquipaById(int id) throws SQLException{
        Equipa equipa = new Equipa();

        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "select * from equipa where id = " + id;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
 
                   equipa = new Equipa(
                    id,
                    rs.getString("nome"),
                    DbEstadio.getEstadiosByID(rs.getInt("estadio")));
                }
                                      
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return equipa;
        }
     
     public static String getNomebyId(int id) throws SQLException {
        String nome = "";
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT nome from equipa where id = " + id);

            while (rs.next()) {
                nome = rs.getString("nome");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return nome;
    }
     
     public static Equipa getEquipaJogo(Jogo jogo, int casaFora) throws SQLException{
        Equipa equipa = new Equipa();
        
        Connection conn = Dbconn.getConn();

            try {

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * from jogoEquipa where idJogo = " + jogo.getJogo()
                + " and casaFora = " + casaFora);

                while (rs.next()) {
 
                   equipa = new Equipa(
                    rs.getInt("idEquipa"),
                    DbEquipa.getNomebyId(rs.getInt("idEquipa")));
                }
                                           
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return equipa;
        }
     
     public static ArrayList<Equipa> getEquipasJogo(Jogo jogo) throws SQLException{
        Equipa equipa = new Equipa();
        ArrayList<Equipa> lista = new ArrayList<>();
        
        

            try {
                Connection conn = Dbconn.getConn();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * from jogoEquipa where idJogo = " + jogo.getJogo());

                while (rs.next()) {
 
                   equipa = new Equipa(
                    rs.getInt("idEquipa"),
                    DbEquipa.getNomebyId(rs.getInt("idEquipa")));
                   lista.add(equipa);
                }
                                           
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return lista;
        }

    public static ArrayList<Equipa> obterEquipasEstadio() throws SQLException {
        ArrayList<Equipa> lista = new ArrayList<>();
        
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from vEquipaEstadio");
            while (rs.next()) {
                Equipa equipa = new Equipa();
                equipa.setEstadio(new Estadio());
                equipa.setId(rs.getInt("id_equipa"));
                equipa.setNome(rs.getString("nome_equipa"));
                equipa.getEstadio().setId(rs.getInt("id_estadio"));
                equipa.getEstadio().setNome(rs.getString("nome_estadio"));
                lista.add(equipa);
            }
            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }

    public static void saveEquipa(String nome, int id_estadio) throws SQLException {
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

    public static boolean updateEquipa(int id, String nome, int id_estadio) throws SQLException {
        Connection conn = Dbconn.getConn();
        String cmd = "";
        try {
            cmd = "UPDATE Equipa SET nome = ? ,estadio = ? WHERE id= ? ";
            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setString(1, nome);
            statement.setInt(2, id_estadio);
            statement.setInt(3, id);

            //Execute the update
            statement.executeUpdate();
            
            //commit in case you have turned autocommit to false
            conn.commit();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            if (ex.getErrorCode() == 547) {
                ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser actualização pois é usado noutra tabela", "Falhou a eliminação do registo!");
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a actualização do registo!");
            }
            return false;
        }

    }

    public static void deleteEquipa(int id) throws SQLException {
        
        try {
            Connection conn = Dbconn.getConn();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM equipa "
                    + "WHERE id=?");
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

    
     public static ArrayList<String[]> obterPlantelEquipa(int idEquipa) throws SQLException {
        ArrayList<String[]> listaPlantelFuncoes = new ArrayList<>();
        try {
            Connection conn = Dbconn.getConn();
             PreparedStatement statement = conn.
                     prepareStatement("select p.nome,f.funcao from pessoaEquipa pe "
                     + "inner join pessoa p on p.id = pe.idPessoa "
                        + "inner join funcaoPessoa f on f.id = pe.funcao "
                            + "where idEquipa = ?");
              statement.setInt(1, idEquipa);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String[] plantelFuncao = new String[2];
                plantelFuncao[0] = rs.getString("nome");
                plantelFuncao[1] = rs.getString("funcao");
                listaPlantelFuncoes.add(plantelFuncao);
            }
             conn.commit();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return listaPlantelFuncoes;
    }
}
