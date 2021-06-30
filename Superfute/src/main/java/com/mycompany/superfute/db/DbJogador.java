/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Estatistica;
import com.mycompany.superfute.models.Jogador;
import com.mycompany.superfute.models.Liga;
import com.mycompany.superfute.models.Pessoa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author nelso
 */
public class DbJogador {

    Pessoa pessoa = new Pessoa();

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
        String query = "select jogador, sum (amareloduplos + vermelho)"
                + " as expulsoes, count (idJogo) as jogos, sum (amarelo)"
                + " as amarelos, sum (golosanulados)"
                + " as anulados from dbo.func_dadosJogadorTodos(-1,-1) "
                + "group by jogador order by expulsoes desc";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setNome(rs.getString("jogador"));
                jogador.setVemelho(rs.getInt("expulsoes"));
                jogador.setQtdJogos(rs.getInt("jogos"));
                jogador.setAmarelos(rs.getInt("amarelos"));
                jogador.setGolosAnulados(rs.getInt("anulados"));
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

    public static ArrayList<Estatistica> obterJogadorEstatisticas(Pessoa pessoa) throws SQLException {
        ArrayList<Estatistica> listaJogadoresEst = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select liga as liga, count (idJogo) as jogos, "
                + "sum (golos) as golos, sum (amarelo) as amarelos, "
                + "sum (amareloduplos) as amarelosduplos,"
                + " sum (vermelho) as vermelho,"
                + " jogador from dbo.func_dadosJogadorTodos(-1,-1)"
                + " where idJogador = '" + pessoa.getId() + "' group by liga, jogador";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Estatistica jogador = new Estatistica();
                jogador.setAnoLiga(rs.getInt("liga"));
                jogador.setQuantJogos(rs.getInt("jogos"));
                jogador.setGol(rs.getInt("golos"));
                jogador.setAmarelo(rs.getInt("amarelos"));
                jogador.setAmareloDuplo(rs.getInt("amarelosduplos"));
                jogador.setVermelho(rs.getInt("vermelho"));
                listaJogadoresEst.add(jogador);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaJogadoresEst;
    }
}
