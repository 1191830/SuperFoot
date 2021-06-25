/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Pais;
import com.mycompany.superfute.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                pessoa.setnome(rs.getString("nome"));
                pessoa.setPais(new Pais(rs.getInt("id"),rs.getString("pais")));
                System.out.println(pessoa);
                listaPessoa.add(pessoa);
            }

            return listaPessoa;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
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
            stmt.setString(1, p.getnome());
            stmt.setInt(2, p.getPais().getId());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean updatePessoa(Pessoa p) throws SQLException {

        try {
            Connection conn = Dbconn.getConn();
            PreparedStatement stmt = conn.prepareStatement("Update Pessoa set nome = ? , nacionalidade = ? where id = ?");
            stmt.setString(1, p.getnome());
            stmt.setInt(2, p.getPais().getId());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
