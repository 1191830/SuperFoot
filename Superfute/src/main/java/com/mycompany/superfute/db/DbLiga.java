/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Liga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class DbLiga {

    /**
     *
     * @return
     */
    public static ArrayList<Liga> ligaDB() throws SQLException {
        ArrayList<Liga> liga = new ArrayList<>();

        try {
            Connection con = Dbconn.getConn();
            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery("Select * from liga");
            while (rst.next()) {
                Liga l = new Liga();
                l.setNome(rst.getString("nome"));
                l.setAno(rst.getInt("ano"));
                liga.add(l);
            }
            return liga;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean inserirLigaDB(Liga liga) throws SQLException {

        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("insert into liga" + "ano,nome"
                    + "values(?,?)");
            pst.setInt(1, liga.getAno());
            pst.setString(2, liga.getNome());
            pst.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean alterarLigaDb(Liga liga) throws SQLException {
        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("Update liga"
                    + "set ano = ?, nome = ?");
            pst.setInt(1, liga.getAno());
            pst.setString(2, liga.getNome());
            pst.executeQuery();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarLigaDb(Liga liga) throws SQLException {
        try {
            Connection con = Dbconn.getConn();
            PreparedStatement pst;
            pst = con.prepareStatement("Delete from Liga"
                    + "Where ano = ?");
            pst.setInt(1, liga.getAno());
            pst.executeQuery();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
