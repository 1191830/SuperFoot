/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Liga;
import com.mycompany.superfute.models.Pessoa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nelso
 */
public class DbPessoa {

    /**
     * Buscar todos as pessoas na base de dados
     * @return lista de pessoas
     */
    public static ArrayList<Pessoa> obterPessoas() throws SQLException {

        ArrayList<Pessoa> listaPessoa = new ArrayList();
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select pessoa.nome,pais.nome as 'pais' "
                    + "from pessoa "
                    + "inner join pais on pais.id = pessoa.nacionalidade");
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setnome(rs.getString("nome"));
                pessoa.setnacionalidade(rs.getString("pais"));
                listaPessoa.add(pessoa);
            }

            return listaPessoa;
        } catch (Exception ex) {
             System.err.println("Erro: " + ex.getMessage());
            return null;
        }

    }
    

    public static ArrayList<Pessoa> obterMelhorMarcadorLiga(Liga liga) throws SQLException{
        Pessoa jogador;
        ArrayList<Pessoa> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, golos from view_melhorMarcador  where Liga = "
                + liga.getAno() + " order by golos desc ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
            
                jogador = new Pessoa(rs.getString("jogador"),rs.getInt("golos"));
                
                listaJogadores.add(jogador);

            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return listaJogadores;
    
    }
    
    public static ArrayList<Pessoa> obterMelhorMarcadorGeral() throws SQLException{
        Pessoa jogador;
        ArrayList<Pessoa> listaJogadores = new ArrayList();
        Connection conn = Dbconn.getConn();
        String query = "select jogador, golos from view_melhorMarcador order by golos desc ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
            
                jogador = new Pessoa(rs.getString("jogador"),rs.getInt("golos"));
                
                listaJogadores.add(jogador);

            }
            
        } catch(Exception ex){       
            ex.printStackTrace();      
        }
        
        return listaJogadores;
    
    }

    public static ArrayList<Pessoa> obterPessoasSemFuncao() throws SQLException {

        ArrayList<Pessoa> listaPessoa = new ArrayList();
        String cmd ="";
        
        try {
            Connection conn = Dbconn.getConn();
            Statement st = conn.createStatement();
         
            cmd = "select * from vPessoasSemFuncoes";
            
            ResultSet rs = st.executeQuery(cmd);
                    
                    
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setnome(rs.getString("nomePessoa"));
              ;
                listaPessoa.add(pessoa);
            }

            
        }   catch (Exception ex) {
            
            
            System.err.println("Erro: " + ex.getMessage());
            return null;
        }
        
       
            return listaPessoa;
    }
    
    
    
    
  

//    /**
//     * Inserir pessoa na base de dados
//     * @return 
//     * @throws SQLException 
//     */
//    public static boolean inserirPessoa() throws SQLException {
//
//        try {
//            Connection conn = Dbconn.getConn();
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery("");
//            while (rs.next()) {
//                Pessoa pessoa = new Pessoa();
//                pessoa.setnome(rs.getString("nome"));
//                pessoa.setnacionalidade(rs.getString("pais"));
//                System.out.println(pessoa);
//                listaPessoa.add(pessoa);
//            }
//
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//
//    }
}
