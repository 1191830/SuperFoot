/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

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
                System.out.println(pessoa);
                listaPessoa.add(pessoa);
            }

            return listaPessoa;
        } catch (Exception ex) {
            return null;
        }

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
