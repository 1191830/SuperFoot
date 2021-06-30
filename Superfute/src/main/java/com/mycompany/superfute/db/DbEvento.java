/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import static com.mycompany.superfute.db.DbCidade.ShowMessage;
import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author Rui
 */
public class DbEvento {
    
    private static final int PARTES = 5;
    private static final int TIPOSEVENTO = 7;
    
    public static ArrayList<Evento> getEventoByJogo(Jogo jogo) throws SQLException {
        ArrayList<Evento> lista = new ArrayList();
        Connection conn = Dbconn.getConn();
        String cmd = "";

        try {
            cmd = "SELECT * from evento where idJogo = " + jogo.getJogo();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Evento obj = new Evento(
                    rs.getInt("id"),
                    rs.getInt("minuto"),
                    rs.getInt("idJogo"),
                    DbEquipa.getEquipaById(rs.getInt("idEquipa")),
                    DbPessoa.getPessoaById(rs.getInt("idPessoa")),
                    rs.getInt("idTipoEvento"),
                    rs.getInt("parte"));

                    lista.add(obj);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public static String getEventoByTipo(int tipoEvento) throws SQLException {
        String evento = "";
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT tipo from tipoEvento where id = " + tipoEvento);

            while (rs.next()) {
                evento = rs.getString("tipo");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return evento;
    }
    
    public static String[][] getPartes() throws SQLException {
        String[][] lista = new String[PARTES][2];
        int count = 0;
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from parteJogo order by id");

            while (rs.next()) {
                lista[count][0] = String.valueOf(rs.getInt("id"));
                lista[count][1] = rs.getString("parte");
                count ++;
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public static String[][] getTipoEvento() throws SQLException {
        String[][] lista = new String[TIPOSEVENTO][2];
        int count = 0;
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from tipoEvento order by id");

            while (rs.next()) {
                lista[count][0] = String.valueOf(rs.getInt("id"));
                lista[count][1] = rs.getString("tipo");
                count ++;
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public static String getParteByIdParte(int id) throws SQLException {
        String parte = "";
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT parte from parteJogo where id = " + id);

            while (rs.next()) {
                parte = rs.getString("parte");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return parte;
    }
    
    public static int getIdParteByNome(String nome) throws SQLException {
        int id = 0;
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT id from parteJogo where parte like " + "'" + nome + "'");

            while (rs.next()) {
                id = rs.getInt("id");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return id;
    }
    
    public static int getIdTipoEventoByNome(String nome) throws SQLException {
        int id = 0;
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT id from tipoEvento where tipo like " + "'" + nome + "'");

            while (rs.next()) {
                id = rs.getInt("id");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return id;
    }
    
    public static boolean insertEvento(Evento evento) throws SQLException {
        
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            //Novo Evento
            cmd = "INSERT INTO evento(idJogo, idEquipa, idPessoa, idTipoEvento, minuto, parte) VALUES (?, ?, ?, ?, ?, ?)";                   

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, evento.getidJogo());          
            statement.setInt(2, evento.getEquipa().getId());
            statement.setInt(3, evento.getJogador().getId());
            statement.setInt(4, evento.gettipoEvento());
            statement.setInt(5, evento.getminuto());
            statement.setInt(6, evento.getIdParte());
            

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "Registo Duplicado", "Falhou ao guardar registo!");
            return false;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            ShowMessage(Alert.AlertType.ERROR, "", "Falhou ao guardar registo!");
            return false;
        }
    }
    
    public static boolean updateEvento(Evento evento) throws SQLException {
        
        try {
            Connection conn = Dbconn.getConn();

            String cmd = ("update evento set idEquipa = ?, idPessoa = ?, idTipoEvento = ?, minuto = ?, parte = ? where id = " + evento.getId());

            PreparedStatement statement = conn.prepareStatement(cmd);
            statement.setInt(1, evento.getEquipa().getId());
            statement.setInt(2, evento.getJogador().getId());
            statement.setInt(3, evento.gettipoEvento());
            statement.setInt(4, evento.getminuto());
            statement.setInt(5, evento.getIdParte());
            ;

            //Execute the update
            statement.executeUpdate();

            //commit in case you have turned autocommit to false
            conn.commit();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex.getMessage());
            if (ex.getErrorCode() == 547) {
                ShowMessage(Alert.AlertType.ERROR, "Registo não pode ser actualização pois é usado noutra tabela", "Falhou a eliminação do registo!");
                return false;
            } else {
                ShowMessage(Alert.AlertType.ERROR, ex.getMessage(), "Falhou a actualização do registo!");
                return false;
            }
        }
    }
    
    public static boolean deleteEvento(int id) throws SQLException {
        
        try {
            Connection conn = Dbconn.getConn();

            PreparedStatement stm = conn.prepareStatement("delete from evento where id = " + id);
            stm.executeUpdate();          

            //commit in case you have turned autocommit to false
            conn.commit();
            
            stm.close();
            return true;
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
            return false;
        }
    }
}
