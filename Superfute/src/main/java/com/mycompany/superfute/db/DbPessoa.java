/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Equipa;
import com.mycompany.superfute.models.Jogo;
import com.mycompany.superfute.models.Pais;
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
    
     public static ArrayList<Pessoa> getPessoasJogoEquipa(Jogo jogo, Equipa equipa) throws SQLException {

        ArrayList<Pessoa> lista = new ArrayList();
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from pessoaJogo where idJogo = " + jogo.getJogo()
                + " and idEquipa = " + equipa.getId());
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(DbPessoa.getNomebyId(rs.getInt("idPessoa")));

                lista.add(pessoa);
            }
            
        } catch (Exception ex) {

            System.out.println(ex.getMessage());

            System.err.println("Erro: " + ex.getMessage());
            return null;
        }
        return lista;
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
    
    public static String getNomebyId(int id) throws SQLException {
        String nome = "";
        
        try {
            Connection conn = Dbconn.getConn();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT nome from Pessoa where id = " + id);

            while (rs.next()) {
                nome = rs.getString("nome");
            }

            st.close();
        } catch (Exception ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return nome;
    }

   

    /**
     * Met??do para fazer atuliza????o na tabela pessoa
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
     * M??todo que retorna todas as pessoas que n??o est??o relacionadas com
     * nenhuma tabela e fun????o
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

    public static ArrayList<Pessoa> obterJogadorExpuls??es() throws SQLException {
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

    public static ArrayList<Pessoa> obterJogadorEquipa() throws SQLException {
        ArrayList<Pessoa> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select * from jogadoresEquipas";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Pessoa jogador = new Pessoa();
                jogador.setNome(rs.getString("jogador"));
                jogador.setNomeEquipa(rs.getString("equipa"));
                jogador.setId(rs.getInt("idPessoa"));
                jogador.setNacionalidade(rs.getString("nacionalidade"));                
                listaJogadores.add(jogador);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaJogadores;

    }


}
