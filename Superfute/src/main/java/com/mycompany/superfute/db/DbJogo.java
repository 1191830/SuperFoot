/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superfute.db;

import com.mycompany.superfute.models.Jogo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
