/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Liga;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nelso
 */
public class DbJogador {

    /**
     * Método retorna arraylist com os melhores marcadores por liga
     *
     * @param liga
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> obterMelhorMarcadorLiga(Liga liga) throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList();
        try {
            Connection conn = Dbconn.getConn();
            String query = "select jogador, golos from view_melhorMarcador  where Liga = "
                    + liga.getAno() + " order by golos desc ";
            Statement st;
            ResultSet rs;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setNome(rs.getString("jogador"));
                jogador.setGolosMarcados(rs.getInt("golos"));
                listaJogadores.add(jogador);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listaJogadores;
    }

    /**
     * Método retorna arraylist com os melhores marcadores por liga
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> obterMelhorMarcadorGeral() throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, golos from view_melhorMarcador order by golos desc ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setNome(rs.getString("jogador"));
                jogador.setGolosMarcados(rs.getInt("golos"));
                listaJogadores.add(jogador);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaJogadores;
    }

    /**
     * Método retorna arraylist com os jogadores expulsos
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> obterJogadorExpulsões() throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, sum (amareloduplos + vermelho) "
                + "as expulsoes from dbo.func_dadosJogadorTodos(-1,-1)"
                + "group by jogador order by expulsoes desc";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setNome(rs.getString("jogador"));
                jogador.setVemelho(rs.getInt("Expulsoes"));
                listaJogadores.add(jogador);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listaJogadores;
    }

    /**
     * Método retorna arraylist com os jogadores expulsos
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> obterJogadorAmarelo() throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, sum (amareloduplos + vermelho) "
                + "as expulsoes from dbo.func_dadosJogadorTodos(-1,-1)"
                + "group by jogador order by expulsoes desc";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setNome(rs.getString("jogador"));
                jogador.setVemelho(rs.getInt("Expulsoes"));
                listaJogadores.add(jogador);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listaJogadores;
    }

    /**
     * Método retorna arraylist com todos os dados dos jogadores
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Jogador> obterDadosCompletosJogadores() throws SQLException {
        ArrayList<Jogador> listaJogadores = new ArrayList();
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from view_DadosJogadores");
            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setId(rs.getInt("idjogador"));
                jogador.setNome(rs.getString("jogador"));
                jogador.setGolosMarcados(rs.getInt("golos"));
                jogador.setGolosAnulados(rs.getInt("golosAnulados"));
                jogador.setAmarelos(rs.getInt("amarelos"));
                jogador.setDuploAmarelo(rs.getInt("amareloDuplos"));
                jogador.setVemelho(rs.getInt("vermelho"));
                jogador.setFuncao("Jogador");
                jogador.setIdFuncao(1);
                listaJogadores.add(jogador);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listaJogadores;
    }

}
