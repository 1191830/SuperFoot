/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Classificacao;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Jornada;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class DbJogo {
     public static ArrayList<Jogo> jogoDB() throws SQLException {
        ArrayList<Jogo> jogo = new ArrayList<>();

        try {
            Connection con = Dbconn.getConn();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("Select * from liga");
            while (rst.next()) {
                Jogo j = new Jogo();
//                j.getJogo().setJogo(rst.getInt("idJogo"));
                jogo.add(j);
            }
            return jogo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
     
     public static Jogo getJogoById(int id) throws SQLException{
        Jogo jogo = new Jogo();
         Date dia;
        
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "select * from jogo where id = " + id;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
 
                   jogo = new Jogo(
                    id,
                    DbJornada.getJornadaByID(rs.getInt("jornada")),
                    DbEstadio.getEstadiosByID(rs.getInt("idEstadio")), 
                    DbArbitro.getArbitroByID(rs.getInt("arbitro")));
                }
                
                
                            
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return jogo;
        }
     
     public static ArrayList<Jogo> obterJogosLigaJornada(Jornada jornada) throws SQLException {
       
        ArrayList<Jogo> arrJogos = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";
        int id;

        try {
            cmd = "Select Distinct * from func_resultadoTodos(-1) where jornada = " + jornada.getIdJornada();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Jogo jogo = new Jogo(
                        rs.getInt("jogo"),
                        rs.getNString("equipaCasa"),                       
                        rs.getInt("golosCasa"),
                        rs.getNString("equipaFora"),                       
                        rs.getInt("golosFora"));
            
            arrJogos.add(jogo);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return arrJogos;
    }
}
