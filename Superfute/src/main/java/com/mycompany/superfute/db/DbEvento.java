/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Evento;
import com.mycompany.superfute.models.Jogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rui
 */
public class DbEvento {
    
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
    
    public static void deleteEvento(int id) throws SQLException {
        
        try {
            Connection conn = Dbconn.getConn();

            PreparedStatement stm = conn.prepareStatement("delete from evento where id = " + id);
            stm.executeUpdate();          

            //commit in case you have turned autocommit to false
            conn.commit();
            
            stm.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

    }
}
