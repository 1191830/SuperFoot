/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Pais;
import com.mycompany.superfute.models.Liga;
import com.mycompany.superfute.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nelso
 */
public class DbPessoa {

    /**
     * Buscar todos as pessoas na base de dados
     *
     * @return lista de pessoas
     */
    public static ArrayList<Pessoa> obterPessoas() throws SQLException {

        ArrayList<Pessoa> listaPessoa = new ArrayList();
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select pessoa.id as idP, pessoa.nome,pais.nome as 'pais', pais.id from pessoa inner join pais on pais.id = pessoa.nacionalidade");
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idP"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setPais(new Pais(rs.getInt("id"), rs.getString("pais")));
                pessoa.setNacionalidade(rs.getString("pais"));
                listaPessoa.add(pessoa);
            }
            return listaPessoa;
        } catch (Exception ex) {

            System.out.println(ex.getMessage());

            System.err.println("Erro: " + ex.getMessage());
            return null;
        }

    }
    
    public static Pessoa getPessoaById(int id) throws SQLException{
        Pessoa pessoa = new Pessoa();
        
        Connection conn = Dbconn.getConn();
        String cmd = "";

            try {
                cmd = "select * from pessoa where id = " + id;

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(cmd);

                while (rs.next()) {
 
                   pessoa = new Pessoa(
                    id,
                    rs.getString("nome"),
                    rs.getString("nacionalidade"));
                }
                
                
                            
                st.close();
                conn.close();
            } catch (Exception ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
            return pessoa;
        }

    /**
     * Método retorna arraylist com os melhores marcadores por liga
     *
     * @param liga
     * @return
     * @throws SQLException
     */
    public static ArrayList<Pessoa> obterMelhorMarcadorLiga(Liga liga) throws SQLException {
        Pessoa jogador;
        ArrayList<Pessoa> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, golos from view_melhorMarcador  where Liga = "
                + liga.getAno() + " order by golos desc ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                jogador = new Pessoa(rs.getString("jogador"), rs.getInt("golos"));

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
    public static ArrayList<Pessoa> obterMelhorMarcadorGeral() throws SQLException {
        Pessoa jogador;
        ArrayList<Pessoa> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, golos from view_melhorMarcador order by golos desc ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                jogador = new Pessoa(rs.getString("jogador"), rs.getInt("golos"));
                listaJogadores.add(jogador);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaJogadores;
    }

    /**
     * Metódo para fazer atulização na tabela pessoa
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public static boolean updatePessoa(Pessoa p) throws SQLException {

        try {
            Connection conn = Dbconn.getConn();
            PreparedStatement stmt = conn.prepareStatement("Update Pessoa set nome = ? , nacionalidade = ? where id = ?");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getPais().getId());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Inserir pessoa na base de dados
     *
     * @return
     * @throws SQLException
     */
    public static boolean inserirPessoa(Pessoa p) throws SQLException {
        try {
            Connection conn = Dbconn.getConn();
            PreparedStatement stmt = conn.prepareStatement("Insert into Pessoa Values (?,?)");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getPais().getId());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Método que retorna todas as pessoas que não estão relacionadas com
     * nenhuma tabela e função
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Pessoa> obterPessoasSemFuncao() throws SQLException {
        ArrayList<Pessoa> listaPessoa = new ArrayList();
        String cmd = "";

        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            cmd = "select * from vPessoasSemFuncoes";
            ResultSet rs = st.executeQuery(cmd);
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nomePessoa"));
                listaPessoa.add(pessoa);
            }

        } catch (Exception ex) {

            System.err.println("Erro: " + ex.getMessage());
            return null;
        }

        return listaPessoa;
    }

    public static ArrayList<Pessoa> obterJogadorExpulsões() throws SQLException {
        ArrayList<Pessoa> listaJogadores = new ArrayList();
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
                Pessoa jogador = new Pessoa();
                jogador.setNome(rs.getString("jogador"));
                jogador.setNumExpulsoes(rs.getInt("Expulsoes"));
                listaJogadores.add(jogador);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaJogadores;

    }
}
