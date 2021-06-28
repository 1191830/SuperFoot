/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Classificacao;
import com.mycompany.superfute.models.Jornada;
import com.mycompany.superfute.models.Liga;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rui
 */
public class DbClassificacao {
    
    public static ArrayList<Classificacao> obterClassificacaoLiga(Liga liga) throws SQLException {
       
        ArrayList<Classificacao> arrClassificacao = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";
        int id;

        try {
            
            if(liga == null)
            cmd = "SELECT * from view_DadosEquipas order by pontuacao desc, golos desc";
            else  {
            cmd = "SELECT * from view_DadosEquipas where idliga = " + liga.getAno() + " order by pontuacao desc, golos desc";
            }
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(cmd);
            while (rs.next()) {
                Classificacao equipa = new Classificacao(
                        rs.getNString("equipa"),
                        rs.getInt("pontuacao"),
                        rs.getInt("golos"),
                        rs.getInt("golos Sofridos"));
                
            id = rs.getInt("idEquipa");
            equipa.setJogos(getJogos(id));
            equipa.setVitorias(getResultado(id,'V'));
            equipa.setEmpates(getResultado(id,'E'));
            equipa.setDerrotas(getResultado(id,'D'));
            
            arrClassificacao.add(equipa);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return arrClassificacao;
    }
    
    public static int getResultado(int equipa, char resultado) throws SQLException{
        Connection conn = Dbconn.getConn();
        String cmd = "";
        int quantidade = 0;

        try {
            cmd = "select count(idJogo) as quantidade from func_dadosJogosTodos(-1,"+equipa+") "
                    + "where resultado = " + "'" + resultado + "'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while(rs.next()){
            quantidade = rs.getInt("quantidade");  
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }       
        
        return quantidade;
    }
    
    public static int getJogos(int equipa) throws SQLException{
        Connection conn = Dbconn.getConn(); 
        String cmd = "";
        int quantidade = 0;

        try {
            cmd = "select count(idJogo) as quantidade from func_dadosJogosTodos(-1,"+equipa+")";

            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(cmd);
            while(rs.next()){

            quantidade = rs.getInt("quantidade"); 
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }       
        
        return quantidade;
    }
    
    public static ArrayList<Classificacao> obterJogosLigaJornada(Jornada jornada, int casaFora) throws SQLException {
       
        ArrayList<Classificacao> arrJogos = new ArrayList();; 
        Connection conn = Dbconn.getConn();
        String cmd = "";
        int id;

        try {
            cmd = "select * from func_dadosJogosTodos(-1,-1) where casaFora = " + casaFora 
                    + " and idJornada = "+ jornada.getIdJornada() + " order by idjogo";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(cmd);

            while (rs.next()) {
                Classificacao equipa = new Classificacao(
                        rs.getNString("equipa"),                       
                        rs.getInt("golos"));
            
            arrJogos.add(equipa);
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return arrJogos;
    }
    
}
